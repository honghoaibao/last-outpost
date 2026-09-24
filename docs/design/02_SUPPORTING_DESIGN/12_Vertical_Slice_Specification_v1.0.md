# LAST OUTPOST — VERTICAL SLICE SPECIFICATION v1.0

## 0. MỤC ĐÍCH

Vertical Slice là một phiên bản **nhỏ nhưng hoàn chỉnh** của Last Outpost, dùng để chứng minh rằng:

- Core gameplay thực sự hoạt động.
- Visual direction mới hoạt động đúng trên màn hình thật.
- UX Flow liền mạch.
- Progression có cảm giác phát triển.
- Outpost tạo được cảm giác “đây là căn cứ của mình”.
- Combat có chiều sâu nhưng vẫn đọc được.
- Trận đấu nằm trong chuẩn **5–15 phút**.
- Toàn bộ slice có thể được dùng để kiểm tra chất lượng trước khi scale lên hàng chục/hàng trăm mission.

Nguyên tắc:

> **Vertical Slice không phải bản game đầy đủ. Nó là một lát cắt hoàn chỉnh đại diện cho chất lượng cuối cùng.**

---

# I. VISUAL TARGET

## Visual Identity

> **Stylized 2.5D Cartoon + Post-Apocalyptic Adventure + Tactical Strategy**

Các đặc điểm bắt buộc:
- cartoon stylized;
- 2.5D isometric;
- friendly post-apocalypse;
- chunky readable shapes;
- expressive characters;
- colorful nhưng có kiểm soát;
- scrap-tech;
- battlefield readability cao;
- UI sạch, hiện đại, có chút industrial personality.

Không:
- gritty military simulator;
- full chibi;
- fantasy RPG;
- photorealistic;
- generic mobile card UI;
- gore/horror-first.

---

# II. SLICE SCOPE

## 1. Outpost

Một Outpost nhỏ nhưng hoàn chỉnh.

Có:
- Command Center;
- Armory;
- Engineering/Workshop;
- Logistics;
- Research;
- Habitat;
- Expedition;
- khu trang trí cơ bản.

Progression hiển thị tối thiểu từ:
> Survivor Camp → Fortified Outpost.

Chưa cần triển khai đủ 5 stage.

---

## 2. Campaign

Một region:

> **Ruined City**

Một chapter:

> **Chapter 1 — First Stronghold**

### Mission

5 mission:

| Mission | Mục tiêu chính | Roster | Độ dài |
|---|---|---|---|
| 01 | Sống sót và học xây Tower | Crawler | 5–7 phút |
| 02 | Xử lý Swarm + Acid | Crawler, Acid Bloater | 6–8 phút |
| 03 | Counter Armor | Crawler, Behemoth | 7–10 phút |
| 04 | Logistics + EMP | Crawler, EMP Stalker, Acid Bloater | 8–11 phút |
| 05 | Boss Defense | Crawler, Behemoth, Siren + Colossus Titan | 10–15 phút |

Các mission dùng **Fixed Enemy Roster**.
Không spawn species ngoài roster của mission.

---

# III. CORE PLAYER FLOW

```text
Boot
↓
First-Time Tutorial / Returning Player
↓
Outpost
↓
Campaign
↓
Mission Briefing
↓
Loadout
↓
Prepare
↓
Combat
↓
Result
↓
Reward
↓
Upgrade / Research
↓
Outpost Growth
↓
Next Mission
```

Không có flow quan trọng nào yêu cầu người chơi hiểu hệ thống ngoài màn hình mà game chưa giới thiệu.

---

# IV. FIRST SESSION

## Bước 1 — Boot

Hiển thị:
- logo;
- short animated intro;
- Continue / New Game.

## Bước 2 — Tutorial

Giới thiệu theo từng lớp:
1. Chọn một Tower.
2. Đặt Tower.
3. Chọn mục tiêu.
4. Quan sát wave.
5. Hiểu Ammo.
6. Nhận reward.
7. Nâng cấp Outpost.

Không giải thích toàn bộ game ngay lần đầu.

---

# V. OUTPOST HOME

## Mục tiêu UX

Người chơi phải hiểu trong vài giây:

> “Đây là căn cứ của mình, mình đang phát triển nó.”

### Khu trung tâm

Command Center.

Hiển thị:
- Outpost stage;
- current objective;
- next unlock;
- campaign button.

### Armory

Dùng để:
- xem Tower;
- xem Barrel;
- xem Module;
- chuẩn bị Loadout.

### Workshop

Dùng để:
- Blueprint;
- Crafting;
- Mastery.

### Logistics

Dùng để:
- Ammo;
- Power;
- Water;
- Drone;
- Supply systems.

### Research

Dùng để:
- Tech Tree.

