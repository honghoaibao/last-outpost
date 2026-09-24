# LAST OUTPOST — PLAYTEST & BALANCE SPECIFICATION v1.0

## 0. Mục đích
Định nghĩa cách kiểm thử và cân bằng Vertical Slice trước khi scale production.

Mục tiêu:
- xác minh core gameplay;
- đo thời lượng màn 5–15 phút;
- kiểm tra Tower/Enemy counter;
- kiểm tra difficulty curve;
- kiểm tra UX/readability;
- kiểm tra progression, Outpost và economy;
- kiểm tra Plus/Pro có lợi thế nhẹ nhưng không phá cân bằng;
- xác định khi nào Vertical Slice đủ tốt để scale.

Nguyên tắc:
> Playtest để tìm vấn đề, telemetry để đo vấn đề, iteration để xác minh giải pháp.

Các con số là prototype targets, chưa phải balance cuối.

---

# I. TEST GROUPS

### First-Time Player
Test onboarding, tutorial, first mission, readability.

### Returning Player
Test progression, upgrade, Outpost, replayability.

### Strategy-Oriented Player
Test counter, loadout, logistics, Tower modularity.

### Casual Mobile Player
Test cognitive load, control, readability và friction.

---

# II. TEST STAGES

## Stage 1 — Internal Prototype
- blocker;
- bugs;
- core loop.

## Stage 2 — Closed Playtest
- combat;
- UX;
- difficulty;
- progression.

## Stage 3 — Vertical Slice Validation
Quyết định slice có đủ tốt để scale hay chưa.

## Stage 4 — Pre-Production Balance
Mở rộng mission/biome sau khi slice đã chứng minh được công thức.

---

# III. CORE KPI

## Mission Duration

Chuẩn:
> 5–15 phút.

Target:
- Early: 5–8 phút
- Mid: 7–12 phút
- Late: 9–15 phút
- Boss: 10–15 phút

Nếu median >15 phút:
- kiểm tra downtime;
- travel;
- wave gap;
- waiting.

Không chỉ giảm HP quái.

## Failure Clarity

Sau khi thua, người chơi phải hiểu:
> “Mình thua vì cái gì?”

Theo dõi:
- failure reason;
- restart;
- quit;
- retry.

---

# IV. COMBAT QUALITY

Theo dõi meaningful decisions:
- thay build;
- ưu tiên target;
- dùng tactical ability;
- repair;
- điều phối resource;
- xử lý emergency.

Nếu người chơi build một lần rồi không thay đổi cả trận:
> có thể combat chưa đủ reactive.

Nếu người chơi liên tục bấm cảnh báo:
> có thể cognitive load quá cao.

---

# V. TOWER BALANCE

Đánh giá mỗi Tower trên:
1. Damage contribution
2. Utility contribution
3. Resource efficiency
4. Positioning value
5. Strategic uniqueness

Không chỉ nhìn DPS.

### Test
- basic build;
- module specialization;
- hard counter;
- wrong matchup;
- resource pressure.

Mục tiêu:
> Tower có identity ngay cả khi không gặp matchup lý tưởng.

---

# VI. MODULE BALANCE

Module ưu tiên:
- trade-off;
- interaction;
- playstyle;
- alternative.

Ví dụ:
- Overclock: fire rate ↑, heat ↑
- Cooling: heat ↓, resource demand có thể ↑
- Utility: damage ↓, control ↑

Không biến mọi module thành:
> +10% damage.

---

# VII. ENEMY BALANCE

Mỗi enemy đánh giá:
- Threat;
- Readability;
- Counter availability;
- Punishment;
- Frequency;
- Interaction.

Người chơi cần biết:
1. Đây là quái gì?
2. Nó nguy hiểm ở đâu?
3. Nên dùng gì?

Nếu chỉ hiểu câu 1:
> visual/telegraph chưa đủ.

---

# VIII. FIXED ROSTER

Mỗi mission có roster cố định.

Kiểm tra:

Roster
→ Expected Counter
→ Available Towers
→ Player Knowledge
→ Difficulty

Không yêu cầu người chơi dùng một counter chưa được mở, trừ deliberate challenge.

---

# IX. WAVE BALANCE

Đánh giá:
- Density;
- Composition;
- Timing;
- Lane pressure;
- Escalation;
- Recovery.

Nhịp mẫu:
> Pressure → Peak → Recovery → New Threat → Escalation

Không:
> Peak → Peak → Peak → Peak.

---

# X. BOSS BALANCE

Boss phải có:
- readability;
- phase clarity;
- counter relevance;
- arena interaction;
- recovery window.

