# Build & Release

## Cục bộ
```bash
./gradlew :core:test :app:assembleDebug
python3 tools/validate_content.py && python3 tools/verify_docs.py && python3 tools/check_version.py
```
Nếu `./gradlew` báo *permission denied* (giải nén từ zip trên Windows): `git update-index --chmod=+x gradlew` hoặc `chmod +x gradlew`.

## Phát hành một bản beta
1. Sửa `version.properties` (`BUILD_LABEL`, `VERSION_NAME`, `VERSION_CODE`, `RELEASE_CHANNEL`) — theo ADR-0002.
2. Thêm mục `## [<VERSION_NAME>] - <ngày>` vào `CHANGELOG.md`.
3. Tạo `docs/releases/<VERSION_NAME>/RELEASE_NOTES.md`.
4. `python3 tools/check_version.py` phải sạch.
5. Merge vào `main` (CI xanh), rồi: `git tag v<VERSION_NAME> && git push origin v<VERSION_NAME>`.
6. `release-beta.yml` build và tạo GitHub Release (prerelease) kèm `SHA256SUMS.txt`.

Chạy tay (Actions → *Release Beta* → *Run workflow*) chỉ tạo artifact, không tạo Release.

## Ký release (tuỳ chọn, khuyến nghị)
Không có secrets → workflow chỉ xuất **APK debug**. Muốn APK/AAB release đã ký, thêm Repository secrets:

| Secret | Nội dung |
|---|---|
| `LO_KEYSTORE_BASE64` | `base64 -w0 release.jks` |
| `LO_KEYSTORE_PASSWORD` | mật khẩu keystore |
| `LO_KEY_ALIAS` | alias khoá |
| `LO_KEY_PASSWORD` | mật khẩu khoá |

Tạo keystore: `keytool -genkeypair -v -keystore release.jks -alias lastoutpost -keyalg RSA -keysize 2048 -validity 10000`.
**Giữ keystore ngoài repo** (`.gitignore` đã chặn `*.jks`/`*.keystore`) và sao lưu — mất khoá = không nâng cấp được app đã phát hành.

## Google Play
Từ 31/08/2026 app mới phải target API 36 (đã cấu hình `targetSdk = 36`). Cần AAB đã ký + chốt `applicationId` (OQ-TECH-02).
