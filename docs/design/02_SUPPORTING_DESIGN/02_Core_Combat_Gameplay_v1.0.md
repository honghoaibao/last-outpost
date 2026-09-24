# LAST OUTPOST — CORE COMBAT & GAMEPLAY DESIGN v1.0

> Tầng tài liệu tiếp theo của `Last_Outpost_System_Gameplay_v1.1`.
>
> Mục tiêu: biến các luật hệ thống thành gameplay combat cụ thể có thể prototype, test và balance.
>
> Các giá trị số trong tài liệu là **đề xuất prototype**, chưa phải thông số khóa cứng.

---

# 0. COMBAT PILLARS

Combat của Last Outpost phải tạo cảm giác:

**Đọc tình hình → Xây đúng → Dẫn đúng → Phản ứng đúng → Tối ưu.**

4 yêu cầu:

1. **Readable:** người chơi hiểu vì sao mình đang thắng/thua.
2. **Tactical:** mỗi wave buộc người chơi ưu tiên một vấn đề.
3. **Interactive:** người chơi có hành động chủ động ngoài việc đặt tower.
4. **Emergent:** các hệ thống có thể kết hợp tạo ra tình huống bất ngờ.

Không dùng hidden counter kiểu "chỉ tower X mới giết được quái Y". Mỗi threat quan trọng phải có ít nhất 2 hướng xử lý khác nhau.

---

# I. COMBAT LOOP CHI TIẾT

## 1.1. Vòng lặp micro

```text
Observe
  ↓
Identify Threat
  ↓
Prioritize
  ↓
Adjust Tower / Hero / Logistics
  ↓
Resolve Wave
  ↓
Recover
```

Một wave tốt phải tạo ra ít nhất một **decision moment** (khoảnh khắc ra quyết định) đáng kể.

## 1.2. Vòng lặp macro của một màn

```text
Briefing
  ↓
Initial Build
  ↓
Wave 1
  ↓
Recovery
  ↓
Wave 2+
  ↓
Twist / Hazard
  ↓
Escalation
  ↓
Final Wave / Boss
  ↓
Result
```

## 1.3. Mốc thời gian

Một màn hoàn chỉnh: **5–15 phút**.

Đề xuất nhịp:

| Giai đoạn | % thời lượng màn | Vai trò |
|---|---:|---|
| Opening | 10–15% | Dạy roster và layout |
| Build Pressure | 30–35% | Bắt đầu yêu cầu tối ưu |
| Twist | 15–20% | Thay đổi bài toán |
| Escalation | 20–25% | Phối hợp threat |
| Climax | 10–15% | Boss/final wave |

---

# II. TOWER DESIGN FRAMEWORK

## 2.1. Mỗi Tower có 3 tầng build

```text
BASE
  ↓
BARREL
  ↓
MODULE
```

Tower không phải một item có một công thức cố định. Build của người chơi xác định **hành vi**.

## 2.2. Base

Base quyết định:

- survivability;
- heat capacity;
- ammo capacity;
- mount compatibility;
- mobility;
- logistics dependency.

### Base families

| Base | Điểm mạnh | Trade-off |
|---|---|---|
| Treaded | Cơ động | Giáp thấp hơn |
| Heavy Anchor | Bền | Không cơ động |
| Heat Sink | Chịu nhiệt | Tốn Scrap hơn |
| Wall Mount | Tận dụng địa hình | Góc đặt hạn chế |
| Rail Mount | Di chuyển theo ray | Phụ thuộc Power |

## 2.3. Barrel

Barrel quyết định hành vi tấn công chính:

- projectile;
- beam;
- explosive;
- control;
- pull/push;
- environmental interaction.

## 2.4. Module

Module tạo biến thể gameplay.

Module nên ưu tiên:

- thay đổi cách chơi;
- trade-off;
- synergy;
- utility;

hơn là cộng stat thuần túy.

### Ví dụ

**Overclock Core**
- Fire Rate +15%
- Heat generation +30%

**Magnet Core**
- tăng phạm vi hút Scrap;
- có thể kéo salvage gần tower;
- damage không tăng.

**Emergency Cooler**
- Cooling burst khi Heat > 80%;
- cooldown riêng.

