# LAST OUTPOST — CONTENT SPECIFICATION v1.0

> Tài liệu lớp Content Design của `Last_Outpost_System_Gameplay_v1.1` và `Last_Outpost_Core_Combat_Gameplay_v1.0`.
>
> Mục tiêu: định nghĩa cách tạo, tổ chức, mở khóa, kiểm thử và mở rộng content của Last Outpost lên hàng trăm màn mà vẫn giữ bản sắc chiến thuật.
>
> **Nguyên tắc:** content phải phục vụ gameplay cốt lõi **BUILD → PREPARE → SURVIVE → ADAPT**.
>
> Mọi giá trị số trong tài liệu là **baseline prototype**, có thể thay đổi qua playtest.

---

# 0. CONTENT PILLARS

## 0.1. Content không chỉ là số lượng màn

Một content unit tốt phải tạo ra ít nhất một trong bốn thứ:

1. **Bài toán mới:** người chơi phải ưu tiên hoặc xây dựng khác đi.
2. **Biến thể quen thuộc:** cùng hệ thống nhưng kết hợp khác.
3. **Mục tiêu mới:** cùng combat nhưng cách thắng khác.
4. **Phần thưởng mới:** tạo động lực quay lại.

Không tăng tuổi thọ bằng cách đơn giản:
- tăng HP quái;
- tăng sát thương quái;
- kéo dài wave;
- spam thêm số lượng.

## 0.2. Quy tắc nhận diện content

Mỗi màn phải có một câu mô tả dễ hiểu:

> **“Màn này dạy / kiểm tra người chơi về điều gì?”**

Ví dụ:

> “Học cách xử lý EMP mà không phụ thuộc hoàn toàn vào điện.”

Nếu không trả lời được câu này, màn chưa đủ rõ.

---

# I. CONTENT HIERARCHY

```text
WORLD
  ↓
REGION
  ↓
BIOME
  ↓
CHAPTER
  ↓
MISSION
  ↓
WAVE
  ↓
ENCOUNTER
  ↓
ENEMY SPAWN / HAZARD
```

## 1.1. World

Lớp bao ngoài của Campaign. World mô tả tiến trình hậu tận thế tổng thể và các khu vực lớn.

## 1.2. Region

Một cụm địa lý / câu chuyện gồm nhiều Chapter.

## 1.3. Biome

Định nghĩa:
- visual theme;
- địa hình;
- tài nguyên;
- hazard pool;
- ambient audio;
- giới hạn xây dựng;
- environmental interaction.

## 1.4. Chapter

Một nhóm màn có chủ đề chiến thuật thống nhất.

Đề xuất:
- 8–12 Mission / Chapter;
- 1 milestone mission;
- 1 boss hoặc climax mission khi phù hợp.

## 1.5. Mission

Đơn vị content gameplay chính.

Mỗi Mission có:
- map;
- mission type;
- fixed enemy roster;
- wave script;
- objective;
- hazard;
- reward;
- difficulty profile;
- unlock conditions.

---

# II. BIOME SYSTEM

Biome không chỉ đổi background. Mỗi biome phải tạo ra một **gameplay identity**.

## 2.1. Biome baseline

### A. Abandoned Industrial Zone

**Fantasy:** nhà máy, kho thép, đường ray cũ.

**Gameplay:**
- nhiều buildable surface;
- logistics thuận lợi;
- nhiều vật cản nhân tạo;
- phù hợp tutorial nâng cao.

**Tài nguyên nổi bật:** Scrap.

### B. Rocky Quarry

**Fantasy:** mỏ đá, vách núi, hẻm sâu.

**Gameplay:**
- chênh cao rõ;
- nhiều Wall/Cliff Mount;
- đường đi hẹp;
- phù hợp Sniper / Harpoon / Control.

### C. Toxic Wetland

**Fantasy:** đầm lầy phóng xạ, nước độc, cây biến dị.

