# Tài liệu Last Outpost

| Thư mục | Nội dung | Ai được sửa |
|---|---|---|
| [`design/`](design/) | **Source of truth** thiết kế (bundle v1.0, nguyên bản). Bắt đầu tại `design/00_START_HERE/CLAUDE_READ_FIRST.md` | Chỉ qua Controlled Change (xem dưới) |
| [`history/`](history/) | Lịch sử: bản legacy, hồ sơ tiếp nhận bundle, changelog thiết kế | Chỉ thêm, không sửa lịch sử |
| [`decisions/`](decisions/) | ADR — quyết định kỹ thuật (engine, phiên bản, content pipeline, quản lý docs) | Thêm ADR mới; ADR cũ đổi status |
| [`open-questions/`](open-questions/) | OPEN QUESTION — rule/quyết định còn thiếu, có owner + status | Cập nhật khi có câu trả lời |
| [`production/`](production/) | Trạng thái Beta 01, nợ kỹ thuật, build & release | Team triển khai |
| [`releases/`](releases/) | Release notes mỗi phiên bản (`<VERSION_NAME>/RELEASE_NOTES.md`) | Mỗi lần phát hành |
| [`playtest/`](playtest/) | Báo cáo playtest | Sau mỗi phiên playtest |
| [`balance/`](balance/) | Bảng/kết quả balance | Sau mỗi vòng balance |

## Thứ tự quyền lực của tài liệu thiết kế
`Current Decisions → Core Design → Beta 01 Scope → Domain docs → Bibles → Visual Reference → Playtest/Production → Legacy`
(chi tiết: `design/01_CANONICAL/LAST_OUTPOST_MASTER_DESIGN_INDEX_v1.2.md`).

## Controlled Change cho `docs/design/`
`docs/design/DESIGN_SHA256SUMS.txt` khoá nội dung tài liệu thiết kế; CI thất bại nếu file bị sửa/thêm/xoá vô tình.
Khi thay đổi thiết kế có chủ đích: (1) sửa source-of-truth, (2) cập nhật tài liệu downstream, (3) nâng version + ghi changelog
vào `history/changelog/`, (4) chạy `python3 tools/verify_docs.py --update`, (5) rà lại conflict.
Ghi chú: hồ sơ `design/00_START_HERE/SHA256SUMS.txt` là bản gốc của bundle (gồm cả mục legacy) — giữ nguyên làm chứng cứ.
