#!/usr/bin/env python3
"""Bảo vệ tài liệu thiết kế nguồn (docs/design/) khỏi thay đổi vô tình.

Master Design Index §4 (Dependency Rule): khi thiết kế thay đổi phải
  1. xác định source-of-truth, 2. sửa, 3. cập nhật tài liệu downstream,
  4. cập nhật version + changelog, 5. chạy lại conflict scan.

Script này so sánh SHA-256 của mọi file trong docs/design/ với docs/design/DESIGN_SHA256SUMS.txt.
CI thất bại nếu có file bị sửa/thêm/xoá mà chưa cập nhật manifest — buộc thay đổi phải có chủ đích.

Sau khi đã cập nhật version + changelog cho thay đổi thiết kế:
    python3 tools/verify_docs.py --update

Định dạng manifest tương thích `sha256sum -c` (chạy trong docs/design/). File văn bản (.md/.txt) được
chuẩn hoá CRLF→LF trước khi băm để kết quả không phụ thuộc cấu hình git autocrlf.
"""
from __future__ import annotations

import argparse
import hashlib
import sys
from pathlib import Path

MANIFEST_NAME = "DESIGN_SHA256SUMS.txt"
TEXT_SUFFIXES = {".md", ".txt"}


def file_digest(path: Path) -> str:
    data = path.read_bytes()
    if path.suffix.lower() in TEXT_SUFFIXES:
        data = data.replace(b"\r\n", b"\n")
    return hashlib.sha256(data).hexdigest()


def compute(design_dir: Path) -> dict[str, str]:
    result: dict[str, str] = {}
    for path in sorted(design_dir.rglob("*")):
        if not path.is_file() or (path.name == MANIFEST_NAME and path.parent == design_dir):
            continue
        result[path.relative_to(design_dir).as_posix()] = file_digest(path)
    return result


def read_manifest(manifest: Path) -> dict[str, str]:
    entries: dict[str, str] = {}
    for lineno, line in enumerate(manifest.read_text(encoding="utf-8").splitlines(), start=1):
        if not line.strip():
            continue
        digest, sep, rel = line.partition("  ")
        if not sep or len(digest) != 64:
            raise ValueError(f"{manifest.name}:{lineno}: dòng không đúng định dạng '<sha256>  <đường dẫn>'")
        entries[rel] = digest
    return entries


def write_manifest(manifest: Path, digests: dict[str, str]) -> None:
    lines = [f"{digest}  {rel}" for rel, digest in sorted(digests.items())]
    manifest.write_text("\n".join(lines) + "\n", encoding="utf-8")


def diff(expected: dict[str, str], actual: dict[str, str]) -> tuple[list[str], list[str], list[str]]:
    missing = sorted(set(expected) - set(actual))
    added = sorted(set(actual) - set(expected))
    changed = sorted(rel for rel in set(expected) & set(actual) if expected[rel] != actual[rel])
    return missing, added, changed


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description="Kiểm tra toàn vẹn docs/design")
    parser.add_argument("--design-dir", default="docs/design", type=Path)
    parser.add_argument("--update", action="store_true", help="ghi lại manifest theo trạng thái hiện tại")
    args = parser.parse_args(argv)

    design_dir: Path = args.design_dir
    manifest = design_dir / MANIFEST_NAME
    if not design_dir.is_dir():
        print(f"ERROR không thấy thư mục {design_dir}")
        return 1

    actual = compute(design_dir)
    if args.update:
        write_manifest(manifest, actual)
        print(f"Đã ghi {manifest} ({len(actual)} file).")
        return 0

    if not manifest.is_file():
        print(f"ERROR thiếu {manifest}. Chạy: python3 tools/verify_docs.py --update")
        return 1

    try:
        expected = read_manifest(manifest)
    except ValueError as exc:
        print(f"ERROR {exc}")
        return 1

    missing, added, changed = diff(expected, actual)
    for rel in changed:
        print(f"ERROR đã sửa:  {rel}")
    for rel in added:
        print(f"ERROR mới thêm: {rel}")
    for rel in missing:
        print(f"ERROR đã mất:  {rel}")

    if missing or added or changed:
        print(
            "\ndocs/design là source-of-truth. Thay đổi phải theo Controlled Change "
            "(docs/design/01_CANONICAL/LAST_OUTPOST_MASTER_DESIGN_INDEX_v1.2.md §4): cập nhật version + changelog "
            "(docs/history/changelog/), rồi chạy `python3 tools/verify_docs.py --update`."
        )
        return 1

    print(f"docs/design: {len(actual)} file khớp manifest — OK")
    return 0


if __name__ == "__main__":
    sys.exit(main())
