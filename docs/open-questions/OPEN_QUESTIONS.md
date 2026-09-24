# OPEN QUESTIONS

Theo Claude Handoff v1.2 và Beta 01 DoD: mỗi câu hỏi có **owner + status**, và code phải dừng ở decision boundary thay vì đoán.
Owner là **đề xuất** — cần xác nhận. Status: `Open` · `Answered` · `Won't decide (deferred)`.

## A. Từ thiết kế (Gameplay Master Map §10, Changelog "Not silently changed")

| ID | Câu hỏi | Ảnh hưởng | Owner (đề xuất) | Status |
|---|---|---|---|---|
| OQ-DESIGN-01 | Commander skill kit chính xác (skill, cooldown, cost) | Commander core, `Command.UseAbility` | Design | Open |
| OQ-DESIGN-02 | Roster Tower cụ thể ngoài archetype (Base/Barrel/Module nào) | Workshop, Tower system, content `towers/` | Design | Open |
| OQ-DESIGN-04 | Giá trị normalization PvP | PvP Raid | Design | Open |
| OQ-DESIGN-05 | Giới hạn reward/economy của Co-op Defense (P1) | Co-op (P1) | Design | Open |
| OQ-DESIGN-06 | Pool incident của Expedition | Expedition | Design | Open |
| OQ-DESIGN-07 | Roster + bonus Specialist | Survivor Specialists | Design | Open |
| OQ-DESIGN-08 | Cân bằng số học chính xác (chi phí, damage, HP…) | Toàn bộ balance; playtest | Design + playtest | Open |

## B. Phát sinh khi triển khai — thiết kế

| ID | Câu hỏi | Ảnh hưởng | Owner (đề xuất) | Status |
|---|---|---|---|---|
| OQ-DESIGN-03 | UI/UX Spec §2.4 nói combat dùng **một orientation cố định** nhưng không nói landscape hay portrait. Lưu ý: app target API 36 **không khoá được orientation trên màn ≥ 600dp** (Android 16). | Layout combat, HUD, manifest | Design | Open |
| OQ-CONTENT-01 | Mission Tutorial: biome/region, mã region trong ID (`MISSION_RC_001` dùng "RC"), số wave, reward, mục tiêu. Hiện `MISSION_TUTORIAL_001` chỉ là placeholder kỹ thuật | Content pipeline, onboarding | Design | Open |
| OQ-CONTENT-02 | Chỉ số + behavior profile của 12 loài (hiện `stats: null`) | Enemy system, Wave Director, balance | Design | Open |

## C. Phát sinh khi triển khai — kỹ thuật (Technical Architecture không chốt)

| ID | Câu hỏi | Giả định đang dùng | Owner (đề xuất) | Status |
|---|---|---|---|---|
| OQ-TECH-01 | Engine/renderer cho combat 2.5D 1000+ enemy | Android native; `:core` thuần; renderer **chưa chọn** (ADR-0001). Phải chốt trước milestone Basic Renderer | Bảo (Tech) | Open |
| OQ-TECH-02 | `applicationId` / package chính thức | `com.lastoutpost.game` (tạm) — khó đổi sau khi publish | Bảo (Tech) | Open |
| OQ-TECH-03 | Backend cho Guild/PvP/Cloud Save/Entitlement (P0 cần PvP server-authoritative, tech doc chỉ nói "modular monolith") | Chưa có backend | Bảo (Tech) | Open |
| OQ-TECH-04 | Tick rate mô phỏng (tài liệu: 20–30/s) | 25/s (`SimConfig`) — đo trên thiết bị yếu để chốt | Bảo (Tech) | Open |
| OQ-TECH-05 | `minSdk` và mốc "thiết bị yếu" cho 30 FPS fallback | `minSdk = 26` | Bảo (Tech) | Open |
| OQ-TECH-06 | Kênh phân phối beta: APK qua GitHub Release hay Google Play (internal/closed testing) | GitHub Release (APK debug; release nếu có keystore) | Bảo (Tech) | Open |