**Gameplay:**
- Water là tài nguyên quan trọng;
- Toxic Spore hazard pool tăng;
- movement lane bị giới hạn.

### D. Frozen Wasteland

**Fantasy:** tuyết, băng, trạm nghiên cứu bỏ hoang.

**Gameplay:**
- Heat giảm nhanh;
- Logistics có nguy cơ đóng băng;
- Supply movement chậm;
- ưu tiên Power / Heating.

### E. Ruined Urban Core

**Fantasy:** thành phố sụp đổ, container, xe bỏ hoang.

**Gameplay:**
- nhiều scavenged objects;
- lane phức tạp;
- nhiều cover;
- thích hợp Harpy / Riot Mech / Burrower.

> Biome có thể mở rộng sau prototype. Không cần tạo nhiều biome trước khi core systems chứng minh được fun.

---

# III. MAP DESIGN

## 3.1. Cấu trúc một map

Mỗi map phải xác định:

```text
ENTRY POINTS
     ↓
PRIMARY LANES
     ↓
TACTICAL INTERSECTIONS
     ↓
BASE CORE / OBJECTIVE
```

## 3.2. Thành phần map

- Enemy Entrance.
- Main Lane.
- Secondary Lane.
- Choke Point.
- Buildable Node.
- Logistics Node.
- Hazard Zone.
- Hero access route.
- Base Core.

## 3.3. Quy tắc lane

Map chuẩn nên có:
- 1–3 primary threat routes;
- tối đa 2 route active cùng lúc ở midgame;
- route phụ chỉ kích hoạt khi có lý do gameplay rõ.

Không tạo maze quá phức tạp khiến người chơi không hiểu hướng quái.

## 3.4. Map size

Map phải đủ lớn để thể hiện 2.5D và logistics nhưng không khiến camera kéo quá xa.

Baseline:
- Early: 1 khu phòng thủ chính.
- Mid: 2 khu kết nối.
- Late: 2–3 khu với vertical gameplay.

---

# IV. MISSION TYPES

## 4.1. Standard Defense

**Mục tiêu:** sống sót qua toàn bộ wave.

Dùng để:
- giới thiệu roster;
- kiểm tra build;
- tiến campaign.

## 4.2. Specialist Mission

**Mục tiêu:** bảo vệ một hệ thống cụ thể.

Ví dụ:
- bảo vệ Ammo Depot;
- giữ Generator;
- bảo vệ Hydroponics;
- giữ Supply Convoy.

## 4.3. Challenge Mission

**Mục tiêu:** hoàn thành một điều kiện đặc biệt.

Ví dụ:
- không dùng Tower điện;
- giữ Core trên 80%;
- giới hạn Scrap;
- hoàn thành dưới thời gian mục tiêu.

## 4.4. Boss Mission

Boss không chỉ là “HP lớn hơn”.

Phải có:
- mechanic riêng;
- phase hoặc behavior change;
- counterplay;
- escalation;
- kết thúc rõ ràng.

---

# V. MISSION TEMPLATE

Mỗi Mission phải được author bằng một schema nhất quán.

```text
Mission ID
Region
Chapter
Biome
Mission Type
Recommended Progression
Primary Objective
Secondary Objectives
Enemy Roster
Wave Count
Wave Duration
Hazard
Available Build Surfaces
Logistics Constraints
Available Resources
Boss / Climax
Reward Table
Star Conditions
Unlock Condition
Teaching Intent
Estimated Clear Time
```

## 5.1. Fixed Enemy Roster

**Mỗi màn có roster quái cố định.**

Ví dụ:

```text
Mission 024
Roster:
- Crawler
- Acid Bloater
- EMP Stalker
```

Wave Director chỉ được chọn các loài trong roster đó.

Có thể thay đổi:
- quantity;
- timing;
- lane;
- elite ratio;
- combination;
- hazard interaction.

Không spawn quái ngoài roster của màn để tạo thử thách ngẫu nhiên.

