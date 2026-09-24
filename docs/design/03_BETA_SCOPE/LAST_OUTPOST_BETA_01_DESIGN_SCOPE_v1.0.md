# LAST OUTPOST — BETA 01 DESIGN & IMPLEMENTATION SCOPE v1.0

## 0. MỤC ĐÍCH
Đây là scope design để Claude triển khai **Beta 01 đầu tiên**. Mục tiêu là một bản chơi được, có chiều sâu thật, nhưng không cố nhồi toàn bộ launch/live-ops vào build đầu.

## 1. BETA SUCCESS CRITERIA
Beta 01 phải chứng minh:
1. Core PvE loop đủ vui trong 5–15 phút.
2. Người chơi có meaningful decisions trong combat.
3. Outpost tạo cảm giác phát triển và có hoạt động để khám phá.
4. Expedition/Intel/Workshop tạo replayability ngoài trận.
5. Guild/Social có lý do quay lại, không chỉ chat/menu.
6. PvP có vòng Attack → Result → Learn → Redesign.
7. Save/recovery/economy đủ an toàn để test thật.
8. UI/audio/visual feedback hỗ trợ tactical readability.

## 2. PRIORITY MATRIX
### P0 — MUST SHIP IN BETA 01
**Combat**
- Mission state flow.
- Build / Tower modular Base+Barrel+Module.
- Fixed enemy roster.
- Wave Director.
- Logistics, Ammo, Power, Water, Heat.
- Hazards theo mission.
- Commander core.
- Boss framework + ít nhất 1 Boss.
- Victory/Defeat/Result.

**Outpost / Progression**
- Outpost Stage 1→2→3 visible growth path.
- Command Center.
- Workshop + Prototype/Test Range.
- Research / Tech Tree.
- Expedition Board.
- Intel Archive.
- Limited Survivor Specialist assignment.
- Blueprint + Fragment.
- Mastery.

**World / Expedition**
- World Map node system.
- Recon/Intel reveal ở mức nhẹ.
- Expedition route/risk choice.
- Small incident/choice pool.

**Social**
- Guild creation/join.
- Guild HQ.
- Shared Tech.
- Shared Storage.
- Guild Projects.
- Guild Operations.
- Async Support.
- PvP Raid/Challenge Base snapshot + scout + result/replay summary.

**Platform / Safety**
- Save / cloud / recovery path.
- Entitlement Plus/Pro flags.
- Analytics core.

### P1 — BETA 01 EXPANSION / ENABLE AFTER P0 IS STABLE
- 2-player Co-op Defense prototype.
- Guild Defense role assignment prototype.
- Trade / Reputation refinement.
- More Expedition incident variety.
- More Outpost zones beyond Stage 3.

### P2 — POST-BETA / SCALE
- Full 5-stage Outpost breadth.
- Large campaign scale.
- All biome content.
- Full seasonal/live-op breadth.
- Complex social war systems.
- Territory War / siege / territory conquest.
- Large-scale live co-op beyond 2-player prototype.

## 3. BETA PLAYER JOURNEY
```text
First Launch
→ Onboarding
→ Survivor Camp
→ First Mission
→ First Reward
→ Workshop
→ First Prototype
→ Second/Third Mission
→ Research Choice
→ Expedition Prep
→ Expedition Result
→ Intel Unlock
→ Outpost Growth
→ Guild Unlock
→ Guild Activity
→ PvP Raid Unlock
```

## 4. OUTPOST DEPTH RULE
Outpost không phải menu hub phẳng. Mỗi unlocked function phải trả lời ít nhất một trong:
- **Discovery:** người chơi khám phá được gì?
- **Decision:** người chơi chọn gì?
- **Preparation:** lựa chọn đó cải thiện/trade-off cho hoạt động nào?

Nếu một UI screen chỉ hiển thị số liệu và có nút claim, không coi nó là một hoạt động gameplay độc lập.

## 5. WORKSHOP / TEST RANGE
### Goal
Cho người chơi “chơi” với modular Tower ngoài trận.

### Required
- chọn Blueprint;
- lắp Base + Barrel + Module;
- xem role/stat/trade-off;
- preview intended use;
- test against standard dummy scenarios;
- save/load preset.

### Not required in Beta 01
Full sandbox editor, custom enemy spawn scripting, infinite simulation.

## 6. EXPEDITION
### Player decisions
- site;
- team;
- loadout;
- route/risk.
### Incident
Tối đa một incident đáng kể trong một expedition entry ở Beta 01.
### Guardrail
Không tạo một RPG stat system riêng cho Expedition.

## 7. INTEL / DISCOVERY
Intel types:
- Enemy;
- Biome;
- Hazard;
- Boss;
- Special Site.

Intel phải hữu ích khi quay lại Mission/Expedition, không chỉ là lore text.

## 8. SURVIVOR SPECIALISTS
Beta 01 dùng roster nhỏ.
Mỗi Specialist có:
- Role;
- một passive utility rõ;
- assignment location;
- availability/injury state nếu cần.

Không thêm hunger/thirst micro-management riêng cho từng survivor.

## 9. GUILD OPERATIONS
Một Operation phải có:
- clear objective;
- contribution của thành viên;
- progress chung;
- personal contribution feedback;
- group reward;
- completion history.

Tránh kill-count-only objective trừ khi nó là một phần nhỏ của operation.

## 10. PVP RAID
Flow:
```text
Select Opponent
→ Scout / Intel
→ Prepare Loadout
→ Raid Snapshot
→ Result
→ Battle Summary
→ Learn / Redesign Defense
```
Không có phá hủy vĩnh viễn tài sản. Cạnh tranh dùng normalization theo Current Decisions.

## 11. 2-PLAYER CO-OP DEFENSE — P1
Đây là prototype multiplayer thực sự, nhưng không blocking Beta 01 core.

Player A/B:
- cùng objective;
- battlefield chung;
- Commander riêng;
- phần setup/budget được xác định rõ;
- cần phối hợp lane/resource/threat.

MVP success: người chơi phải có ít nhất 3 lần buộc phải phối hợp/đáp ứng vai trò của nhau trong một trận.

## 12. UI / AUDIO / VISUAL PRIORITY
Trong Beta 01, readability > spectacle.
P0 feedback:
- enemy special telegraph;
- hazard telegraph;
- logistics shortage;
- heat/Power/Water warning;
- Core Critical;
- Commander ability readiness;
- Guild/PvP result clarity.

## 13. ECONOMY BOUNDARIES
Không thêm currency mới ngoài những loại đã có trong Economy source-of-truth, trừ khi một proposal được review riêng.
Event currency chỉ xuất hiện khi event framework được bật.

## 14. DEFINITION OF DONE — BETA 01 DESIGN
- [ ] Core mission playable end-to-end.
- [ ] Outpost has meaningful interaction, not only menus.
- [ ] Workshop/Test Range works as decision/prototyping activity.
- [ ] Expedition has route/risk choice and result feedback.
- [ ] Intel changes player preparation.
- [ ] Guild has cooperative purpose.
- [ ] PvP has scout/attack/learn/redesign loop.
- [ ] P1 multiplayer prototype is isolated behind scope gate.
- [ ] No legacy feature accidentally restored.
- [ ] All open questions have an explicit owner/status.
