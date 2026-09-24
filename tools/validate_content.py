#!/usr/bin/env python3
"""Kiểm tra dữ liệu content của Last Outpost (chạy trong CI và cục bộ).

Nguồn quy tắc: docs/design/02_SUPPORTING_DESIGN/09_Technical_Architecture_v1.0.md §XXXII
("Content validation cần bắt ...") và 15_Content_Production_Pack_v1.0.md §II–III (ID, status).

Đã triển khai:
  - schema mismatch (schemaVersion của manifest và từng file)
  - duplicate IDs (toàn bộ content)
  - missing reference (mission.roster / mission.unlockAfter → id không tồn tại)
  - invalid roster (rỗng, trùng, không phải enemy/boss)
  - missing reward / missing stats (bắt buộc từ status Prototype trở lên; Draft chỉ cảnh báo)
  - dependency cycle (mission.unlockAfter)
  - quy tắc ID (định dạng, tiền tố theo loại, không chứa version, khớp tên file)
  - quy tắc thời lượng mission 5–15 phút (Current Decisions §1)
  - kênh production: cấm Draft/Prototype/... (Content Production Pack §III)
  - legacy guard: không để từ khoá hệ thống legacy đã loại bỏ lọt vào content/code

CHƯA triển khai (chưa có dữ liệu Tower/Tech Tree để kiểm): invalid module compatibility, Tech Tree cycle.

Chỉ dùng thư viện chuẩn Python 3.10+.
"""
from __future__ import annotations

import argparse
import json
import os
import re
import sys
from dataclasses import dataclass, field
from pathlib import Path

SUPPORTED_SCHEMA_VERSION = 1

# Content Production Pack §III
STATUS_ORDER = [
    "Draft",
    "Internal Review",
    "Prototype",
    "Playtest",
    "Balance",
    "Art Polish",
    "QA",
    "Approved",
    "Released",
]
STATUS_RANK = {name: rank for rank, name in enumerate(STATUS_ORDER)}
PROTOTYPE_RANK = STATUS_RANK["Prototype"]
APPROVED_RANK = STATUS_RANK["Approved"]

ENEMY_KINDS = {"species": "ENEMY_", "boss": "BOSS_"}
ENEMY_GROUPS = {"A", "B", "C", "BOSS"}  # Core Design v1.3: nhóm A/B/C + Boss
MISSION_TYPES = {"Standard", "Specialist", "Challenge", "Boss", "Tutorial", "Event"}  # Content Production Pack §IV
MISSION_PREFIX = "MISSION_"

DURATION_MIN_MINUTES = 5  # Current Decisions §1: mission target 5–15 phút
DURATION_MAX_MINUTES = 15

ID_RE = re.compile(r"^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$")
VERSION_IN_ID_RE = re.compile(r"(^|_)V\d+(_|$)")  # "ID không chứa version"

# Các hệ thống legacy KHÔNG được khôi phục (Current Decisions §5, Master Design Index §6).
LEGACY_PATTERN = re.compile(
    r"(?<![a-z])(territory[ _-]?war|siege|hex[ _-]?grid|nuclear|diplomacy)(?![a-z])",
    re.IGNORECASE,
)
LEGACY_SCAN_SUFFIXES = {".json", ".kt", ".kts", ".xml"}


@dataclass
class Report:
    errors: list[str] = field(default_factory=list)
    warnings: list[str] = field(default_factory=list)

    def error(self, where: str, message: str) -> None:
        self.errors.append(f"{where}: {message}")

    def warn(self, where: str, message: str) -> None:
        self.warnings.append(f"{where}: {message}")

    @property
    def ok(self) -> bool:
        return not self.errors


def _load_json(path: Path, report: Report, where: str):
    try:
        with path.open("r", encoding="utf-8") as handle:
            return json.load(handle)
    except FileNotFoundError:
        report.error(where, "không tìm thấy file")
    except json.JSONDecodeError as exc:
        report.error(where, f"JSON không hợp lệ: {exc}")
    return None