---

# VI. ENEMY INTRODUCTION PLAN

12 loài quái không được mở cùng lúc.

## 6.1. Roster progression đề xuất

| Stage | Loài mở dần |
|---|---|
| Tutorial | Crawler |
| Early | Acid Bloater |
| Early+ | Iron-Hide Behemoth |
| Mid | EMP Stalker |
| Mid | Tunnel Burrower |
| Mid+ | Corpse Weaver |
| Mid+ | Scavenger Harpy |
| Late | Spore Spreader |
| Late | Riot Mech Mutate |
| Late | Siren Screamer |
| Boss | Colossus Titan |
| Boss | Hive Mother |

## 6.2. Enemy mastery rule

Mỗi quái phải có ít nhất:
- 1 Mission giới thiệu;
- 1 Mission kiểm tra counter;
- 1 Mission phối hợp với threat khác;
- 1 Mission advanced nếu loài đó vẫn xuất hiện ở late game.

---

# VII. WAVE CONTENT DESIGN

## 7.1. Wave composition

Một wave gồm:

```text
ENTRY
→ BUILDUP
→ THREAT MOMENT
→ RESOLUTION
```

## 7.2. Wave grammar

Các mẫu wave cơ bản:

### Swarm Wave

Crawler mass.

**Mục đích:** kiểm tra Anti-Swarm / Control.

### Pressure Wave

Tank + support.

**Mục đích:** ưu tiên target.

### Disruption Wave

EMP / Burrower / Harpy.

**Mục đích:** ép người chơi bảo vệ hệ thống.

### Combo Wave

2–3 threat classes hỗ trợ nhau.

**Mục đích:** kiểm tra adaptation.

### Recovery Wave

Threat thấp hơn để người chơi sửa / reload / tái bố trí.

### Climax Wave

Threat cao nhất của mission.

## 7.3. Anti-frustration

Không được để 2–3 wave disruption cực mạnh liên tiếp nếu người chơi không có recovery window.

---

# VIII. MISSION PACING — 5–15 PHÚT

## 8.1. Early Mission — 5–8 phút

Đề xuất:
- 6–9 wave;
- 1 major twist;
- tối đa 1 elite threat;
- không bắt người chơi quản lý quá 3 hệ thống cùng lúc.

## 8.2. Mid Mission — 7–12 phút

Đề xuất:
- 8–12 wave;
- 1–2 major twists;
- 2–3 enemy species;
- 1 logistics problem.

## 8.3. Late Mission — 9–15 phút

Đề xuất:
- 10–15 wave;
- 2–3 threat layers;
- 3–4 enemy species;
- hazard hoặc boss/climax.

## 8.4. Boss Mission — 10–15 phút

Không kéo dài boss bằng HP sponge.

Tăng độ dài bằng:
- phase;
- arena change;
- spawn pattern;
- objective change;
- mechanic interaction.

---

# IX. OBJECTIVE DESIGN

## 9.1. Primary Objective

Luôn phải rõ trong 5 giây đầu.

Ví dụ:
> Bảo vệ Base Core.

## 9.2. Secondary Objective

Có 1–3 objective phụ.

Mục tiêu phụ nên khuyến khích **chơi giỏi hơn**, không phải bắt người chơi grind.

Ví dụ:
- Core HP ≥ 80%.
- không mất Logistics Hub.
- không để Civilian casualty.
- hoàn thành ≤ 10 phút.

## 9.3. Optional Challenge

Challenge có thể cực khó nhưng không bắt buộc để hoàn thành campaign.

---

# X. STAR & SCORE DESIGN

## 10.1. Ba sao

### ⭐ 1 Star

Hoàn thành primary objective.

### ⭐ 2 Stars

Hoàn thành primary + một performance condition.

### ⭐ 3 Stars

Hoàn thành primary + performance + mastery/challenge condition.

## 10.2. Score components

