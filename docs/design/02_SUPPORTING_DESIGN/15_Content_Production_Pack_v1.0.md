# LAST OUTPOST — CONTENT PRODUCTION PACK v1.0

## 0. MỤC ĐÍCH

Content Production Pack là bộ template và quy trình chuẩn để tạo content mới cho Last Outpost sau khi Vertical Slice đã được xác nhận.

Mục tiêu:
- tạo content nhanh;
- giữ chất lượng nhất quán;
- data-driven;
- dễ review;
- dễ balance;
- dễ bàn giao;
- không phải viết logic riêng cho từng content;
- hỗ trợ scale từ vài mission lên hàng trăm mission.

Nguyên tắc:

> **Create → Validate → Playtest → Balance → Polish → Approve → Release**

---

# I. CONTENT HIERARCHY

```text
World
└── Region
    └── Biome
        └── Chapter
            └── Mission
                └── Wave
                    └── Spawn Group
```

Content meta:

```text
Enemy
Tower
Module
Commander
Boss
Hazard
Objective
Reward
Event
Expedition
Cosmetic
```

---

# II. CONTENT ID SYSTEM

Mỗi asset phải có ID ổn định.

Format đề xuất:

```text
BIOME_RUINEDCITY_01
MISSION_RC_001
ENEMY_CRAWLER
ENEMY_ACID_BLOATER
TOWER_SENTRY
MODULE_OVERCLOCK
BOSS_COLOSSUS
HAZARD_ACID_RAIN
EVENT_METEOR_01
```

Rules:
- unique;
- human-readable;
- không đổi ID chỉ vì đổi tên hiển thị;
- ID không chứa version.

Version nằm riêng:

```text
contentVersion
schemaVersion
```

---

# III. CONTENT STATUS

Mỗi content có một trạng thái:

```text
Draft
→ Internal Review
→ Prototype
→ Playtest
→ Balance
→ Art Polish
→ QA
→ Approved
→ Released
```

Không đưa Draft/Prototype vào production release.

---

# IV. MISSION PRODUCTION TEMPLATE

## Mission Header

```text
Mission ID:
Name:
Region:
Biome:
Chapter:
Mission Type:
Target Duration:
Recommended Power:
Unlock Requirement:
```

## Mission Type

- Standard
- Specialist
- Challenge
- Boss
- Tutorial
- Event

---

# V. MISSION DESIGN CARD

Mỗi mission phải có:

### 1. Fantasy / Theme
Người chơi đang bảo vệ / khám phá / xử lý gì?

### 2. Gameplay Lesson
Mission muốn dạy hoặc kiểm tra điều gì?

### 3. Main Objective
Điều kiện thắng.

### 4. Secondary Objectives
1–3 mục tiêu phụ.

### 5. Fixed Enemy Roster
Chỉ các species được phép xuất hiện.

### 6. Threat Profile
- Swarm
- Heavy
- Disruption
- Air
- Burrow
- Support

### 7. Hazards
Không có / 1 hazard chính / event hazard.

### 8. Key Terrain
- chokepoint;
- open zone;
- elevated point;
- alternate route;
- supply route.

### 9. Reward Profile
- Scrap;
- Materials;
- Blueprint Fragment;
- Mastery;
- Event reward nếu có.

### 10. Expected Failure Reason
Dự kiến người chơi có thể thua vì điều gì?

---

# VI. MISSION DIFFICULTY CARD

```text
Difficulty Tier:
Expected Player Knowledge:
Threat Budget:
Resource Pressure:
Attention Load:
Mechanical Complexity:
Expected Retry Count:
```

Difficulty tăng theo:

> Complexity → Pressure → Combination

Không chỉ tăng HP/Damage.

---

# VII. MISSION TEMPLATE

```text
MISSION ID:
NAME:

THEME:
LESSON:

TYPE:
TARGET DURATION:
TARGET DIFFICULTY:

ROSTER:
1.
2.
3.

MAIN OBJECTIVE:

SECONDARY OBJECTIVES:
1.
2.
3.

MAP FEATURES:
- Main Route:
- Alternate Route:
- Chokepoint:
- Elevated Area:
- Logistics Area:

HAZARD:

WAVE PLAN:
1.
2.
3.
4.
5.

BOSS:

RESOURCE PROFILE:
- Ammo:
- Power:
- Water:
- Scrap:

REWARDS:
- Scrap:
- Materials:
- Blueprint:
- Mastery:

EXPECTED FAILURE:
EXPECTED COUNTER:

PLAYTEST NOTES:
BALANCE NOTES:
QA NOTES:
```

---

# VIII. WAVE PRODUCTION TEMPLATE

## Wave Card

```text
Wave ID:
Mission:
Wave Number:
Threat Budget:
Spawn Window:
Lane:
Recovery Before:
Recovery After:
```

