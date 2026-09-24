# ADR-0004: Khoá `docs/design` bằng checksum (Controlled Change)

- **Status:** Accepted
- **Ngày:** 2026-09-24
- **Liên quan:** Master Design Index §4, Documentation Audit §4, Claude Handoff "Design change rule"

## Quyết định
`docs/design/` là source-of-truth và được khoá bằng `docs/design/DESIGN_SHA256SUMS.txt`.
`tools/verify_docs.py` chạy trong CI; sửa/thêm/xoá file mà không cập nhật manifest → CI đỏ.
Người sửa phải cập nhật version + changelog (`docs/history/changelog/`) rồi chạy `--update`.

## Hệ quả
- (+) Không ai (kể cả AI) "lặng lẽ" đổi thiết kế trong lúc code.
- (−) Thêm một bước khi cố ý sửa thiết kế — đúng tinh thần Controlled Change.
- Lịch sử/legacy nằm ngoài `docs/design/` nên sửa thoải mái không bị khoá (nhưng quy ước là không sửa).