Score nên ưu tiên:
- Core HP remaining;
- completion time;
- civilian safety;
- logistics uptime;
- tactical efficiency.

Không để score chỉ phụ thuộc damage.

---

# XI. REWARD CONTENT

## 11.1. Mission reward

Có thể gồm:
- Scrap;
- Biomass;
- Blueprint Fragment;
- upgrade material;
- Mastery XP;
- cosmetic token;
- premium reward khi có event phù hợp.

## 11.2. Reward rule

Mỗi reward phải có mục đích rõ:

| Reward | Sink |
|---|---|
| Scrap | Battle upgrade / Outpost |
| Biomass | Tech Tree |
| Blueprint Fragment | Blueprint |
| Mastery XP | Mastery |
| Cosmetic Token | Cosmetic |
| Premium Currency | Shop / Pass |

## 11.3. Reward pacing

Không khóa power progression vào một reward random duy nhất.

Blueprint quan trọng phải luôn có **đường kiếm được chắc chắn**.

---

# XII. CHAPTER STRUCTURE

## 12.1. Một Chapter 10 màn mẫu

```text
M01 — Introduction
M02 — Reinforcement
M03 — First Counter
M04 — System Pressure
M05 — Specialist
M06 — Combination
M07 — Hazard Introduction
M08 — Advanced Combination
M09 — Pre-Boss
M10 — Boss / Climax
```

## 12.2. Content rhythm

Mỗi Chapter nên có nhịp:

```text
Teach
→ Reinforce
→ Mix
→ Twist
→ Test
```

Không đưa mechanic mới ở mọi màn.

---

# XIII. CAMPAIGN SCALE

## 13.1. Mục tiêu content

Campaign có thể mở rộng tới:
- 100+ màn ở giai đoạn đầu;
- 200+ màn là mục tiêu trung hạn;
- 500+ màn nếu content pipeline chứng minh được hiệu quả.

Không cần thiết kế 500 màn thủ công hoàn toàn từ đầu.

## 13.2. Modular content generation

Một mission được tạo từ module:

```text
Biome
+ Map Layout
+ Enemy Roster
+ Wave Grammar
+ Hazard
+ Objective
+ Reward Profile
+ Difficulty Modifier
```

Từ đó tạo nhiều biến thể mà vẫn kiểm soát được quality.

## 13.3. Rule chống “copy màn”

Hai màn liên tiếp không nên giống nhau ở cả 4 yếu tố:
- map layout;
- main threat;
- objective;
- hazard.

Ít nhất một yếu tố phải thay đổi đáng kể.

---

# XIV. DIFFICULTY CURVE

## 14.1. Difficulty tăng theo quyết định, không chỉ stat

Thứ tự ưu tiên:

1. Enemy combination.
2. Route complexity.
3. System interaction.
4. Hazard timing.
5. Resource pressure.
6. Stat increase.

## 14.2. Difficulty tiers

### Normal

Dùng để học và hoàn thành campaign.

### Hard

Tăng phối hợp threat và giảm safety margin.

### Nightmare

Cho endgame:
- stricter resource pressure;
- faster escalation;
- advanced enemy combinations;
- optional modifiers.

Không chỉ nhân HP lên 2x/3x.

---

# XV. EVENT / CHALLENGE CONTENT

## 15.1. Daily Challenge

Một mission seed cố định trong ngày.

Có modifier, ví dụ:
- no electrical tower;
- heat +30%;
- Crawler speed +20%.

## 15.2. Weekly Challenge

Một challenge phức tạp hơn:
- multi-objective;
- special roster;
- unique modifier;
- leaderboard nếu phù hợp.

## 15.3. Seasonal Event

Event kéo dài nhiều ngày/tuần:
- event mission pool;
- exclusive cosmetics;
- special resource;
- limited challenges.

Không thay thế campaign chính.

---

# XVI. WORLD EVENT CONTENT

World Event là content có ảnh hưởng tới nhiều mission nhưng không phá progression.

