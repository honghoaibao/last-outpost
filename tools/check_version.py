#!/usr/bin/env python3
"""Kiểm tra tính nhất quán của phiên bản (docs/decisions/0002-version-scheme.md).

  python3 tools/check_version.py                       # kiểm tra version.properties + CHANGELOG + release notes
  python3 tools/check_version.py --tag v2026.09-beta.1 # thêm: tag phải khớp VERSION_NAME
  python3 tools/check_version.py --github-env >> "$GITHUB_ENV"  # xuất VERSION_NAME, VERSION_CODE, BUILD_LABEL
"""
from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

NAME_RE = re.compile(r"^(?P<year>\d{4})\.(?P<month>\d{2})-(?P<channel>alpha|beta|rc)\.(?P<n>[1-9]\d*)$")


def read_properties(path: Path) -> dict[str, str]:
    props: dict[str, str] = {}
    for raw in path.read_text(encoding="utf-8").splitlines():
        line = raw.strip()
        if not line or line.startswith("#") or "=" not in line:
            continue
        key, _, value = line.partition("=")
        props[key.strip()] = value.strip()
    return props


def check(root: Path, tag: str | None = None) -> list[str]:
    errors: list[str] = []
    props = read_properties(root / "version.properties")
    for key in ("BUILD_LABEL", "VERSION_NAME", "VERSION_CODE", "RELEASE_CHANNEL"):
        if key not in props:
            errors.append(f"version.properties thiếu {key}")
    if errors:
        return errors

    name = props["VERSION_NAME"]
    match = NAME_RE.match(name)
    if not match:
        return [f"VERSION_NAME {name!r} không đúng dạng YYYY.MM-<alpha|beta|rc>.<N>"]

    year, month, channel, n = int(match["year"]), int(match["month"]), match["channel"], int(match["n"])
    if not 1 <= month <= 12:
        errors.append(f"tháng {month} không hợp lệ trong VERSION_NAME")
    if props["RELEASE_CHANNEL"] != channel:
        errors.append(f"RELEASE_CHANNEL={props['RELEASE_CHANNEL']!r} không khớp kênh trong VERSION_NAME ({channel!r})")

    expected_label = f"{year}.{month:02d} {channel}-{n}"
    if props["BUILD_LABEL"] != expected_label:
        errors.append(f"BUILD_LABEL={props['BUILD_LABEL']!r}, mong đợi {expected_label!r}")

    try:
        code = int(props["VERSION_CODE"])
    except ValueError:
        errors.append(f"VERSION_CODE không phải số nguyên: {props['VERSION_CODE']!r}")
    else:
        base = year * 1_000_000 + month * 10_000 + n * 100
        if not base <= code < base + 100:
            errors.append(f"VERSION_CODE={code} phải nằm trong [{base}, {base + 99}] cho {name}")
        if code > 2_100_000_000:
            errors.append("VERSION_CODE vượt giới hạn Google Play (2.100.000.000)")

    changelog = root / "CHANGELOG.md"
    if not changelog.is_file() or f"## [{name}]" not in changelog.read_text(encoding="utf-8"):
        errors.append(f"CHANGELOG.md chưa có mục '## [{name}]'")
    notes = root / "docs" / "releases" / name / "RELEASE_NOTES.md"
    if not notes.is_file():
        errors.append(f"thiếu {notes.relative_to(root).as_posix()}")

    if tag is not None and tag != f"v{name}":
        errors.append(f"tag {tag!r} không khớp VERSION_NAME (mong đợi 'v{name}')")
    return errors


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description="Kiểm tra phiên bản Last Outpost")
    parser.add_argument("--root", default=".", type=Path)
    parser.add_argument("--tag", default=None)
    parser.add_argument("--github-env", action="store_true", help="in VERSION_NAME/VERSION_CODE/BUILD_LABEL dạng KEY=VALUE")
    args = parser.parse_args(argv)

    errors = check(args.root, args.tag)
    if errors:
        for message in errors:
            print(f"ERROR {message}", file=sys.stderr)
        return 1

    props = read_properties(args.root / "version.properties")
    if args.github_env:
        for key in ("VERSION_NAME", "VERSION_CODE", "BUILD_LABEL"):
            print(f"{key}={props[key]}")
    else:
        print(f"version: {props['BUILD_LABEL']} ({props['VERSION_NAME']}, code {props['VERSION_CODE']}) — OK")
    return 0


if __name__ == "__main__":
    sys.exit(main())