---

# VI. MISSION BRIEFING

Mission Briefing phải giải thích:

### Mục tiêu
Ví dụ:
> Survive all waves.

### Roster
Hiển thị rõ:
- Crawler
- Behemoth
- Siren

### Main Threat
Ví dụ:
> Heavy Armor + Speed Boost.

### Recommended Counter
Ví dụ:
> Crowd Control + Armor Break.

### Optional Objectives
Ví dụ:
- Base HP ≥ 70%.
- Không mất Kho Đạn.
- Hoàn thành dưới 10 phút.

Không bắt người chơi đọc một đoạn lore dài trước trận.

---

# VII. LOADOUT

## Tower Loadout

Slice có 6 tower/build archetype:

1. **Sentry Cannon**
   - single target.
   - ổn định.

2. **Glue Trap Tower**
   - slow.
   - anti swarm.

3. **Acid Tower**
   - armor reduction.
   - damage over time.

4. **Harpoon Tower**
   - pull/control.
   - anti heavy.

5. **Mechanical Nail Tower**
   - low-tech.
   - hoạt động khi EMP.

6. **Rail Sniper**
   - long-range.
   - high priority target.

Người chơi chỉ mang một subset giới hạn vào mission để tạo quyết định.

---

# VIII. MODULAR TOWER

Mỗi Tower dùng:

> **Base + Barrel + Module**

Slice chỉ cần một số module mẫu:

### Base
- Stable Base
- Cooling Base
- Mobile Base

### Barrel
- Standard Cannon
- Nail Barrel
- Acid Sprayer
- Harpoon

### Module
- Magnet Core
- Overclock Core
- Cooling Module
- Utility Pulse

Module không cần quá nhiều trong slice.

Mục tiêu là chứng minh:

> “Cùng một Tower có thể tạo các playstyle khác nhau.”

---

# IX. COMMANDER

Slice dùng 1 Commander:

## Engineer

Vai trò:
- repair;
- cooling;
- logistics support.

### Active Skills

**Emergency Repair**
- sửa một công trình trọng điểm.

**Overcool**
- giảm Heat của nhóm Tower trong vùng.

Skill phải có:
- cooldown;
- clear telegraph;
- animation;
- VFX;
- feedback.

---

# X. BATTLEFIELD

## Map

Một map Ruined City có:

- Main lane;
- Alternate lane;
- Elevated position;
- Supply route;
- chokepoint;
- Hazard area;
- Outpost/core area.

## Build Zones

Có:
- Ground Mount;
- elevated/wall mount ở mức thử nghiệm.

Không cần triển khai toàn bộ multi-surface system trong slice.

Mục tiêu:
> chứng minh 2.5D làm thay đổi cách đặt Tower.

---

# XI. COMBAT LOOP

Trong trận:

```text
Observe
→ Build
→ Prepare Logistics
→ Wave Begins
→ Counter
→ Adapt
→ Repair / Resupply
→ Boss / Escalation
→ Survive
```

Người chơi phải luôn có ít nhất một quyết định ý nghĩa.

Không để combat trở thành:
> build xong → ngồi chờ.

---

# XII. RESOURCE MODEL

Slice sử dụng:

### Ammo
Tower weapon supply.

### Power
Dành cho một số Tower/utility.

### Water
Cooling.

### Scrap
Build và reward.

Chưa cần đưa toàn bộ Economy vào battlefield.

---

# XIII. FIRST MISSION DESIGN

## Mission 01 — “First Night”

### Roster
- Crawler.

### Objective
- Survive all waves.

### Duration
- 5–7 phút.

### Teaching
- build;
- targeting;
- Ammo;
- basic logistics;
- resource feedback.

### Waves

**Wave 1**
- small Crawler group.

**Wave 2**
- larger group.

**Wave 3**
- lane pressure.

**Wave 4**
- mixed speed.

**Wave 5**
- final swarm.

No boss.

---

# XIV. MISSION 02 — “Acid Run”

### Roster
- Crawler.
- Acid Bloater.

### Main lesson
> Priority targeting.

Người chơi học:
- Acid Bloater phải được xử lý sớm.
- Đặt Sniper ở vị trí phù hợp.
- Không để Acid Bloater chết gần Tower quan trọng.

### Optional
- Không mất Tower.
- Base HP ≥ 80%.

---

# XV. MISSION 03 — “Break the Wall”

### Roster
- Crawler.
- Iron-Hide Behemoth.

### Main lesson
> Armor counter.

Người chơi học:
- Armor Break.
- Harpoon.
- Chokepoint.
- Heavy target priority.

### Duration
7–10 phút.

---

# XVI. MISSION 04 — “Blackout”