Ví dụ:

### Scrap Meteor Shower

- một nhóm mission xuất hiện meteor;
- tạo node Scrap hiếm;
- có risk/reward.

### Solar EMP Flare

- electrical towers bị vô hiệu hóa;
- mechanical build trở nên giá trị hơn.

## 16.1. World Event rules

- chỉ 1 Major World Event active tại một thời điểm;
- event phải có counterplay;
- không khóa player khỏi campaign chính.

---

# XVII. BOSS CONTENT

## 17.1. Boss entry rule

Boss cần được foreshadow (báo trước) qua:
- Intel;
- elite encounter;
- visual clue;
- audio cue;
- pre-boss mission.

## 17.2. Colossus Titan

Content identity:
- phá công trình;
- ép reposition;
- tương tác địa hình.

Boss phase mẫu:
1. Approach.
2. Structure Break.
3. Rage / environmental interaction.
4. Final push.

## 17.3. Hive Mother

Content identity:
- spawn pressure;
- hatchery control;
- anti-swarm.

Phase mẫu:
1. Nest setup.
2. Spawn escalation.
3. Defensive retaliation.
4. Egg overload / final push.

---

# XVIII. TUTORIAL CONTENT CURRICULUM

Tutorial không đưa hết hệ thống một lần.

### Mission 1

Build + basic Crawler.

### Mission 2

Targeting + resource basics.

### Mission 3

Module customization.

### Mission 4

Armor threat.

### Mission 5

Logistics / Ammo.

### Mission 6

Heat / Water.

### Mission 7

Special disruption threat.

### Mission 8

Objective + replay / stars.

Sau tutorial, game mở dần meta systems.

---

# XIX. CONTENT UNLOCK GATING

## 19.1. Không mở quá nhiều thứ cùng lúc

Mỗi milestone chỉ nên mở 1–2 hệ thống lớn.

Ví dụ:

```text
Campaign Progress
   ↓
New Enemy
   ↓
New Tower Option
   ↓
New System
   ↓
New Biome
```

## 19.2. Gate bằng mastery hiểu biết

Có thể yêu cầu:
- hoàn thành một mission;
- đạt 2 sao;
- dùng mechanic mới thành công;
- đánh bại một enemy cụ thể.

Không yêu cầu grind vô nghĩa để mở core mechanic.

---

# XX. CONTENT AUTHORING WORKFLOW

Mỗi mission phải đi qua pipeline:

```text
Concept
 ↓
Paper Design
 ↓
Graybox
 ↓
Playable Prototype
 ↓
Balance Pass
 ↓
UX / Readability Pass
 ↓
Art / Audio Pass
 ↓
QA
 ↓
Telemetry Review
 ↓
Release
```

## 20.1. Graybox requirement

Mission phải chơi được **trước khi có art hoàn chỉnh**.

## 20.2. QA requirement

Test ít nhất:
- normal clear;
- fastest clear;
- low-resource clear;
- intended counter build;
- off-meta build;
- failure state;
- reconnect / resume;
- 5–15 minute duration target.

---

# XXI. CONTENT QUALITY CHECKLIST

Một Mission đạt chuẩn khi:

- Người chơi hiểu objective trong vài giây.
- Roster rõ ràng.
- Enemy có counterplay.
- Có ít nhất một decision moment đáng nhớ.
- Không cần hidden knowledge để thắng.
- Có recovery window hợp lý.
- Không quá dài 15 phút trong điều kiện bình thường.
- Không bị giải duy nhất bởi một Tower.
- Reward hữu ích.
- Replay có lý do.
- Art readability không che gameplay.

---

# XXII. CONTENT ANTI-REPETITION RULES

Tránh lặp bằng cách kiểm soát 6 trục:

1. Map.
2. Roster.
3. Objective.
4. Hazard.
5. Resource pressure.
6. Wave grammar.

Hai màn có thể dùng cùng roster nếu ít nhất 2–3 trục còn lại thay đổi.