### Composition

```text
Species:
Count:
Spawn Timing:
Formation:
Entry Lane:
Special Behavior:
```

### Wave Role

Chọn 1 hoặc nhiều:
- Introduction
- Pressure
- Counter Check
- Disruption
- Recovery
- Escalation
- Boss Prep
- Boss

---

# IX. WAVE GRAMMAR

Một mission nên sử dụng nhịp:

```text
Intro
→ Pressure
→ Recovery
→ New Threat
→ Pressure
→ Peak
→ Recovery
→ Escalation
→ Finale
```

Không cần wave nào cũng mạnh hơn wave trước.

Recovery wave là một phần gameplay, không phải filler.

---

# X. ENEMY PRODUCTION TEMPLATE

```text
Enemy ID:
Display Name:
Archetype:
Threat Role:
Size Class:
Movement Class:
Primary Attack:
Special Mechanic:
Counter:
Weakness:
Threat Cost:
Recommended Frequency:
```

## Enemy Visual

```text
Silhouette:
Primary Material:
Accent:
Locomotion:
Attack Pose:
Hit Reaction:
Death Reaction:
VFX:
Audio Cue:
```

## Enemy Gameplay QA

- [ ] Silhouette readable
- [ ] Role recognizable
- [ ] Counter understandable
- [ ] Telegraph readable
- [ ] Threat proportional
- [ ] No accidental dominant behavior

---

# XI. ENEMY ROLE TAXONOMY

Mỗi enemy nên có role rõ:

### Swarm
Tạo density.

### Tank
Ép damage/armor solution.

### Disruptor
Phá một hệ thống.

### Support
Buff hoặc tạo setup.

### Specialist
Đòi hỏi response đặc biệt.

### Air
Buộc anti-air.

### Burrow
Phá safe zone.

### Boss
Kết hợp nhiều pressure.

Một species có thể có secondary role nhưng không nên có 5 role ngang nhau.

---

# XII. TOWER PRODUCTION TEMPLATE

```text
Tower ID:
Display Name:
Archetype:
Primary Role:
Secondary Role:
Mount Type:

BASE:
BARREL:
MODULE:

Targeting:
Damage Type:
Range:
Rate:
Ammo:
Heat:
Power:
Water:

Strength:
Weakness:
Countered By:
Counters:
```

## Tower Identity Test

Tower phải trả lời được:

> “Tôi xây tower này vì lý do gì?”

Nếu câu trả lời chỉ là:
> “nó có DPS cao hơn”

→ identity chưa đạt.

---

# XIII. MODULE PRODUCTION TEMPLATE

```text
Module ID:
Name:
Category:
Compatible Towers:
Primary Effect:
Secondary Effect:
Trade-off:
Resource Impact:
Visual Cue:
Audio Cue:
```

Module nên ưu tiên:
- alternative build;
- interaction;
- trade-off;
- utility.

---

# XIV. COMMANDER TEMPLATE

```text
Commander ID:
Name:
Role:
Personality:
Gameplay Focus:

Passive:
Skill 01:
Skill 02:

Strength:
Weakness:
Best With:
Counterplay:

Animation Signature:
VFX:
Audio:
```

---

# XV. BOSS TEMPLATE

```text
Boss ID:
Name:
Biome:
Role:
Arena:

Phase 1:
Phase 2:
Phase 3:
Final Phase:

Weak Point:
Counter:
Mobility:
Arena Interaction:
Adds:
Telegraph:
Recovery Window:

Victory Condition:
Defeat Feedback:
```

Boss checklist:
- [ ] Không chỉ là HP sponge.
- [ ] Có telegraph.
- [ ] Có learning.
- [ ] Có response.
- [ ] Có phase identity.
- [ ] Có counterplay.
- [ ] Có recovery window.

---

# XVI. HAZARD TEMPLATE

```text
Hazard ID:
Name:
Type:
Duration:
Area:
Intensity:
Telegraph:
Primary Effect:
Secondary Effect:
Interaction:
Counter:
Reward/Opportunity:
```

Hazard phải tạo:
> risk + response + opportunity

Không chỉ:
> random damage.

---

# XVII. BIOME TEMPLATE

```text
Biome ID:
Name:
Theme:
Story:
Climate:
Primary Palette:
Secondary Palette:

Terrain:
Structures:
Props:
Weather:
Hazards:

Native Enemy Candidates:
Native Resources:
Unique Gameplay:
Unique Visual:
```

Biome cần có ít nhất một gameplay identity.

---

# XVIII. BIOME IDENTITY TEST

Biome mới phải trả lời:

> “Nếu bỏ tên biome, người chơi vẫn nhận ra đây là biome gì không?”

và:

> “Biome này có thay đổi cách chơi không?”

Nếu chỉ đổi màu:
> chưa đạt.

---