---

# III. TOWER ARCHETYPES

## 3.1. Single Target

Vai trò: xử lý elite/boss/high-value target.

Đặc điểm:
- DPS ổn định;
- range cao;
- ít hiệu quả với swarm.

## 3.2. Anti Swarm

Vai trò: kiểm soát đàn quái.

Đặc điểm:
- AoE;
- damage thấp hơn;
- quan trọng trong các roster có Crawler.

## 3.3. Armor Break

Vai trò: chuẩn bị mục tiêu cho hỏa lực khác.

Hiệu ứng nên dùng:

`Armor Shred`, `Expose`, `Acid`.

## 3.4. Control

Vai trò:
- slow;
- stun;
- root;
- pull;
- knockback.

Control phải có diminishing return để tránh perma-lock boss.

## 3.5. Support / Logistics

Vai trò:
- cấp đạn;
- cooling;
- power support;
- repair;
- detection.

## 3.6. Special / Environmental

Tương tác:
- oil + fire;
- water + cooling;
- electricity + wet zone;
- terrain collapse;
- corpse disposal.

---

# IV. TARGETING SYSTEM

## 4.1. Target rules

Mỗi tower chọn một mode chính:

- First;
- Nearest;
- Strongest;
- Lowest HP;
- Special Threat.

Không cho phép scripting tùy ý trong gameplay cơ bản.

## 4.2. Special Threat score

Một tower có thể ưu tiên theo:

```text
Threat Score
=
Base Threat
+ Mission Modifier
+ Distance Risk
+ Special Ability Risk
```

Mục tiêu: tower support không tự bắn nhầm swarm khi một EMP Stalker đang chuẩn bị disable cả cụm điện.

## 4.3. Target lock

- Target lock ngắn để tránh đổi mục tiêu mỗi frame.
- Nếu target chết/rời range, tower tìm target mới.
- Một số tower có lock-on riêng.

---

# V. DAMAGE & STATUS

## 5.1. Damage flow

```text
Attack
 ↓
Hit Validation
 ↓
Raw Damage
 ↓
Armor / Resistance
 ↓
Weakness / Interaction
 ↓
Final Damage
 ↓
Status Effect
```

## 5.2. Damage tags

- Kinetic
- Fire
- Acid
- Electric
- Freeze
- Explosive

Một enemy không nên có quá 3 resistance/weakness quan trọng cùng lúc.

## 5.3. Status rules

Mỗi status có:

- source;
- duration;
- intensity;
- stack rule;
- cleanse rule.

Không dùng stack vô hạn.

### Ví dụ

**Slow**
- stack theo intensity;
- cap 70% slow.

**Burn**
- refresh duration;
- damage theo tick.

**Shock**
- stack tối đa 3;
- đạt ngưỡng → stun ngắn.

---

# VI. ENEMY DESIGN FRAMEWORK

Mỗi enemy cần có 6 trường:

```text
Role
Threat
Defense
Signature Ability
Counter A
Counter B
```

## 6.1. Enemy roster

### 1. Crawler

- Role: Swarm
- Signature: số lượng lớn, leo barricade thấp
- Counter A: AoE
- Counter B: slow/control

### 2. Acid Bloater

- Role: Structure Punisher
- Signature: acid burst khi chết
- Counter A: sniper
- Counter B: controlled kill zone

### 3. Iron-Hide Behemoth

- Role: Heavy
- Signature: phá barricade
- Counter A: armor break
- Counter B: environmental damage

### 4. EMP Stalker

- Role: System Disruptor
- Signature: disable electric cluster
- Counter A: mechanical towers
- Counter B: spread power infrastructure

### 5. Tunnel Burrower

- Role: Infiltrator
- Signature: bypass surface maze
- Counter A: seismic detection
- Counter B: internal guard

### 6. Corpse Weaver

- Role: Support
- Signature: tăng Corpse Density
- Counter A: corpse disposal
- Counter B: focus fire

### 7. Scavenger Harpy

- Role: Air Harasser
- Signature: target drone/supply object
- Counter A: anti-air
- Counter B: protected logistics

### 8. Spore Spreader