---

# XXIII. CONTENT DATA STRUCTURE — CONCEPTUAL

Một Mission data có thể hình dung:

```text
MissionData {
    id
    regionId
    chapterId
    biomeId
    missionType
    recommendedPower

    mapLayoutId
    buildNodeSet
    logisticsProfile

    primaryObjective
    secondaryObjectives[]

    enemyRoster[]
    waveSet[]
    hazardSet[]

    bossId?

    rewardProfile
    starConditions[]
    unlockRequirements[]

    expectedDuration
    teachingIntent
}
```

## 23.1. Content versioning

Mỗi mission phải có version để tránh thay đổi live content làm hỏng replay/result data.

Ví dụ:

`M024_v3`

---

# XXIV. ENDGAME CONTENT

Sau Campaign chính, content không cần chỉ tiếp tục bằng map mới.

Có thể dùng:
- Endless / Survival Variant;
- Nightmare missions;
- Daily/Weekly challenges;
- Mastery missions;
- seasonal events;
- Boss rematch;
- score hunting.

## 24.1. Endless rule

Endless không thay thế campaign.

Mục đích:
- replayability;
- mastery;
- leaderboard;
- challenge.

Không dùng Endless làm cách duy nhất để farm core progression.

---

# XXV. CONTENT ROADMAP

## Phase 1 — Prototype

- 1 biome;
- 3–4 maps;
- 4–5 enemy species;
- 6–8 towers/build options;
- 10 missions;
- 1 boss.

## Phase 2 — Vertical Slice

- 2 biomes;
- 15–20 missions;
- toàn bộ core enemy families;
- 1–2 bosses;
- Outpost progression cơ bản.

## Phase 3 — Launch Candidate

- 4–5 biomes;
- 50–100 campaign missions;
- đầy đủ 12 enemy roster;
- Daily/Weekly;
- Mastery;
- seasonal framework.

## Phase 4 — Live Expansion

- 200+ missions;
- scenario packs;
- new biome;
- boss/event rotations;
- long-term seasonal content.

---

# XXVI. CONTENT BALANCE PRINCIPLES

1. **Một màn = một bài toán chính.**
2. **Một Chapter = một cụm bài toán liên quan.**
3. **Một Biome = một identity gameplay rõ.**
4. **Một Boss = một mechanic đáng nhớ.**
5. **Một Event = một modifier có counterplay.**
6. **Một reward = một sink cụ thể.**
7. **Một hệ thống mới = phải có tutorial / introduction trước khi test ở độ khó cao.**

---

# XXVII. DEFINITION OF DONE — CONTENT SYSTEM

Content pipeline được coi là hoàn thành khi:

- Có thể tạo Mission bằng data mà không cần sửa gameplay code cho mỗi màn.
- Có fixed roster per mission.
- Có wave grammar tái sử dụng.
- Có objective system tái sử dụng.
- Có reward profile tái sử dụng.
- Có difficulty modifiers.
- Có biome modifiers.
- Có Boss content framework.
- Có Daily / Weekly challenge framework.
- Có telemetry cho clear rate, duration, fail point và pick rate.
- Có thể sản xuất content mới mà không tạo hệ thống mới cho từng mission.

---

# XXVIII. KẾT LUẬN

Last Outpost không cần hàng trăm cơ chế để có hàng trăm màn.

Game cần một **content grammar (ngữ pháp content)** đủ mạnh để các hệ thống hiện tại có thể kết hợp theo nhiều cách:

```text
BIOME
  + MAP
  + FIXED ROSTER
  + WAVE GRAMMAR
  + OBJECTIVE
  + HAZARD
  + REWARD
  + DIFFICULTY
      ↓
   NEW MISSION
```

Mục tiêu cuối:

> **Người chơi luôn nhận ra đây là Last Outpost, nhưng không cảm thấy mình đang chơi lại đúng một trận.**