def _check_common(doc: dict, where: str, report: Report) -> int | None:
    """Kiểm tra các trường chung. Trả về rank của status (hoặc None nếu status lỗi)."""
    schema = doc.get("schemaVersion")
    if schema != SUPPORTED_SCHEMA_VERSION:
        report.error(where, f"schema mismatch: schemaVersion={schema!r}, hỗ trợ {SUPPORTED_SCHEMA_VERSION}")

    version = doc.get("contentVersion")
    if not isinstance(version, int) or isinstance(version, bool) or version < 1:
        report.error(where, f"contentVersion phải là số nguyên >= 1, nhận {version!r}")

    status = doc.get("status")
    if status not in STATUS_RANK:
        report.error(where, f"status không hợp lệ: {status!r} (hợp lệ: {', '.join(STATUS_ORDER)})")
        return None
    return STATUS_RANK[status]


def _check_id(doc: dict, path: Path, prefix: str, where: str, report: Report) -> str | None:
    content_id = doc.get("id")
    if not isinstance(content_id, str) or not content_id:
        report.error(where, "thiếu id")
        return None
    if not ID_RE.match(content_id):
        report.error(where, f"id {content_id!r} phải là UPPER_SNAKE_CASE, human-readable")
    if not content_id.startswith(prefix):
        report.error(where, f"id {content_id!r} phải bắt đầu bằng {prefix!r}")
    if VERSION_IN_ID_RE.search(content_id):
        report.error(where, f"id {content_id!r} không được chứa version (version nằm ở contentVersion/schemaVersion)")
    if path.stem != content_id:
        report.error(where, f"tên file {path.name!r} phải khớp id {content_id!r}")
    return content_id


def _find_cycle(graph: dict[str, list[str]]) -> list[str] | None:
    """Trả về một chu trình (danh sách id) nếu có, ngược lại None."""
    WHITE, GREY, BLACK = 0, 1, 2
    color = {node: WHITE for node in graph}
    stack: list[str] = []

    def visit(node: str) -> list[str] | None:
        color[node] = GREY
        stack.append(node)
        for nxt in graph.get(node, []):
            if nxt not in color:
                continue  # tham chiếu thiếu đã được báo lỗi riêng
            if color[nxt] == GREY:
                return stack[stack.index(nxt):] + [nxt]
            if color[nxt] == WHITE:
                found = visit(nxt)
                if found:
                    return found
        stack.pop()
        color[node] = BLACK
        return None

    for node in list(graph):
        if color[node] == WHITE:
            found = visit(node)
            if found:
                return found
    return None


