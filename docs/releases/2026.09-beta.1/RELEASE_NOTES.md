# Last Outpost 2026.09 beta-1 — nền móng Beta 01

**Đây là bản nền móng, chưa phải bản chơi được.** Mục tiêu: có repo, mô phỏng lõi, pipeline content, CI/CD và hệ thống tài liệu
để triển khai Beta 01 theo đúng thiết kế (`docs/design/`).

## Có gì
- `:core` — mô phỏng Kotlin/JVM thuần: fixed tick 25/s, vòng đời trận, pipeline 13 stage, hàng đợi Command,
  Wave Director cưỡng chế **Fixed Enemy Roster**, sổ cái giao dịch idempotent.
- `content/` — 12 loài quái (Draft, chưa có chỉ số), 1 mission placeholder, manifest; validator trong CI (kèm legacy guard).
- App Android: màn hình khởi động hiển thị phiên bản, số file content đóng gói và nút **Chạy thử mô phỏng**.
- CI + release bằng GitHub Actions; tài liệu, lịch sử, ADR, open questions.

## Chưa có
Tower/Enemy/Combat thật, renderer, Outpost, Expedition, Guild, PvP, Save/Cloud, Entitlement, Analytics pipeline.
Xem `docs/production/BETA_01_STATUS.md`.

## Vấn đề đã biết
- Cấu hình Gradle/Android chưa được build thật trước khi bàn giao (TD-01) — lần chạy CI đầu có thể cần chỉnh nhỏ.
- Nếu không có keystore, bản này là **APK debug**; cài đè lên bản release ký khác sẽ không được.
- Giả định kỹ thuật chờ xác nhận: `applicationId`, `minSdk`, tick rate, renderer, backend (`docs/open-questions/`).

## Cài đặt
Tải `LastOutpost-2026.09-beta.1-debug.apk` từ mục Assets, kiểm tra `SHA256SUMS.txt`, cài trên Android 8.0 (API 26) trở lên.
