# Changelog

Định dạng theo [Keep a Changelog](https://keepachangelog.com/vi/1.1.0/). Phiên bản theo [`docs/decisions/0002-version-scheme.md`](docs/decisions/0002-version-scheme.md).
Changelog của **thiết kế** nằm riêng ở [`docs/history/changelog/`](docs/history/changelog/).

## [2026.09-beta.1] - 2026-09-24

Bản nền móng (foundation) cho Beta 01 — chưa phải bản chơi được.

### Thêm
- Cấu trúc repo: `app/` (Android, Kotlin + Compose), `core/` (mô phỏng thuần Kotlin/JVM), `content/`, `tools/`, `docs/`.
- `:core` — fixed-tick clock (20–30 tick/s), vòng đời `BattleWorld` (8 phase), pipeline 13 stage đúng Tech Architecture §IV,
  hàng đợi Command, `WaveDirector` cưỡng chế **Fixed Enemy Roster**, `Ledger` giao dịch idempotent/nguyên tử. 19 unit test.
- `content/` — 12 loài quái (10 species + 2 boss) ở trạng thái Draft (chưa có chỉ số — OPEN), 1 mission placeholder, manifest.
- `tools/` — `validate_content.py` (schema, ID, roster, reference, cycle, reward, kênh production, legacy guard),
  `verify_docs.py` (bảo vệ `docs/design`), `check_version.py`. 34 test Python.
- GitHub Actions: `ci.yml`, `release-beta.yml` (ký release khi có secrets), Dependabot, issue/PR template.
- Hệ thống tài liệu: `docs/design` (nguyên bản), `docs/history`, `docs/decisions` (ADR), `docs/open-questions`,
  `docs/production`, `docs/releases`, `docs/playtest`, `docs/balance`.

### Ghi chú
- Toàn bộ nội dung `docs/design/` giữ nguyên bản từ bundle `LAST_OUTPOST_BETA_01_CLEAN_DESIGN_PRODUCTION_BUNDLE_v1_0.zip`.
- Các giả định kỹ thuật chưa được thiết kế chốt (engine/renderer, applicationId, minSdk, tick rate, backend) được ghi ở
  [`docs/open-questions/OPEN_QUESTIONS.md`](docs/open-questions/OPEN_QUESTIONS.md).