- Role: Area Denial
- Signature: vision + drone disruption
- Counter A: wind/heat tower
- Counter B: long-range focus

### 9. Riot Mech Mutate

- Role: Front Shield
- Signature: frontal defense
- Counter A: flanking
- Counter B: pull/control

### 10. Siren Screamer

- Role: Buffer
- Signature: speed + control resistance
- Counter A: interrupt
- Counter B: snipe

### 11. Colossus Titan

- Role: Boss / Siege
- Signature: displace structures
- Counter A: sustained DPS
- Counter B: terrain interactions

### 12. Hive Mother

- Role: Boss / Spawner
- Signature: continuous Crawler production
- Counter A: burst
- Counter B: spawn control

---

# VII. ENEMY AI PRIORITY

Enemy không chỉ đi thẳng đến Core.

## 7.1. Primary target

```text
Mission Objective
→ Strategic Structure
→ Logistics
→ Core
```

Tùy enemy role.

## 7.2. Special action rules

Special skill chỉ dùng khi:

1. điều kiện phù hợp;
2. cooldown sẵn sàng;
3. target hợp lệ;
4. không bị interrupted.

## 7.3. Telegraph

Mọi ability có thể gây hậu quả lớn phải có:

- animation;
- icon;
- audio;
- vùng cảnh báo nếu cần.

---

# VIII. FIXED ROSTER PER MISSION

Đây là luật nội dung quan trọng.

## 8.1. Mỗi màn có roster cố định

Ví dụ:

```text
Mission 12
Roster = [Crawler, Behemoth, Siren]
```

Wave Director **không được spawn** EMP Stalker hoặc Burrower trong màn này.

## 8.2. Difficulty bằng cách phối hợp

Cùng một roster có thể tạo nhiều độ khó:

```text
Crawler alone
→ Crawler + Siren
→ Crawler + Behemoth
→ Siren behind Behemoth
→ Crawler + Behemoth + Siren + Hazard
```

Như vậy game dạy người chơi sâu hơn mà không cần liên tục thêm species mới.

---

# IX. WAVE DIRECTOR

## 9.1. Threat budget

Mỗi wave có:

```text
Budget = Base × Wave Factor × Difficulty Factor
```

Enemy được mua bằng Threat Cost.

## 9.2. Director priorities

Thứ tự tăng áp lực:

1. quantity;
2. timing;
3. lane combination;
4. enemy synergy;
5. special ability frequency;
6. elite modifier;
7. raw stats.

## 9.3. Anti-frustration rules

Wave Director không được:

- spawn quá nhiều special threat cùng một lúc;
- spawn boss ngay sau một critical hazard nếu người chơi không có recovery window;
- phá toàn bộ logistics mà không có telegraph.

## 9.4. Wave grammar

Một wave nên có:

```text
Lead
→ Pressure
→ Threat
→ Twist
→ Release
```

Không phải wave nào cũng cần đủ 5 bước.

---

# X. BOSSES

## 10.1. Boss philosophy

Boss không đơn giản là:

`Enemy HP × 20`.

Boss phải thay đổi bài toán của battlefield.

## 10.2. Colossus Titan

Phase 1 — March
- áp sát;
- phá barricade.

Phase 2 — Displacement
- phá hoặc đẩy một số tower;
- buộc người chơi rebuild/reposition.

Phase 3 — Exposure
- có cửa sổ yếu điểm;
- khuyến khích burst damage.

## 10.3. Hive Mother

Phase 1 — Nest
- spawn Crawler vừa phải.

Phase 2 — Overgrowth
- tăng tốc spawn;
- tạo pressure lên corpse/space.

Phase 3 — Collapse
- giảm defensive action;
- mở burst window.

Boss phải nằm trong tổng thời lượng màn **10–15 phút**.

---

# XI. COUNTER DESIGN MATRIX

Mỗi threat quan trọng phải có ít nhất 2 counter.

| Threat | Counter 1 | Counter 2 |
|---|---|---|
| Swarm | AoE | Slow |
| Heavy Armor | Armor Break | Environmental |
| EMP | Mechanical | Power redundancy |
| Burrower | Seismic | Internal Guard |
| Corpse Bridge | Incinerate | Prevent density |
| Air | Anti-Air | Protected logistics |
| Spore | Wind/Heat | Range / positioning |
| Buffer | Interrupt | Focus fire |
| Boss | DPS | Mechanics |