Boss không được là HP sponge.

Test:
1. Blind first attempt.
2. Sau khi hiểu counter.
3. Skilled player.
4. Resource pressure.

Nếu người chơi chết vì mechanic không thể hiểu:
> sửa communication trước khi giảm damage.

---

# XI. DIFFICULTY CURVE

Tăng theo:
> Complexity → Pressure → Combination

Không chỉ:
> HP ↑ / Damage ↑

### Early
Build, targeting, resource.

### Mid
Counter, logistics, disruption.

### Late
Multi-threat, adaptation, compound interactions.

### Boss
Tổng hợp kiến thức.

---

# XII. MISSION LESSON

Mỗi mission phải có một “lesson”.

Ví dụ:
- Mission 01: Build basics
- Mission 02: Priority target
- Mission 03: Armor counter
- Mission 04: Logistics redundancy
- Mission 05: Combine systems

Playtest phải xác minh người chơi thực sự học được lesson đó.

---

# XIII. RESOURCE BALANCE

Battle resources:
- Ammo;
- Power;
- Water;
- Scrap.

Theo dõi:
- starting;
- earned;
- spent;
- wasted;
- shortage;
- overflow;
- emergency demand.

Healthy loop:
> Scarcity → Decision → Action → Recovery

Tránh:
> Scarcity → Helplessness → Death

---

# XIV. LOGISTICS BALANCE

Logistics phải tạo decision chứ không tạo busywork.

Theo dõi:
- ammo shortages;
- power shortages;
- water shortages;
- route repairs;
- time spent on logistics.

Nếu logistics chiếm phần lớn sự chú ý:
> giảm complexity.

Nếu logistics gần như không ảnh hưởng trận:
> tăng strategic relevance.

---

# XV. COMMANDER BALANCE

Commander phải:
- giải quyết emergency;
- mở playstyle;
- hỗ trợ strategy;
- không autoplay.

Theo dõi:
- skill use;
- successful save;
- panic usage;
- missed opportunities;
- perceived value.

---

# XVI. OBJECTIVES

Secondary objectives phải:
- optional nhưng đáng làm;
- thúc đẩy gameplay;
- không ép chơi ngược strategy.

Không tạo objective tự mâu thuẫn với mission.

---

# XVII. PROGRESSION PLAYTEST

Sau mỗi session:
> Người chơi có biết mình vừa unlock gì không?

Theo dõi:
- unlock comprehension;
- upgrade rate;
- time between upgrades;
- resource bottleneck;
- return to Outpost.

Healthy loop:
> Mission → Reward → Visible Progress → New Option → New Mission

---

# XVIII. OUTPOST PLAYTEST

Kiểm tra:
- người chơi có quay về Outpost;
- có nhận thấy visual growth;
- có hiểu building;
- upgrade có ý nghĩa;
- Outpost có thành menu phức tạp không.

Mục tiêu:
> “Đây là căn cứ của mình.”

---

# XIX. UI / UX TEST

Người chơi phải dễ thấy:
- objective;
- roster;
- current wave;
- resource shortage;
- critical danger;
- ability;
- victory condition.

Nếu người chơi bấm sai:
> phải hiểu vì sao.

Đừng tối ưu “ít click” một cách máy móc; mỗi bước cần có giá trị.

---

# XX. VFX / AUDIO TEST

Hai trạng thái:
- Normal;
- Peak Combat.

Nếu tester nói:
> “Đẹp nhưng không biết chuyện gì đang xảy ra”

→ VFX fail readability.

Boss audio phải giúp:
- báo phase;
- báo attack;
- tăng tension.

---

# XXI. ECONOMY BALANCE

Mỗi mission cần:
- immediate reward;
- progression reward;
- occasional aspirational reward.

Cost phải tạo choice/planning, không tạo:
> wait wall hoặc paywall.

Theo dõi:
- earn/spend;
- overflow;
- bottleneck;
- reward per session;
- time to next unlock.

---

# XXII. PLUS / PRO BALANCE

Plus/Pro có thể có lợi thế PvE nhẹ theo design.

Đo:
- clear time;
- failure rate;
- resource efficiency;
- tactical option count;
- perceived value.

Không cho:
- massive damage advantage;
- unavoidable PvP advantage;
- exclusive required campaign counter.

PvP cạnh tranh:
> stat nhạy cảm cần được normalize/limit.

---

# XXIII. MONETIZATION TEST

Không chỉ đo purchase rate.

Đo:
- player understanding;
- purchase regret;
- progression disruption;
- ad fatigue;
- retention sau purchase.

