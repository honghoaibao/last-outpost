# ADR-0002: Quy ước phiên bản `YYYY.MM-<kênh>.<N>`

- **Status:** Accepted
- **Ngày:** 2026-09-24

## Quyết định
Phiên bản hiển thị của "2026.09 beta-1":

| Trường | Giá trị | Quy tắc |
|---|---|---|
| `BUILD_LABEL` | `2026.09 beta-1` | `YYYY.MM <kênh>-<N>` — dùng trong UI/release title |
| `VERSION_NAME` | `2026.09-beta.1` | `YYYY.MM-<alpha\|beta\|rc>.<N>` |
| `VERSION_CODE` | `2026090101` | `YYYY*1_000_000 + MM*10_000 + N*100 + patch` (patch 0–99) — luôn tăng đơn điệu, < 2,1 tỷ |
| Git tag | `v2026.09-beta.1` | `v<VERSION_NAME>` |
| Thư mục release notes | `docs/releases/2026.09-beta.1/` | tên = `VERSION_NAME` |

Nguồn duy nhất: `version.properties`. `tools/check_version.py` (chạy trong CI) kiểm tra nhất quán với `CHANGELOG.md`, release notes và tag.

## Hệ quả
- Hotfix trong cùng beta tăng `patch` của `VERSION_CODE` (vd `2026090102`) mà không đổi `VERSION_NAME`.
- Beta thứ hai của tháng: `2026.09-beta.2` → `VERSION_CODE` 2026090201.