Đây là khung balance quan trọng: **không ép người chơi phải sở hữu một item duy nhất mới có thể qua màn.**

---

# XII. COMBAT FEEL

## 12.1. Hit feedback

Mỗi loại damage cần feedback riêng ở mức dễ đọc:

- Kinetic: impact;
- Fire: burn;
- Acid: corrosion;
- Electric: arc;
- Freeze: ice build-up;
- Explosive: burst.

## 12.2. Audio priority

Khi swarm 1000+ units, audio không thể phát từng tiếng.

Dùng:

- pooled impact sounds;
- layered ambience;
- threat-specific cues;
- boss cue;
- hazard cue.

## 12.3. Camera

Không rung camera liên tục.

Camera shake chỉ dùng cho:

- boss hit;
- major explosion;
- earthquake;
- orbital event.

---

# XIII. 5-PHÚT VÀ 15-PHÚT REFERENCE FLOW

## Early mission — khoảng 5–8 phút

```text
0:00–0:45  Brief/Build
0:45–2:00  Wave 1–2
2:00–4:30  Pressure + Twist
4:30–6:00  Final wave
6:00–8:00  Boss/Result buffer nếu cần
```

## Late mission — khoảng 9–15 phút

```text
0:00–1:00  Build
1:00–4:00  Early waves
4:00–7:00  Escalation
7:00–10:00 Hazard/Twist
10:00–14:00 Final sequence
14:00–15:00 Result transition
```

Các mốc là khung tham khảo; actual pacing phải được playtest.

---

# XIV. PROTOTYPE TEST CHECKLIST

## Test A — Swarm

Mục tiêu:
- 50–200 Crawler;
- kiểm tra readable AoE;
- kiểm tra pathing;
- kiểm tra target selection.

## Test B — System Disruption

Roster:
- EMP Stalker;
- Mechanical tower;
- Electric tower;
- Support logistics.

Mục tiêu:
- kiểm tra power redundancy;
- kiểm tra alert clarity.

## Test C — Corpse Dynamics

Roster:
- Crawler;
- Corpse Weaver;
- Heavy.

Mục tiêu:
- kiểm tra Corpse Density;
- kiểm tra bridge threshold;
- kiểm tra visual readability.

## Test D — Boss

Mục tiêu:
- boss không làm trận vượt 15 phút;
- mechanic hiểu được mà không cần đọc wiki;
- boss tạo quyết định mới thay vì chỉ tăng HP.

---

# XV. DEFINITION OF DONE — CORE COMBAT

Core Combat chỉ được xem là đạt prototype khi:

- [ ] 6 archetype tower có hành vi khác nhau rõ rệt.
- [ ] Base + Barrel + Module tạo ra build có khác biệt thực sự.
- [ ] 12 enemy có vai trò nhận diện được.
- [ ] Mỗi mission chỉ spawn đúng roster của mission.
- [ ] Mỗi major threat có ít nhất 2 counter.
- [ ] Wave Director điều chỉnh pressure mà không phá fairness.
- [ ] Boss có mechanic riêng.
- [ ] Người chơi hiểu nguyên nhân thất bại qua UI/feedback.
- [ ] Màn prototype nằm trong khoảng 5–15 phút.
- [ ] Không cần trả tiền để unlock counter bắt buộc.
- [ ] Swarm test đạt mục tiêu hiệu năng của engine prototype.

---

# XVI. NEXT DESIGN LAYER

Sau tài liệu này, thứ tự thiết kế nên là:

```text
CORE COMBAT
   ↓
CONTENT SPEC
   ↓
PROGRESSION / OUTPOST
   ↓
ECONOMY & MONETIZATION
   ↓
UI/UX FLOW
   ↓
TECHNICAL ARCHITECTURE
   ↓
PROTOTYPE / PLAYTEST
```

Ưu tiên prototype trước:

**Tower → Enemy → Wave → Path → Combat Feedback → Boss.**

Đây là phần phải "fun" trước khi mở rộng world map, guild, economy hoặc live-service content.