Rewarded Ads:
- opt-in;
- không khóa core progression;
- không ép interruption quá mức.

---

# XXIV. PLAYER FEEDBACK

Sau session:

1. Fun
2. Clarity
3. Strategy
4. Difficulty
5. Progression
6. Desire to continue

Luôn hỏi:
> “Điều gì làm bạn cảm thấy vậy?”

---

# XXV. OBSERVATION

Observer ghi:
- first meaningful action;
- hesitation;
- wrong tap;
- confusion;
- unused mechanic;
- panic;
- successful adaptation;
- repeated failure;
- UI search;
- verbal reaction.

Không hướng dẫn trong first-play observation.

---

# XXVI. SESSION FLOW

```text
Brief
→ Play
→ Observe
→ Short Interview
→ Telemetry Review
→ Classify Issue
→ Hypothesis
→ Change
→ Re-test
```

Không đổi quá nhiều biến cùng lúc.

---

# XXVII. ISSUE CLASSIFICATION

### A — Bug
Không chạy đúng thiết kế.

### B — UX Problem
Chạy đúng nhưng khó hiểu.

### C — Balance Problem
Hiểu đúng nhưng quá dễ/khó.

### D — Content Problem
Mission/wave/roster chưa tốt.

### E — Core Design Problem
Cơ chế không tạo giá trị.

### F — Performance Problem
Frame/memory/loading.

### G — Monetization Problem
Monetization phá progression/trust.

---

# XXVIII. ITERATION LOOP

> Observe → Identify → Hypothesis → Change ONE major variable → Re-test → Keep/Revert

Balance change log:

```text
Date
Build
Problem
Observed Data
Hypothesis
Change
Expected Result
Actual Result
Decision
```

---

# XXIX. BALANCE BUDGET

Mỗi mission có:

### Enemy Threat Budget
Tổng threat.

### Resource Budget
Tổng resource.

### Time Budget
5–15 phút.

### Attention Budget
Số lượng simultaneous alerts/threats.

### Cognitive Budget
Số lượng mechanic người chơi phải xử lý cùng lúc.

Game khó không đồng nghĩa với:
> bắt người chơi theo dõi quá nhiều thứ cùng lúc.

---

# XXX. RED FLAGS

Dừng scale content nếu:
- người chơi không biết vì sao thua;
- một build thắng gần như mọi mission;
- boss là HP sponge;
- mission thường xuyên >15 phút;
- người chơi bỏ qua core system;
- logistics thành busywork;
- UI che battlefield;
- VFX che critical info;
- progression không tạo cảm giác phát triển;
- Plus/Pro tạo cảm giác bắt buộc;
- save/reward thiếu tin cậy.

---

# XXXI. VERTICAL SLICE PASS CRITERIA

## Gameplay
- [ ] Core loop được hiểu.
- [ ] Có meaningful decisions.
- [ ] Tower counter hoạt động.
- [ ] Enemy identity rõ.
- [ ] Boss có learning/response.
- [ ] Mission chủ yếu nằm 5–15 phút.

## UX
- [ ] Người mới hiểu objective.
- [ ] Người chơi hiểu failure reason.
- [ ] Outpost progression rõ.
- [ ] Result → Upgrade → Next Mission liền mạch.

## Economy
- [ ] Reward loop hoạt động.
- [ ] Không có grind wall bất hợp lý.
- [ ] Cost/reward có thể scale.

## Visual
- [ ] Style nhất quán.
- [ ] Battlefield readable.
- [ ] VFX không che critical info.

## Technical
- [ ] Save ổn định.
- [ ] Không blocker crash.
- [ ] Performance profile có dữ liệu.

---

# XXXII. SCALE-READINESS CHECK

Trước khi từ 5 mission lên hàng chục mission:

- [ ] Mission template ổn.
- [ ] Fixed roster system ổn.
- [ ] Wave authoring đủ linh hoạt.
- [ ] Reward authoring data-driven.
- [ ] Difficulty controls có.
- [ ] Telemetry phân biệt mission.
- [ ] Balance change có thể revert.
- [ ] Content validation hoạt động.

---

# XXXIII. FINAL PRINCIPLE

> **Không scale một game chưa được chứng minh. Scale một công thức đã được chứng minh.**

Last Outpost chỉ nên mở rộng khi:

```text
Player Understands
+
Player Decides
+
Player Acts
+
Player Gets Feedback
+
Player Grows
+
Player Wants Another Run
```

Đó là chuẩn trước khi bắt đầu sản xuất content hàng loạt.
