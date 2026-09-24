# Nợ kỹ thuật, giả định, rủi ro — 2026.09-beta.1

| ID | Vấn đề | Mức | Hướng xử lý |
|---|---|---|---|
| TD-01 | **Gradle/Android chưa được build thật trước khi bàn giao.** Môi trường soạn thảo không truy cập được Maven/Google repo. Đã xác minh: `:core` biên dịch (Kotlin 2.2.21, không warning) và 19 test chạy đúng qua runner tạm; toàn bộ tool Python và workflow (actionlint) chạy sạch. **Chưa** xác minh: cấu hình AGP/Compose, `lintDebug`, `assembleDebug`. Lần chạy CI đầu tiên có thể cần chỉnh nhỏ (phiên bản thư viện, lint) | Cao | Chạy `ci.yml` lần đầu và sửa theo log; sau đó cập nhật dòng này |
| TD-02 | `gradle-wrapper.jar` lấy từ Gradle 8.9.0, `distributionUrl` trỏ 8.14.3 (vẫn hợp lệ) | Thấp | `./gradlew wrapper --gradle-version 8.14.3` để đồng bộ |
| TD-03 | R8/minify tắt ở release (chưa kiểm chứng trên thiết bị) | Trung bình | Bật + thêm rule trước RC |
| TD-04 | Icon launcher là placeholder | Thấp | Thay theo Asset Production Specification |
| TD-05 | Content manifest chưa có checksum + resolve dependency giữa pack (Tech Arch §XXV) | Trung bình | Khi có ≥ 2 content pack |
| TD-06 | Chưa có content loader phía Kotlin (app mới đếm file trong assets) | Trung bình | Milestone Content Loader (sau khi chốt schema) |
| TD-07 | `Ledger` chỉ in-memory; chưa Room/cloud/versioning/recovery (Save Data Design §XII–XV) | Cao (P0) | Milestone Save/Cloud |
| TD-08 | Legacy guard là regex theo từ khoá — có thể dương tính giả | Thấp | Cho phép allowlist khi cần |
| TD-09 | Beta-1 phát hành bằng **APK debug** (ký bằng debug key) khi chưa có keystore → không nâng cấp đè lên bản release ký khác | Trung bình | Cấu hình secrets ký (BUILD_AND_RELEASE.md) |
| TD-10 | Phiên bản thư viện (AGP 8.13.2, Kotlin 2.2.21, Compose BOM 2025.10.01, …) chọn theo hiểu biết lúc soạn, chưa build | Trung bình | Dependabot đề xuất bump; xác nhận ở lần CI đầu |
| TD-11 | `tools/tests` in nhiều dòng log; `validate_content` in 13 cảnh báo "Draft" mỗi lần chạy | Thấp | Gộp cảnh báo khi content lớn hơn |

## Giả định đã ghi
`applicationId`, `minSdk`, tick rate, renderer, backend, kênh phân phối → xem `docs/open-questions/OPEN_QUESTIONS.md` mục C.