def validate_content(content_dir: Path, channel: str = "beta") -> Report:
    report = Report()
    content_dir = Path(content_dir)

    # --- manifest -----------------------------------------------------------------------------
    manifest = _load_json(content_dir / "manifest.json", report, "manifest.json")
    if isinstance(manifest, dict):
        for key in ("packId", "packVersion", "schemaVersion", "dependencies"):
            if key not in manifest:
                report.error("manifest.json", f"thiếu trường {key!r}")
        if manifest.get("schemaVersion") != SUPPORTED_SCHEMA_VERSION:
            report.error(
                "manifest.json",
                f"schema mismatch: schemaVersion={manifest.get('schemaVersion')!r}, hỗ trợ {SUPPORTED_SCHEMA_VERSION}",
            )
        if not isinstance(manifest.get("dependencies", []), list):
            report.error("manifest.json", "dependencies phải là danh sách")
    elif manifest is not None:
        report.error("manifest.json", "phải là object JSON")

    seen_ids: dict[str, str] = {}
    ranks: dict[str, int] = {}

    def register(content_id: str | None, where: str, rank: int | None) -> None:
        if content_id is None:
            return
        if content_id in seen_ids:
            report.error(where, f"duplicate id {content_id!r} (đã có ở {seen_ids[content_id]})")
        else:
            seen_ids[content_id] = where
        if rank is not None:
            ranks[content_id] = rank

    # --- enemies ------------------------------------------------------------------------------
    enemy_ids: set[str] = set()
    for path in sorted((content_dir / "enemies").glob("*.json")):
        where = f"enemies/{path.name}"
        doc = _load_json(path, report, where)
        if not isinstance(doc, dict):
            if doc is not None:
                report.error(where, "phải là object JSON")
            continue
        rank = _check_common(doc, where, report)
        kind = doc.get("kind")
        if kind not in ENEMY_KINDS:
            report.error(where, f"kind không hợp lệ: {kind!r} (hợp lệ: {', '.join(ENEMY_KINDS)})")
            prefix = "ENEMY_"
        else:
            prefix = ENEMY_KINDS[kind]
        content_id = _check_id(doc, path, prefix, where, report)
        if doc.get("group") not in ENEMY_GROUPS:
            report.error(where, f"group không hợp lệ: {doc.get('group')!r} (hợp lệ: {', '.join(sorted(ENEMY_GROUPS))})")
        if kind == "boss" and doc.get("group") != "BOSS":
            report.error(where, "kind=boss thì group phải là BOSS")
        if not doc.get("name"):
            report.error(where, "thiếu name")
        stats = doc.get("stats")
        if stats is None:
            if rank is not None and rank >= PROTOTYPE_RANK:
                report.error(where, f"thiếu stats ở status {doc.get('status')!r} (bắt buộc từ Prototype)")
            else:
                report.warn(where, "stats chưa có (Draft) — OPEN, không tự đoán số liệu")
        elif not isinstance(stats, dict):
            report.error(where, "stats phải là object hoặc null")
        register(content_id, where, rank)
        if content_id and kind in ENEMY_KINDS:
            enemy_ids.add(content_id)

    # --- missions -----------------------------------------------------------------------------
    unlock_graph: dict[str, list[str]] = {}
    mission_unlocks: dict[str, tuple[str, list]] = {}
    for path in sorted((content_dir / "missions").glob("*.json")):
        where = f"missions/{path.name}"
        doc = _load_json(path, report, where)
        if not isinstance(doc, dict):
            if doc is not None:
                report.error(where, "phải là object JSON")
            continue
        rank = _check_common(doc, where, report)
        content_id = _check_id(doc, path, MISSION_PREFIX, where, report)

        if doc.get("missionType") not in MISSION_TYPES:
            report.error(where, f"missionType không hợp lệ: {doc.get('missionType')!r} (hợp lệ: {', '.join(sorted(MISSION_TYPES))})")

        roster = doc.get("roster")
        if not isinstance(roster, list) or not roster:
            report.error(where, "invalid roster: Fixed Enemy Roster không được rỗng")
        else:
            if len(set(roster)) != len(roster):
                report.error(where, "invalid roster: có species bị lặp")
            for species_id in roster:
                if species_id not in enemy_ids:
                    report.error(where, f"missing reference: roster tham chiếu {species_id!r} không tồn tại trong content/enemies")

        duration = doc.get("targetDurationMinutes")
        if duration is not None:
            if (
                not isinstance(duration, dict)
                or not isinstance(duration.get("min"), int)
                or not isinstance(duration.get("max"), int)
            ):
                report.error(where, "targetDurationMinutes phải là null hoặc {min:int, max:int}")
            elif not (DURATION_MIN_MINUTES <= duration["min"] <= duration["max"] <= DURATION_MAX_MINUTES):
                report.error(
                    where,
                    f"targetDurationMinutes {duration} nằm ngoài khoảng {DURATION_MIN_MINUTES}–{DURATION_MAX_MINUTES} phút (Current Decisions §1)",
                )

        rewards = doc.get("rewards")
        if not rewards:
            if rank is not None and rank >= PROTOTYPE_RANK:
                report.error(where, f"missing reward ở status {doc.get('status')!r} (bắt buộc từ Prototype)")
            else:
                report.warn(where, "rewards chưa có (Draft) — OPEN")
        elif not isinstance(rewards, dict):
            report.error(where, "rewards phải là object hoặc null")

        unlock_after = doc.get("unlockAfter", [])
        if not isinstance(unlock_after, list):
            report.error(where, "unlockAfter phải là danh sách id mission")
            unlock_after = []
        register(content_id, where, rank)
        if content_id:
            unlock_graph[content_id] = [u for u in unlock_after if isinstance(u, str)]
            mission_unlocks[content_id] = (where, unlock_after)

    for mission_id, (where, unlock_after) in mission_unlocks.items():
        for target in unlock_after:
            if target not in unlock_graph:
                report.error(where, f"missing reference: unlockAfter tham chiếu {target!r} không tồn tại")
    cycle = _find_cycle(unlock_graph)
    if cycle:
        report.error("missions", "dependency cycle trong unlockAfter: " + " → ".join(cycle))

    # --- kênh production ----------------------------------------------------------------------
    if channel == "production":
        for content_id, rank in sorted(ranks.items()):
            if rank < APPROVED_RANK:
                report.error(
                    seen_ids[content_id],
                    f"kênh production không nhận content ở status < Approved ({content_id})",
                )
    elif channel != "beta":
        report.error("cli", f"channel không hợp lệ: {channel!r} (beta|production)")

    return report