### Roster
- Crawler.
- Acid Bloater.
- EMP Stalker.

### Main lesson
> Redundancy / low-tech fallback.

Người chơi học:
- Power grid;
- EMP;
- Mechanical Tower;
- emergency response.

### Hazard
Một EMP event có telegraph rõ.

### Duration
8–11 phút.

---

# XVII. MISSION 05 — “The First Stronghold”

## Boss
**Colossus Titan**

### Roster
- Crawler;
- Iron-Hide Behemoth;
- Siren Screamer;
- Colossus Titan.

### Main lesson
> Combine all previous systems.

### Boss phases

#### Phase 1
March.

#### Phase 2
Damages defensive structure.

#### Phase 3
Threatens Tower displacement.

#### Phase 4
Final burst / weak-point window.

Boss không được là:
> một thanh HP khổng lồ.

Boss phải buộc người chơi:
- di chuyển;
- ưu tiên target;
- dùng Commander skill;
- quản lý logistics;
- thích nghi.

---

# XVIII. WAVE DESIGN

Wave phải có role:

- Tutorial wave.
- Pressure wave.
- Counter-check wave.
- Recovery wave.
- Escalation wave.
- Boss preparation.
- Boss wave.

Không tăng độ khó chỉ bằng:
> +300% HP.

Threat Budget dùng để điều khiển:
- số lượng;
- tốc độ;
- composition;
- timing;
- lane.

---

# XIX. OBJECTIVE SYSTEM

Mỗi mission có:

### Main
Survive / Defend.

### Secondary
1–3 objectives.

Ví dụ:
- Base HP ≥ 70%.
- Không mất Supply Depot.
- Destroy 5 Acid Bloater trước khi chúng chết gần Tower.
- Hoàn thành dưới X phút.

### Reward

Main:
- Scrap;
- Materials.

Optional:
- extra Scrap;
- Blueprint Fragment;
- Mastery XP.

---

# XX. RESULT SCREEN

Khi thắng:

```text
VICTORY
Wave 5/5
Time 06:42
Base HP 87%
Towers Lost 1
```

Hiển thị:
- reward;
- stars;
- objectives;
- mastery;
- blueprint fragments;
- next unlock.

### Defeat

Không chỉ nói:
> You lost.

Phải giải thích:

> **Why you lost**
> - Ammo shortage.
> - Behemoth reached the core.
> - Power outage.

Sau đó:

> **Suggested preparation**
> - Add Armor Break.
> - Add Mechanical Tower.

---

# XXI. PROGRESSION AFTER SLICE MISSION

Sau Mission 1:
- unlock module system.

Sau Mission 2:
- unlock Acid Tower.

Sau Mission 3:
- unlock Harpoon.

Sau Mission 4:
- unlock Mechanical Nail Tower.

Sau Mission 5:
- unlock first Research branch.

Mục tiêu:
> Mỗi mission thắng mở ra một cách chơi mới.

---

# XXII. OUTPOST GROWTH

Sau mỗi milestone:

### Mission 1–2
Survivor Camp.

### Mission 3
Fortified structure unlock.

### Mission 4
Workshop / Logistics expansion.

### Mission 5
Command Center upgrade.

Trụ Sở không reset.

---

# XXIII. MASTERy / BLUEPRINT

Slice cần chứng minh hai vòng:

### Mastery
Chơi Tower → nhận Mastery XP.

### Blueprint
Hoàn thành mission → nhận Blueprint Fragments.

Ví dụ:

```text
5 Fragments
→ unlock Utility Module
```

Không cần triển khai full blueprint library.

---

# XXIV. ECONOMY SLICE

Currencies:

- Scrap.
- Materials.
- Blueprint Fragment.
- Rad-Gems ở mức UI placeholder nếu chưa cần monetization test.

Không cho economy biến slice thành grind.

Mỗi mission phải cho đủ progression để:
> người chơi cảm thấy mình tiến lên sau một session.

---

# XXV. UI SCREENS REQUIRED

Vertical Slice phải có tối thiểu:

1. Boot.
2. Outpost.
3. Campaign.
4. Mission Briefing.
5. Loadout.
6. Prepare.
7. Combat HUD.
8. Tower Inspect.
9. Enemy Inspect.
10. Commander Ability.
11. Mission Result.
12. Upgrade.
13. Tech Tree.
14. Blueprint.
15. Settings.

Không cần full Shop/Guild/PvP trong slice đầu.

---

# XXVI. VISUAL ASSET SET

## Characters
- 1 Commander.
- 2–3 civilian silhouettes.

## Enemy
- Crawler.
- Acid Bloater.
- Behemoth.
- EMP Stalker.
- Siren.
- Colossus Titan.

## Towers
6 core archetypes.

