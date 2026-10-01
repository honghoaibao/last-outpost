# Last Outpost — 2026.09 beta-1

Repo triển khai **Beta 01** của LAST OUTPOST — *Stylized 2.5D Cartoon + Post-Apocalyptic Adventure + Tactical Strategy*.
Nguồn thiết kế: [`docs/design/`](docs/design/) (bộ *Clean Design & Production Bundle v1.0*, giữ nguyên bản gốc).

> **Trạng thái:** `2026.09-beta.1` là **nền móng** (foundation) của Beta 01: khung mô phỏng, pipeline content, CI/CD và hệ thống tài liệu.
> Chưa phải bản chơi được. Tiến độ theo từng hạng mục P0: [`docs/production/BETA_01_STATUS.md`](docs/production/BETA_01_STATUS.md).

## Cấu trúc

```text
last-outpost/
├── .github/                 # CI (ci.yml), build/release beta (release-beta.yml), issue/PR template, dependabot
├── app/                     # Android app (Kotlin + Jetpack Compose) — hiện là shell + nút chạy thử mô phỏng
├── core/                    # Mô phỏng gameplay THUẦN Kotlin/JVM (không phụ thuộc Android) — test được trên CI
├── content/                 # Dữ liệu content data-driven (12 loài quái, mission mẫu, manifest)
├── tools/                   # validate_content.py · verify_docs.py · check_version.py (+ tests)
├── docs/
│   ├── design/              # NGUỒN THIẾT KẾ (00_START_HERE … 04_VISUAL_REFERENCE) — chỉ sửa theo Controlled Change
│   ├── history/             # Lịch sử: legacy-archive/, bundle-v1.0/ (hồ sơ tiếp nhận), changelog/
│   ├── decisions/           # ADR — quyết định kỹ thuật
│   ├── open-questions/      # OPEN QUESTION còn treo (owner + status)
│   ├── production/          # Trạng thái Beta 01, nợ kỹ thuật, hướng dẫn build/release
│   ├── releases/            # Release notes theo phiên bản
│   ├── playtest/            # Báo cáo playtest
│   └── balance/             # Kết quả/bảng balance
├── version.properties       # Nguồn phiên bản duy nhất
└── CHANGELOG.md
```

## Bắt đầu nhanh

Yêu cầu: JDK 17, Android SDK (compileSdk 36), Python 3.10+.

```bash
./gradlew :core:test                                   # test mô phỏng (không cần emulator)
./gradlew :app:assembleDebug                           # APK debug → app/build/outputs/apk/debug/
python3 tools/validate_content.py                      # kiểm tra content + legacy guard
python3 tools/verify_docs.py                           # docs/design khớp manifest
python3 tools/check_version.py                         # version.properties · CHANGELOG · release notes nhất quán
python3 -m unittest discover -s tools/tests -t tools   # test của tools
```

## Phiên bản

`2026.09 beta-1` → `VERSION_NAME=2026.09-beta.1`, `VERSION_CODE=2026090101`, git tag `v2026.09-beta.1`.
Quy ước: [`docs/decisions/0002-version-scheme.md`](docs/decisions/0002-version-scheme.md).

## Build bằng GitHub Actions

| Workflow | Khi nào | Làm gì |
|---|---|---|
| `ci.yml` | push `main`, mọi PR | verify docs/content/version → test `:core` → lint + build APK debug (artifact) |
| `release-beta.yml` | push tag `v*-beta.*` hoặc chạy tay | build APK debug (+ APK/AAB release nếu có secrets ký) → artifact → GitHub Release (prerelease) |

Chi tiết secrets và quy trình phát hành: [`docs/production/BUILD_AND_RELEASE.md`](docs/production/BUILD_AND_RELEASE.md).

## Quy tắc khi triển khai (rút từ `docs/design/00_START_HERE/CLAUDE_READ_FIRST.md`)

1. Đọc theo thứ tự: `CURRENT_DECISIONS → CORE_DESIGN → GAMEPLAY_MASTER_MAP → BETA_01_SCOPE → domain docs`.
2. Làm **P0 trước**; P1 bị gate; P2 không thuộc Beta.
3. `docs/history/legacy-archive/` chỉ là lịch sử — không khôi phục nếu chưa có quyết định thiết kế rõ ràng (CI có *legacy guard*).
4. Thiếu rule → **không đoán**: ghi vào [`docs/open-questions/`](docs/open-questions/OPEN_QUESTIONS.md) và dừng ở decision boundary.