# XIX. EVENT TEMPLATE

```text
Event ID:
Name:
Theme:
Start:
End:
Scope:
Gameplay Modifier:
Special Reward:
Cosmetics:
UI Theme:
Audio Theme:
```

Event phải có:
- gameplay identity;
- reward identity;
- visual identity.

---

# XX. SEASONAL EVENT TEMPLATE

### Event Loop

```text
Enter
→ Discover Modifier
→ Play Missions
→ Earn Event Currency
→ Spend Event Currency
→ Unlock Rewards
→ Complete Event
```

Không nên bắt người chơi học một core system hoàn toàn mới nếu event chỉ kéo dài ngắn.

---

# XXI. REWARD TEMPLATE

```text
Reward ID:
Source:
Tier:
Guaranteed:
Random:
Amount:
Eligible Pool:
```

Reward categories:
- progression;
- resource;
- blueprint;
- mastery;
- cosmetic;
- event.

---

# XXII. COSMETIC TEMPLATE

```text
Cosmetic ID:
Type:
Target:
Theme:
Rarity:
Acquisition:
VFX:
SFX:
Animation:
UI Preview:
```

Cosmetic không ảnh hưởng competitive balance.

PvE tactical-adjacent cosmetic effect chỉ được làm khi rõ ràng là presentation, không thay đổi power.

---

# XXIII. EXPEDITION TEMPLATE

```text
Expedition ID:
Region:
Duration:
Risk:
Required Capacity:
Expected Reward:
Rare Reward Chance:
Return Condition:
```

Expedition nên:
- tạo passive value;
- không thay thế active play;
- không trở thành bắt buộc mỗi session.

---

# XXIV. CONTENT REVIEW CHECKLIST

Mỗi content review theo 6 nhóm:

### Design
- [ ] Clear purpose
- [ ] Gameplay identity
- [ ] Counterplay
- [ ] Difficulty fit

### Content
- [ ] Roster valid
- [ ] Wave valid
- [ ] Reward valid
- [ ] Dependencies valid

### UX
- [ ] Player understands
- [ ] Feedback sufficient
- [ ] No unnecessary friction

### Visual
- [ ] Style consistent
- [ ] Silhouette clear
- [ ] Palette correct
- [ ] Mobile readability

### Audio/VFX
- [ ] Telegraph readable
- [ ] Important cues distinct

### Technical
- [ ] Valid IDs
- [ ] No missing refs
- [ ] Version valid
- [ ] Performance budget valid

---

# XXV. CONTENT VALIDATION RULES

Tool/validation cần phát hiện:

- duplicate IDs;
- missing references;
- invalid roster;
- enemy chưa unlock nhưng bị dùng;
- module incompatible với tower;
- reward không tồn tại;
- impossible objective;
- circular dependency;
- boss không có phase;
- hazard không có telegraph;
- mission duration target không hợp lý;
- missing localization key;
- missing asset reference.

---

# XXVI. CONTENT AUTHORING WORKFLOW

```text
Idea
→ Design Card
→ Draft
→ Internal Review
→ Prototype
→ Playtest
→ Balance
→ Art
→ Audio/VFX
→ QA
→ Approval
→ Release
```

Design và art có thể chạy song song sau khi gameplay contract đủ ổn.

---

# XXVII. CONTENT REVIEW QUESTIONS

Reviewer phải trả lời:

1. Content này tồn tại để làm gì?
2. Nó có tạo quyết định mới không?
3. Counter có rõ không?
4. Có hợp với progression hiện tại không?
5. Có quá giống content đã có không?
6. Có gây thêm cognitive load không cần thiết không?
7. Visual có rõ trên mobile không?
8. Reward có tương xứng không?
9. Có thể tái sử dụng system hiện có không?
10. Có đáng để đưa vào game không?

---

# XXVIII. ANTI-REPETITION RULES

Hai mission liên tiếp không nên giống nhau ở toàn bộ:

- map;
- roster;
- lesson;
- hazard;
- objective;
- pacing.

Có thể lặp một phần, nhưng phải đổi ít nhất 2–3 yếu tố có ý nghĩa.

Ví dụ:

Mission A:
> Swarm + Open Area + Survival

Mission B:
> Swarm + Chokepoint + Protect Supply

Cùng enemy nhưng gameplay khác.

---

# XXIX. CONTENT SCALING MODEL

Một content family có thể tạo nhiều biến thể bằng:

### Map
- layout A/B/C.

### Roster
- primary + secondary.

### Wave
- pressure pattern.

### Objective
- survive / protect / conserve / speed.

### Hazard
- none / environment / event.

### Reward
- standard / mastery / blueprint.

### Modifier
- optional challenge.

Mục tiêu:
> Một system tạo ra nhiều experience khác nhau mà không cần code mới.

---

# XXX. MISSION FAMILY LIBRARY

