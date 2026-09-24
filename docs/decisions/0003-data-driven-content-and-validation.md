# ADR-0003: Content data-driven (JSON) + validator trong CI

- **Status:** Accepted
- **Ngày:** 2026-09-24
- **Liên quan:** Technical Architecture §VI, §XXV, §XXXII; Content Production Pack §II–III

## Bối cảnh
Thiết kế yêu cầu: content data-driven; ID ổn định, human-readable, không chứa version; status Draft → … → Released;
CI phải bắt duplicate ID, missing reference, invalid roster, missing reward, dependency cycle, schema mismatch.

## Quyết định
- Content là JSON trong `content/` (`enemies/`, `missions/`, `manifest.json`), `schemaVersion = 1` (**bản nháp schema**, có thể đổi trước khi có mission thật).
- Validator viết bằng **Python (chỉ stdlib)** — `tools/validate_content.py` — độc lập engine/renderer, chạy được trước cả khi Gradle sẵn sàng.
- **Không tự bịa số liệu**: chỉ số quái, reward, wave… chưa có nguồn canon → `null` + status `Draft` (cảnh báo, không lỗi).
  Từ status `Prototype` trở lên các trường này bắt buộc (lỗi). Kênh `production` chỉ nhận `Approved`/`Released`.
- Content được đóng gói vào assets của app (`app/build.gradle.kts`).

## Hệ quả
- (+) Thêm content = thêm dữ liệu, không thêm class (Tech Architecture §XXXVI).
- (−) Chưa có content loader phía Kotlin, chưa có checksum/dependency giữa content pack (TD-05, TD-06).