def scan_legacy(paths: list[Path]) -> Report:
    """Legacy guard: tìm từ khoá của các hệ thống legacy đã loại bỏ trong content/code."""
    report = Report()
    for root in paths:
        if not root.exists():
            continue
        files = [root] if root.is_file() else sorted(p for p in root.rglob("*") if p.is_file())
        for path in files:
            if path.suffix not in LEGACY_SCAN_SUFFIXES:
                continue
            try:
                text = path.read_text(encoding="utf-8")
            except UnicodeDecodeError:
                continue
            for lineno, line in enumerate(text.splitlines(), start=1):
                match = LEGACY_PATTERN.search(line)
                if match:
                    report.error(
                        f"{path.as_posix()}:{lineno}",
                        f"legacy guard: {match.group(0)!r} thuộc hệ thống legacy đã loại bỏ "
                        "(Current Decisions §5) — cần quyết định thiết kế rõ ràng trước khi dùng",
                    )
    return report


def _emit(report: Report, strict: bool) -> None:
    github = os.environ.get("GITHUB_ACTIONS") == "true"
    for message in report.errors:
        print(f"::error::{message}" if github else f"ERROR   {message}")
    for message in report.warnings:
        level = "error" if strict else "warning"
        print(f"::{level}::{message}" if github else f"WARNING {message}")


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description="Kiểm tra content Last Outpost")
    parser.add_argument("--content-dir", default="content", type=Path)
    parser.add_argument("--channel", default="beta", choices=["beta", "production"])
    parser.add_argument("--strict", action="store_true", help="coi cảnh báo là lỗi")
    parser.add_argument(
        "--legacy-scan",
        nargs="*",
        type=Path,
        default=None,
        help="thư mục/file quét legacy guard (mặc định: content, core/src, app/src cạnh content-dir)",
    )
    args = parser.parse_args(argv)

    report = validate_content(args.content_dir, args.channel)
    root = args.content_dir.resolve().parent
    scan_paths = args.legacy_scan
    if scan_paths is None:
        scan_paths = [args.content_dir, root / "core" / "src", root / "app" / "src"]
    legacy = scan_legacy(scan_paths)
    report.errors.extend(legacy.errors)

    _emit(report, args.strict)
    failed = bool(report.errors) or (args.strict and bool(report.warnings))
    print(
        f"content: {len(report.errors)} lỗi, {len(report.warnings)} cảnh báo, kênh={args.channel}"
        + (" — THẤT BẠI" if failed else " — OK")
    )
    return 1 if failed else 0


if __name__ == "__main__":
    sys.exit(main())