Đề xuất family:

1. Standard Defense
2. Swarm Control
3. Heavy Break
4. Escort / Supply
5. Internal Breach
6. Resource Scarcity
7. Hazard Survival
8. Boss Siege
9. Challenge
10. Event Mission

Mỗi family có template riêng nhưng dùng chung core systems.

---

# XXXI. CONTENT DIFFICULTY TAGS

```text
T1 — Intro
T2 — Basic
T3 — Intermediate
T4 — Advanced
T5 — Mastery
```

Tags:
- swarm;
- armor;
- disruption;
- logistics;
- hazard;
- boss;
- multitask.

Mission composition phải khớp player knowledge.

---

# XXXII. CONTENT PROGRESSION RULE

Mỗi content mới nên:
1. Introduce
2. Reinforce
3. Combine
4. Master

Ví dụ Enemy:

```text
Introduce Crawler
→ Reinforce Crawler
→ Combine Crawler + Acid
→ Master Crawler + Armor
```

Không introduce 4 mechanic mới cùng một mission.

---

# XXXIII. LOCALIZATION CONTENT

Mỗi content phải có:
- display name key;
- description key;
- objective key;
- reward key;
- tooltip key.

Không hard-code text vào gameplay definitions.

Text phải hỗ trợ:
- Vietnamese;
- English;
- expansion languages sau này.

---

# XXXIV. ART HANDOFF

Mỗi asset gửi artist phải có:

```text
Asset ID
Gameplay Role
Size / Relative Scale
Silhouette Reference
Color Direction
Material Direction
Animation Needs
VFX Needs
Audio Needs
Environment
Priority
Do / Don't
```

---

# XXXV. VFX / AUDIO HANDOFF

Mỗi gameplay event:

```text
Event:
Visual Cue:
Timing:
Priority:
Audio Cue:
Camera:
Screen FX:
Accessibility Alternative:
```

Ví dụ:
> EMP Stalker charge

Visual:
- pulse buildup.

Audio:
- rising electronic tone.

World:
- nearby lights flicker.

Warning:
- ring appears.

---

# XXXVI. CONTENT PRODUCTION PRIORITY

## P0
- Campaign missions.
- Core enemy roster.
- Core towers.
- Boss.
- Outpost stage.
- Core biome.

## P1
- More missions.
- More modules.
- More objectives.
- Daily/Weekly challenges.
- Cosmetics.

## P2
- Seasonal content.
- Events.
- New biome.
- New boss.
- Expansion content.

## P3
- Experimental systems.
- Optional social meta.
- Large live-event systems.

---

# XXXVII. CONTENT HANDOFF PACKAGE

Mỗi released content package gồm:

```text
/content_id
├── Design.md
├── Data
├── Localization
├── Art
├── Animation
├── VFX
├── Audio
├── QA
└── Balance Notes
```

Không release content chỉ với một gameplay file.

---

# XXXVIII. DEFINITION OF DONE

## Mission
- [ ] Design card
- [ ] Fixed roster
- [ ] Wave plan
- [ ] Objective
- [ ] Reward
- [ ] Playtest
- [ ] Balance
- [ ] Art
- [ ] Audio/VFX
- [ ] QA
- [ ] Localization
- [ ] Validation

## Enemy
- [ ] Gameplay role
- [ ] Counter
- [ ] Telegraph
- [ ] Visual identity
- [ ] Animation
- [ ] Audio/VFX
- [ ] Balance

## Tower
- [ ] Role
- [ ] Base
- [ ] Barrel
- [ ] Module compatibility
- [ ] Counterplay
- [ ] Visual
- [ ] VFX/SFX
- [ ] Balance

## Boss
- [ ] Phases
- [ ] Telegraph
- [ ] Counter
- [ ] Arena interaction
- [ ] Reward
- [ ] Balance
- [QA

## Biome
- [ ] Visual identity
- [ ] Gameplay identity
- [Terrain
- [ ] Hazards
- [ ] Props
- [ ] Enemy fit
- [ ] Content templates

---

# XXXIX. FINAL CONTENT PRINCIPLE

> **Last Outpost không cần hàng nghìn content độc lập. Nó cần một bộ hệ thống content đủ mạnh để tạo ra hàng trăm trải nghiệm khác nhau.**

Content tốt phải:
- có purpose;
- có identity;
- có counterplay;
- có feedback;
- có progression value;
- tái sử dụng được technology;
- đủ khác biệt để người chơi cảm nhận được sự mới mẻ.

---

# XL. HANDOFF STATUS

Content Production Pack v1.0 là template cấp production.

Nó được dùng sau khi:
- Vertical Slice pass;
- Playtest criteria được xác nhận;
- Visual Identity được khóa.

Mọi content production về sau nên xuất phát từ các template trong tài liệu này.
