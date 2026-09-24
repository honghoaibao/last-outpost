## Mô tả
<!-- Thay đổi gì, vì sao. Liên kết tới tài liệu thiết kế / OPEN QUESTION liên quan. -->

## Phạm vi (Beta scope)
- [ ] P0  - [ ] P1 (đã được gate)  - [ ] Hạ tầng/CI/tài liệu  - [ ] Không thuộc Beta (P2) — cần lý do

## Kiểm tra tuân thủ thiết kế
- [ ] Không tự quyết định rule còn thiếu — đã ghi OPEN QUESTION (`docs/open-questions/`) nếu cần
- [ ] Không đổi terminology / core loop / roster / Tower structure / Outpost persistence
- [ ] Không khôi phục hệ thống legacy (territory war, siege, diplomacy, ...)
- [ ] Nếu sửa `docs/design/`: đã cập nhật version + changelog và chạy `python3 tools/verify_docs.py --update`

## Definition of Done (Claude Handoff v1.2)
- [ ] Player flow hoàn chỉnh · [ ] feedback đọc được · [ ] state lưu đúng · [ ] edge case phục hồi được
- [ ] telemetry hook (nếu cần) · [ ] giữ nguyên design intent

## Đã chạy cục bộ
- [ ] `./gradlew :core:test`  - [ ] `python3 tools/validate_content.py`  - [ ] `python3 -m unittest discover -s tools/tests -t tools`