## Environment
1 Ruined City biome.

## Outpost
2 visual stages.

## VFX
- projectile;
- hit;
- explosion;
- acid;
- slow;
- EMP;
- boss attack;
- repair;
- upgrade;
- victory.

---

# XXVII. AUDIO SET

Minimum:
- Outpost ambience.
- Combat loop.
- Boss music.
- 6 enemy audio identities.
- 6 tower firing/impact groups.
- UI clicks.
- Warning.
- Victory.
- Defeat.
- Upgrade.

---

# XXVIII. PERFORMANCE TARGET

Slice phải được profile trong:
- normal wave;
- peak swarm;
- Boss;
- multiple VFX;
- weather/hazard.

Target:
- 60 FPS trên target device khi điều kiện phù hợp.
- 30 FPS fallback trên thiết bị yếu hơn.
- Không có major frame spike do:
  - spawn;
  - pooling;
  - pathfinding;
  - VFX;
  - UI update.

1000+ enemy là mục tiêu của engine dài hạn; Vertical Slice chỉ cần chứng minh architecture có đường mở rộng.

---

# XXIX. ACCESSIBILITY

Tối thiểu:
- text readable;
- contrast;
- semantic colors có icon/shape hỗ trợ;
- vibration toggle;
- audio volume;
- reduced motion option nếu cần;
- no critical information communicated only through color.

---

# XXX. TELEMETRY

Slice cần tracking tối thiểu:

### Flow
- tutorial_complete;
- mission_start;
- mission_complete;
- mission_fail.

### Combat
- tower_built;
- enemy_spawn;
- enemy_killed;
- tower_destroyed;
- objective_complete.

### Progression
- upgrade;
- blueprint_unlock;
- mastery_gain.

### Technical
- FPS sample;
- frame spike;
- crash;
- save error.

---

# XXXI. PLAYTEST QUESTIONS

Testers phải có khả năng trả lời:

1. Bạn có biết mục tiêu của màn không?
2. Bạn có hiểu vì sao mình thua không?
3. Bạn có biết quái nào nguy hiểm nhất không?
4. Bạn có hiểu Tower khác nhau để làm gì không?
5. Bạn có cảm giác căn cứ đang phát triển không?
6. Sau khi thắng, bạn có biết mình vừa mở được gì không?
7. Trận có quá dài không?
8. UI có che battlefield không?
9. Hiệu ứng có làm khó nhìn không?
10. Bạn có muốn chơi thêm màn tiếp theo không?

---

# XXXII. ACCEPTANCE CRITERIA

Vertical Slice đạt khi:

- [ ] Core loop chơi được end-to-end.
- [ ] 5 mission hoàn chỉnh.
- [ ] Trận nằm trong 5–15 phút.
- [ ] Fixed roster hoạt động.
- [ ] Tower modular hoạt động.
- [ ] Commander hoạt động.
- [ ] Boss có phase.
- [ ] Outpost có progression.
- [ ] Reward → Upgrade → Next Mission hoạt động.
- [ ] Save/Load hoạt động.
- [ ] Visual direction nhất quán.
- [ ] UI/UX flow liền mạch.
- [ ] Performance profile có dữ liệu.
- [ ] Có telemetry.
- [ ] Không có blocker bug trong core flow.

---

# XXXIII. OUT OF SCOPE

Không đưa vào Vertical Slice đầu:

- Full Guild.
- Territory War.
- Complex PvP.
- Full Season system.
- Full monetization.
- 100+ missions.
- All 5 Outpost stages.
- All biomes.
- All 12 enemies nếu chưa cần cho slice.
- Full cosmetics.
- Complex diplomacy.
- Advanced live-event infrastructure.

Các phần này chỉ cần được chứng minh bằng design documents, không cần tồn tại đầy đủ trong slice.

---

# XXXIV. VERTICAL SLICE SUCCESS DEFINITION

Vertical Slice thành công khi người chơi có thể đi qua:

```text
Outpost
→ Mission
→ Preparation
→ 5–15 minute battle
→ Strategic decision
→ Victory/Defeat
→ Reward
→ Upgrade
→ Visible Outpost Growth
→ Next Mission
```

và cảm nhận rõ:

> **“Mình đang xây một căn cứ hậu tận thế của riêng mình, mỗi trận chiến làm nó phát triển, và những quyết định của mình thật sự ảnh hưởng đến trận đánh.”**

---

# XXXV. HANDOFF STATUS

Tài liệu này là cầu nối giữa:

`Design Documents → Visual References → Production / Implementation`

Nó chưa phải code specification.

Các con số gameplay trong slice được xem là:
> **Prototype / Vertical Slice values**

Chúng phải được playtest và balance trước khi trở thành giá trị production cố định.

