# LAST OUTPOST — CURRENT DECISIONS v1.2

## PURPOSE
Đây là bảng quyết định hiện hành cấp cao. Claude phải dùng tài liệu này cùng Master Design Index trước khi triển khai hoặc thay đổi design.

## 1. CORE IDENTITY — LOCKED
- Visual: **Stylized 2.5D Cartoon + Post-Apocalyptic Adventure + Tactical Strategy**.
- Tone: friendly apocalypse, expressive, chunky, readable, hopeful; không realistic gritty, horror-first, fantasy RPG hay generic mobile card UI.
- Core loop: **BUILD → PREPARE → SURVIVE → ADAPT → GROW → TRY AGAIN**.
- Mission target: **5–15 phút**.
- Enemy selection: **Fixed Enemy Roster per Mission**.
- Difficulty đến từ composition, timing, terrain, hazards, logistics, counterplay và resource pressure; không dựa chủ yếu vào stat inflation.

## 2. BATTLE — LOCKED
- Tower formula: **BASE + BARREL + MODULE**.
- Combat phải có meaningful decisions sau khi trận bắt đầu; không được “build xong rồi ngồi chờ”.
- Resources trong trận có vai trò chiến thuật: Scrap, Ammo, Power, Water; Heat/Logistics/Hazards tạo áp lực có thể đọc và counter.
- 12 enemy species là toàn roster cấp game; mỗi Mission chỉ dùng roster con đã định trước.
- Boss phải tạo phase/decision, không phải HP sponge.
- Tactical ability và Commander có vai trò intervention, không thay thế Tower/Logistics.

## 3. OUTPOST / PROGRESSION — LOCKED
- Outpost là progression **permanent**; Season không reset core progression.
- Permanent: Outpost, unlocked buildings, Tech Tree, Blueprint sở hữu, Tower/Module sở hữu, Commander, Mastery, Achievements, Story unlocks, Cosmetics.
- Seasonal reset/rotation chỉ áp dụng cho dữ liệu thực sự temporary như challenge progress, seasonal leaderboard/event currency/progress.
- Outpost Evolution: Survivor Camp → Fortified Outpost → Industrial Outpost → Advanced Fortress → Last Bastion.
- Tech Tree có 4 nhánh: Firepower / Logistics / Survival / Military.
- Blueprint có đường kiếm bằng gameplay; Fragment không thay thế gameplay mà làm progress rõ ràng.

## 4. OUT-OF-MATCH DEPTH — BETA LOCK
Beta đầu tiên phải có hoạt động có gameplay meaning ngoài trận, không chỉ menu/claim:
1. **Outpost Functional Spaces:** Command Center, Workshop/Test Range, Research, Logistics, Expedition Board, Intel Archive; Survivor Quarter ở mức nhẹ.
2. **Workshop / Prototype:** lắp Base + Barrel + Module và xem trade-off/build identity trước khi đưa build vào mission.
3. **Expedition 2-step depth:** chọn team/loadout + route/risk; expedition có incident/decision ngắn thay vì chỉ chờ reward.
4. **Discovery / Intel:** World Map có vùng/knowledge chưa biết hoàn toàn; Recon/Expedition có thể mở Intel; Enemy/Biome/Hazard Codex tạo knowledge dùng lại trong gameplay.
5. **Survivor Specialists:** assign survivor vào Logistics / Research / Expedition / Repair / Guard; không mở micro-sim needs phức tạp.
6. **Visible Outpost Growth:** progression phải xuất hiện trong không gian, không chỉ bằng level number.

## 5. SOCIAL / MULTIPLAYER — BETA LOCK
PvE vẫn là priority cao nhất. Multiplayer mở rộng theo 3 dạng: **Support / Cooperation / Competition**.

### Beta P0
- Guild HQ.
- Guild shared progression ở mức gọn: Shared Tech / Shared Storage / Guild Projects.
- Guild Operations có mục tiêu gameplay, không chỉ kill-count.
- Async Support: gửi hỗ trợ tài nguyên/fragment/supply/defense contribution theo giới hạn economy.
- PvP Raid/Challenge Base: defensive snapshot, attacker scout/intel, battle result/replay summary, defender redesign loop.

### Beta P1 (prototype sau P0, không blocking core PvE beta)
- **2-player Co-op Defense**: một battlefield chung, mỗi người có Commander/loadout/việc phải làm riêng, shared objective; scope giới hạn.
- Guild seasonal defense có role assignment (Defender / Logistics / Support / Recon) ở mức prototype.

### Explicitly NOT restored from legacy
- Hex-grid territory war.
- 24h siege.
- Nuclear war monuments.
- Persistent territory conquest.
- Complex diplomacy/war map.
- MMO/open-world guild structure.

## 6. ECONOMY / MONETIZATION — LOCKED
- Free → Plus → Pro.
- Plus/Pro chủ yếu QoL + tactical options; lợi thế PvE nhỏ, có trần.
- PvP competitive phải normalization/giới hạn stat nhạy cảm.
- Không bán core Tower/Blueprint power độc quyền.
- Không thêm monetization system mới trong Beta chỉ vì cơ hội doanh thu.

## 7. PRODUCTION / CLAUDE — LOCKED
- Claude triển khai; design documents là source of truth.
- Không tự ý đổi terminology, core loop, roster, Outpost persistence, Tower structure, visual identity hoặc PvE priority.
- Khi thiếu rule: ghi OPEN QUESTION và dừng ở decision boundary; không tự đoán thành canon.
- Beta scope phải ưu tiên P0 trước P1.
