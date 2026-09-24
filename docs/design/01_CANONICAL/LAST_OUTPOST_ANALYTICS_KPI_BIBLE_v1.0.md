# LAST OUTPOST — ANALYTICS & KPI BIBLE v1.0

**Document ID:** LO-AN-KPI-001  
**Version:** v1.0  
**Status:** Production Design Draft / KPI Framework  
**Language:** Vietnamese  
**Owner:** Game Design / Product Design  
**Primary Consumers:** Game Design, System Design, Content, UX/UI, Economy, QA, Production, Live Ops, Analytics, Claude Handoff  

---

## 0. MỤC ĐÍCH

Tài liệu này định nghĩa lớp **Analytics & KPI** của LAST OUTPOST ở cấp design / specification.

Mục tiêu không phải là thu thập càng nhiều dữ liệu càng tốt, mà là đảm bảo team có thể trả lời được các câu hỏi quan trọng:

- Người chơi có hiểu và chơi được core loop không?
- Mission có đang tạo đúng độ khó bằng composition, timing, terrain, hazards, logistics và resource pressure không?
- Người chơi thất bại vì quyết định chiến thuật, vì thiếu thông tin hay vì vấn đề UX?
- Progression có tạo cảm giác tiến bộ mà không biến thành grind không?
- Outpost có thực sự là persistent progression hub không?
- Economy có bị inflation, deflation hoặc bottleneck bất hợp lý không?
- Plus / Pro có tạo value nhưng vẫn nằm trong giới hạn PvE advantage đã chốt không?
- Live Ops có tạo nội dung bổ sung mà không phá core loop hoặc ép đăng nhập không?
- Save, performance và stability có gây mất tiến trình hoặc làm hỏng trải nghiệm không?

Analytics phải hỗ trợ **quyết định thiết kế**, không thay thế playtest và không biến metric thành mục tiêu tối ưu mù quáng.

---

# I. ANALYTICS PRINCIPLES

## 1.1. Gameplay trước, metric sau

Một KPI tăng không tự động đồng nghĩa với trải nghiệm tốt.

Ví dụ:

- Shop open rate tăng vì popup spam → không được xem là thành công.
- Session length tăng vì người chơi bị mắc kẹt ở loading → không phải retention improvement.
- Mission completion tăng vì difficulty bị giảm quá mạnh → cần kiểm tra quality của challenge.

Mọi metric quan trọng phải được đọc cùng **context gameplay + UX + qualitative feedback**.

---

## 1.2. Chỉ đo khi dữ liệu có câu hỏi sử dụng rõ ràng

Mỗi telemetry event phải có ít nhất một trong các mục đích:

1. Gameplay tuning.
2. UX diagnosis.
3. Content performance.
4. Economy/balance monitoring.
5. Monetization health.
6. Technical reliability.
7. Live Ops operations.
8. Save/recovery integrity.

Event không có decision use-case rõ ràng không mặc định được thêm.

---

## 1.3. Không dùng Analytics để thay thế Design

Analytics không được tự quyết định:

- core loop;
- terminology;
- visual identity;
- enemy roster;
- tower architecture;
- Outpost persistence;
- monetization philosophy;
- narrative direction.

Các thay đổi design vẫn phải thông qua design review / quyết định sản phẩm.

---

## 1.4. Privacy by default

LAST OUTPOST chỉ nên thu thập dữ liệu cần thiết cho gameplay/product/technical operation.

Không mặc định thu thập:

- nội dung riêng tư không cần thiết;
- dữ liệu thiết bị vượt quá nhu cầu chẩn đoán;
- dữ liệu vị trí chính xác nếu không phục vụ tính năng;
- hành vi ngoài game không liên quan;
- thông tin nhận diện cá nhân không cần thiết cho mục đích sản phẩm.

Analytics production phải tuân thủ chính sách nền tảng, pháp luật và consent requirements áp dụng tại từng thị trường.

---

# II. SOURCE OF TRUTH & ANALYTICS SCOPE

## 2.1. Nguồn thiết kế làm cơ sở

Analytics kế thừa các lớp thiết kế hiện có:

- `Last_Outpost_System_Gameplay_v1.1`
- `Last_Outpost_Core_Combat_Gameplay_v1.0`
- `Last_Outpost_Content_Specification_v1.0`
- `Last_Outpost_Economy_Design_v1.0`
- `Last_Outpost_Player_Experience_UX_Flow_v1.0`
- `Last_Outpost_UI_UX_Specification_v1.0`
- `Last_Outpost_AI_Simulation_Design_v1.0`
- `Last_Outpost_Save_Data_Design_v1.0`
- `Last_Outpost_Playtest_Balance_Specification_v1.0`
- `Last_Outpost_Toi_Uu_v1.2`
- Live Ops & Seasonal Content Bible v1.0
- Current Decisions trong Claude Handoff Pack.

## 2.2. Analytics không thay đổi design hiện hữu

Các quy tắc đã khóa tiếp tục được áp dụng:

- Mission: 5–15 phút.
- Enemy: Fixed Roster theo mission.
- Tower: Base + Barrel + Module.
- Outpost: persistent.
- Progression: Outpost + Tech Tree + Mastery + Blueprint.
- Monetization: Free → Plus → Pro.
- PvP/Guild: thấp hơn PvE core về priority.
- Visual: Stylized 2.5D Cartoon + Post-Apocalyptic Adventure + Tactical Strategy.

---

# III. KPI HIERARCHY

Analytics của LAST OUTPOST chia thành 6 nhóm chính.

| Nhóm | Mục tiêu |
|---|---|
| Product KPI | Đánh giá health tổng thể của sản phẩm |
| Gameplay KPI | Đánh giá combat, difficulty, strategy và content |
| UX KPI | Đánh giá clarity, friction, flow và usability |
| Economy KPI | Đánh giá reward, resource, progression và inflation |
| Monetization KPI | Đánh giá Free/Plus/Pro và monetization health |
| Technical KPI | Đánh giá performance, crashes, save và reliability |

Không có một KPI duy nhất được dùng làm “điểm số của game”.

---

# IV. PRODUCT KPI

## 4.1. North Star context

Không đặt một North Star duy nhất làm mục tiêu tối ưu toàn bộ hệ thống.

LAST OUTPOST nên đọc product health thông qua một **KPI cluster** gồm:

- New Player Activation.
- Mission Engagement.
- Core Loop Repeat.
- Progression Continuity.
- Retention.
- Stability.
- Monetization Health.

---

## 4.2. Activation

### Time to First Playable

Thời gian từ first launch tới khi người chơi thực sự bắt đầu gameplay có ý nghĩa đầu tiên.

Theo dõi:

- median;
- p75;
- first-session distribution.

Không tối ưu bằng cách bỏ các explanation cần thiết.

### First Mission Start Rate

`first_mission_start / eligible_new_players`

Phân tích thêm:

- onboarding completion;
- first Outpost visit;
- briefing exit;
- loadout friction;
- loading failure.

### First Mission Completion Rate

`players completing first mission / players starting first mission`

Cần tách:

- Victory;
- Defeat;
- Abandon;
- technical termination.

---

## 4.3. Core Loop Engagement

Theo dõi số người thực hiện chuỗi:

`Outpost → Prepare → Mission → Result → Progression → Outpost`

Metric quan trọng:

- mission start per active player;
- completed mission per active player;
- repeat mission rate;
- next mission continuation;
- progression action after mission;
- return-to-Outpost rate;
- session ending after result.

---

## 4.4. Retention

Theo dõi cohort retention theo:

- first playable date;
- first mission date;
- platform;
- acquisition bucket nếu hợp lệ;
- Free / Plus / Pro nếu đã đủ sample;
- player progression stage.

Các mốc tiêu chuẩn cần hỗ trợ:

- D1;
- D3;
- D7;
- D14;
- D30;
- D60;
- D90.

Các mốc trên là reporting convention, không phải target bắt buộc.

### Retention phải đọc cùng progression

Không chỉ hỏi “người chơi còn quay lại không”, mà phải hỏi:

- họ quay lại ở Outpost stage nào;
- sau mission type nào;
- sau Victory hay Defeat;
- sau unlock nào;
- sau event nào;
- có bị stuck progression không.

---

# V. STANDARD ANALYTICS EVENT MODEL

## 5.1. Event naming

Tên event dùng `snake_case`.

Convention:

`domain_action`

Ví dụ:

- `session_start`
- `mission_start`
- `mission_result`
- `tower_place`
- `tower_upgrade`
- `outpost_upgrade_complete`
- `shop_view`
- `purchase_complete`
- `save_sync_success`

Không dùng tên quá chung như:

- `event1`;
- `click_button`;
- `misc_action`.

---

## 5.2. Event categories

### Session

- `session_start`
- `session_end`
- `app_background`
- `app_foreground`

### Onboarding

- `onboarding_start`
- `onboarding_step_complete`
- `onboarding_skip`
- `onboarding_complete`

### Navigation / UX

- `screen_open`
- `screen_exit`
- `alert_shown`
- `alert_action`
- `tutorial_hint_shown`
- `tutorial_hint_action`

### Mission

- `mission_view`
- `mission_start`
- `mission_prepare`
- `mission_abandon`
- `mission_result`
- `mission_retry`
- `objective_progress`
- `objective_complete`
- `hazard_trigger`
- `world_event_modifier_applied`

### Combat

- `wave_start`
- `wave_complete`
- `enemy_spawn_group`
- `enemy_eliminated`
- `tower_place`
- `tower_remove`
- `tower_upgrade`
- `module_change`
- `commander_action`
- `commander_down`
- `tower_destroyed`
- `base_damage_taken`
- `core_critical`
- `mission_defeat`

### Progression

- `xp_awarded`
- `mastery_progress`
- `mastery_unlock`
- `blueprint_fragment_gained`
- `blueprint_completed`
- `tech_unlock`
- `outpost_upgrade_start`
- `outpost_upgrade_complete`
- `expedition_start`
- `expedition_complete`

### Economy

- `resource_gain`
- `resource_spend`
- `reward_claim`
- `currency_exchange`
- `event_token_gain`
- `event_token_spend`

### Shop / Monetization

- `shop_view`
- `item_view`
- `purchase_start`
- `purchase_complete`
- `purchase_cancel`
- `entitlement_grant`
- `plus_view`
- `pro_view`
- `subscription_start`
- `subscription_renew`
- `subscription_cancel`
- `rewarded_ad_offer`
- `rewarded_ad_opt_in`
- `rewarded_ad_complete`

### Live Ops

- `season_view`
- `season_progress`
- `season_reward_claim`
- `event_view`
- `event_start`
- `event_progress`
- `event_complete`
- `event_reward_claim`
- `comeback_summary_view`
- `comeback_reward_claim`

### Save / Technical

- `save_checkpoint`
- `save_sync_start`
- `save_sync_success`
- `save_sync_failure`
- `save_conflict_detected`
- `save_recovery`
- `crash_detected`
- `loading_complete`
- `loading_timeout`

---

# VI. EVENT PROPERTY STANDARD

Mỗi event không cần chứa toàn bộ dữ liệu người chơi.

## 6.1. Common properties

Chỉ dùng những property phù hợp:

- `event_time`
- `session_id`
- `anonymous_player_id` hoặc identifier được policy phê duyệt
- `platform`
- `app_version`
- `content_version`
- `locale`
- `mode`
- `season_id` nếu có
- `event_id` nếu có event/live operation context

## 6.2. Gameplay context

Khi phù hợp:

- `mission_id`
- `mission_type`
- `chapter_id`
- `biome_id`
- `difficulty_tier`
- `seed_id` hoặc scenario identifier nếu được phép
- `wave_index`
- `objective_id`
- `commander_id`
- `tower_id`
- `base_id`
- `barrel_id`
- `module_id`
- `enemy_id`
- `hazard_id`

Không gửi toàn bộ runtime state nếu chỉ cần vài trường để phân tích.

## 6.3. Result context

Khi phù hợp:

- `result`
- `duration_seconds`
- `attempt_number`
- `retry_count`
- `abandon_stage`
- `resource_spent`
- `resource_earned`
- `mastery_earned`
- `reward_profile_id`

---

# VII. MISSION METRICS

Mission là đơn vị gameplay quan trọng nhất của analytics.

## 7.1. Mission funnel

Theo dõi:

`mission_view → mission_prepare → mission_start → gameplay → mission_result`

Phân biệt rõ:

- player viewed but never prepared;
- prepared but never started;
- started then abandoned;
- started then technical exit;
- completed Victory;
- completed Defeat.

---

## 7.2. Duration

Metric:

- median mission duration;
- p25;
- p75;
- p90;
- duration by mission ID;
- duration by mission type;
- duration by difficulty;
- duration by player progression stage.

Mission target theo design vẫn là **5–15 phút**.

Không dùng average đơn độc vì swarm/boss mission có thể có distribution lệch.

---

## 7.3. Completion

Theo dõi:

- Victory rate;
- Defeat rate;
- Abandon rate;
- Retry after defeat;
- Replay after Victory;
- Objective completion rate;
- Secondary challenge completion rate.

### Failure classification

Mỗi defeat nên cố gắng phân loại theo cause chính:

- Base/Core destroyed;
- Commander down;
- timeout nếu objective có timer;
- resource failure;
- logistics failure;
- hazard;
- wave pressure;
- tactical misplay không gắn được cause cụ thể;
- technical termination.

Không ép mọi failure vào một cause nếu dữ liệu không đủ chắc chắn.

---

# VIII. COMBAT METRICS

## 8.1. Wave metrics

- wave start interval;
- wave clear time;
- threat budget consumed;
- peak active enemies;
- enemy survival time;
- spawn-to-target time;
- target switches;
- wave abandon point.

## 8.2. Enemy metrics

Theo Fixed Roster:

- spawn count;
- elimination count;
- survival duration;
- damage dealt;
- damage received nếu cần balance;
- target selection;
- special ability activation;
- special ability success/failure;
- contribution to defeat.

Không dùng analytics để spawn thêm species ngoài roster của mission.

## 8.3. Tower metrics

Theo `Base + Barrel + Module`:

- placement rate;
- placement position category;
- remove/rebuild rate;
- module usage;
- upgrade timing;
- damage contribution nếu available;
- heat/overheat occurrence nếu có;
- ammo/power pressure;
- tower survival;
- tower replacement.

Mục tiêu là phát hiện:

- component quá mạnh;
- component không được hiểu;
- component có utility nhưng không được sử dụng;
- build pattern bị dominant;
- tower bị bỏ vì friction chứ không phải vì balance.

---

# IX. TACTICAL READABILITY METRICS

Vì audio, VFX và UX được xem là gameplay readability layer, cần theo dõi:

- warning shown → action rate;
- warning shown → correct action rate khi có thể đo;
- alert ignore rate;
- hazard reaction delay;
- telegraph-to-impact survival rate;
- UI warning interaction;
- repeated warning occurrence before player adaptation.

Ví dụ:

`alert_shown → alert_action → hazard outcome`

không chỉ đo `alert_shown`.

Một warning xuất hiện 100% nhưng player không bao giờ phản ứng không phải là warning thành công.

---

# X. DIFFICULTY METRICS

## 10.1. Difficulty health

Mỗi mission cần được nhìn qua cluster:

- completion rate;
- defeat rate;
- abandon rate;
- retry rate;
- duration;
- resource remainder;
- objective clarity;
- common failure cause;
- tower/build diversity;
- Commander selection distribution.

## 10.2. Difficulty diagnosis

### Quá dễ có thể biểu hiện bằng

- completion rate rất cao;
- duration thấp;
- resource pressure thấp;
- rất ít retry;
- tower diversity thấp vì một build đủ cho mọi tình huống.

### Quá khó có thể biểu hiện bằng

- defeat tăng mạnh;
- abandon tăng;
- retry giảm bất thường vì player bỏ hẳn;
- một failure cause chiếm áp đảo;
- progression stall sau một mission;
- build diversity giảm do player phải dùng một answer duy nhất.

Các dấu hiệu trên là **diagnostic signals**, không phải tự động kết luận.

---

# XI. PROGRESSION METRICS

## 11.1. Outpost

Theo dõi:

- time to first Outpost upgrade;
- time between upgrades;
- upgrade completion rate;
- resource readiness for upgrade;
- upgrade cancel/abandon nếu có flow tương ứng;
- Outpost stage distribution;
- building interaction rate;
- progression stall points.

## 11.2. Tech Tree

- node view;
- node unlock attempt;
- node unlock complete;
- time from prerequisite completion → unlock;
- node adoption;
- branch abandonment;
- branch concentration.

## 11.3. Mastery

- mastery XP per session;
- mastery XP by content source;
- time to milestone;
- mastery milestone claim;
- usage after mastery unlock.

Mastery analytics phải tránh biến mastery thành grind KPI.

## 11.4. Blueprint

- fragment acquisition rate;
- fragment source;
- fragment loss/recovery incidents;
- blueprint completion time;
- craft/unlock conversion;
- post-unlock usage.

---

# XII. ECONOMY KPI

Economy metrics phải được đọc theo **source → stock → sink → progression result**.

## 12.1. Income

Theo dõi theo resource:

- Scrap;
- Ammo;
- Power;
- Water;
- premium currency;
- Event Token;
- Blueprint Fragment;
- Mastery XP.

Metric:

- income per mission;
- income per minute;
- income per session;
- income by source;
- average stock;
- stock distribution.

## 12.2. Spend

- spend per mission;
- spend per minute;
- spend by sink;
- spend by progression stage;
- unspent balance;
- time from reward → spend.

## 12.3. Economy health

Theo dõi:

- resource inflation;
- reward saturation;
- resource scarcity;
- bottleneck resource;
- time to unlock;
- time to Outpost milestone;
- blueprint completion time.

### Không dùng single-player “average balance” làm nguồn sự thật duy nhất

Phân phối thấp/trung/cao percentile quan trọng hơn một con số average đơn độc.

---

# XIII. REWARD METRICS

Mission reward:

- reward claimed;
- reward value by category;
- claim delay;
- claim failure;
- reward source;
- reward conversion into progression.

Theo dõi đặc biệt:

`Reward → Spend/Use → Progression`

Nếu người chơi nhận reward nhưng không bao giờ dùng, cần kiểm tra:

- reward relevance;
- reward visibility;
- inventory cap;
- sink availability;
- progression pacing.

---

# XIV. SHOP & MONETIZATION KPI

## 14.1. Shop funnel

`shop_view → item_view → purchase_start → purchase_complete`

Tách:

- cosmetic;
- convenience;
- Plus;
- Pro;
- event shop;
- rewarded ads.

## 14.2. Plus / Pro

Theo dõi tổng hợp:

- view rate;
- conversion rate;
- renewal rate;
- cancellation rate;
- entitlement usage;
- PvE progression comparison;
- tactical option usage.

Không chỉ đo doanh thu.

Phải theo dõi chênh lệch Free / Plus / Pro để kiểm tra giới hạn advantage đã chốt.

## 14.3. Monetization guardrails

Flag khi có dấu hiệu:

- core progression bị chặn bởi paywall;
- paid option trở thành answer bắt buộc của mission;
- purchase pressure tăng trong combat;
- monetization popup chen primary action;
- PvE advantage vượt design boundary;
- PvP normalization không còn đảm bảo fairness.

## 14.4. Rewarded Ads

Theo dõi:

- offer shown;
- opt-in rate;
- completion rate;
- reward claim;
- repeat use;
- ad-driven frustration indicators.

Rewarded Ads là voluntary.

Không dùng “ad starts” làm KPI duy nhất.

---

# XV. LIVE OPS KPI

## 15.1. Daily / Weekly

Theo dõi:

- participation;
- completion;
- reward claim;
- repeat participation;
- abandonment;
- progression contribution;
- player return after activity.

Không coi frequency cao tự động là thành công.

## 15.2. Seasonal Event

- event view rate;
- event entry rate;
- participation;
- completion;
- token acquisition;
- token spend;
- reward claim;
- event shop utilization;
- event abandonment;
- post-event retention.

## 15.3. World Event

Đặc biệt theo dõi:

- exposure rate;
- modifier encounter rate;
- counterplay usage;
- failure contribution;
- player understanding;
- event fatigue signals.

World Event vẫn phải là lớp bổ sung trên core gameplay, không trở thành game mode khác.

## 15.4. Comeback

- comeback offer shown;
- summary viewed;
- rewards claimed;
- first mission after return;
- progression recovery;
- next-session return.

Không tối ưu comeback bằng spam notification.

---

# XVI. UX KPI

## 16.1. Core UX metrics

Kế thừa các metric UX đã có:

- Time to First Playable;
- Time to First Successful Mission;
- Tutorial Completion Rate;
- Mission Abandon Rate;
- Briefing Time;
- Prepare Time;
- Combat Duration;
- Retry Rate;
- Loadout Reuse Rate;
- Alert Interaction Rate;
- Tech Tree Interaction;
- Outpost Upgrade Conversion;
- Reward Claim Latency;
- Shop Open Rate;
- Plus/Pro View → Purchase Conversion;
- Rewarded Ad Opt-in Rate.

## 16.2. UX friction signals

- backtracking nhiều;
- repeated screen opens;
- repeated tutorial hints;
- invalid placement attempts;
- repeated alert dismiss;
- purchase funnel drop-off;
- reward claim delay;
- mission start cancellation;
- excessive loading;
- repeated reconnect.

Không phải friction nào cũng xấu.

Ví dụ:

- một lần inspect Tower để hiểu nó → friction hợp lý;
- 7 lần mở cùng một panel để tìm nút Start Mission → friction xấu.

---

# XVII. SESSION ANALYTICS

## 17.1. Session duration

Theo dõi:

- median;
- p25;
- p75;
- p90.

Phân loại:

- very short;
- one-mission session;
- multi-mission session;
- long Outpost session.

Không đặt mục tiêu bắt người chơi chơi lâu.

## 17.2. Session composition

Cần biết session được tạo bởi:

- Outpost;
- preparation;
- combat;
- result;
- progression;
- shop;
- social/guild nếu có;
- event.

Cần phát hiện trường hợp:

`shop time > gameplay time`

mà không phải do player chủ động.

---

# XVIII. CHURN & STALL DIAGNOSTICS

## 18.1. Churn signals

Không chỉ dùng “không mở app”.

Theo dõi trước churn:

- mission defeat streak;
- repeated mission abandonment;
- progression stall;
- Outpost resource shortage;
- tutorial drop-off;
- shop/monetization pressure;
- crash/reconnect frequency;
- content repetition.

## 18.2. Progression stall

Một player được xem là có **stall signal** khi có dấu hiệu kết hợp như:

- không mở khóa progression trong nhiều session;
- không đủ resource trong thời gian dài;
- cùng một mission được retry nhiều lần;
- progression action rate giảm;
- session vẫn còn nhưng forward progression gần bằng 0.

Threshold cụ thể phải được calibrate sau playtest.

---

# XIX. PERFORMANCE KPI

## 19.1. Runtime

Theo platform/device bucket:

- average frame rate nếu khả dụng;
- frame-time percentile;
- worst frame spikes;
- memory usage;
- CPU usage;
- GPU pressure;
- thermal throttling signal nếu platform cho phép theo dõi an toàn.

## 19.2. Combat performance

Theo scenario:

- peak active enemies;
- peak VFX count;
- peak projectile activity;
- wave transition cost;
- boss phase cost;
- hazard cost;
- AI update cost.

AI telemetry đã xác định các chỉ số:

- time-to-target;
- target switches;
- path recalculations;
- average AI update cost;
- enemy survival time;
- boss phase duration;
- CC uptime;
- EMP hit count;
- Burrower emergence success;
- Corpse Bridge events;
- deaths by cause.

Analytics phải cho phép liên kết các chỉ số này với scenario/mission để tìm bottleneck.

---

# XX. STABILITY KPI

## 20.1. Crash

Theo dõi:

- crash-free sessions;
- crash-free users;
- crash rate by app version;
- crash rate by device bucket;
- crash during mission;
- crash during reward claim;
- crash during save/sync.

## 20.2. ANR / freeze / timeout

Theo dõi:

- ANR rate nếu nền tảng hỗ trợ;
- loading timeout;
- sync timeout;
- long frame / freeze;
- reconnect failure.

### Production rule

Một phiên chơi bị technical termination phải được phân biệt với:

- voluntary abandon;
- defeat;
- background app.

Nếu không, difficulty KPI sẽ bị sai.

---

# XXI. SAVE / DATA INTEGRITY KPI

Save system phải recovery-safe.

Theo dõi:

- checkpoint success;
- sync success;
- sync failure;
- retry;
- save conflict;
- recovery success;
- recovery failure;
- duplicate transaction detection;
- missing reward incident;
- progression rollback incident;
- corrupted snapshot detection nếu có.

## 21.1. Critical transactions

Ưu tiên theo dõi transaction có impact lớn:

- purchase;
- premium currency;
- Plus/Pro entitlement;
- Blueprint fragment transaction;
- Blueprint unlock;
- season reward claim;
- event reward claim;
- major Outpost upgrade.

Một transaction quan trọng phải có khả năng audit bằng transaction/reference ID theo Save/Data Design.

---

# XXII. CONTENT PERFORMANCE

Mỗi content unit cần có ID ổn định:

- Mission ID;
- Enemy ID;
- Tower ID;
- Module ID;
- Commander ID;
- Biome ID;
- Event ID;
- Reward Profile ID;
- Season ID.

## 22.1. Mission content health

Dashboard tối thiểu:

| Metric | Purpose |
|---|---|
| Start rate | Người chơi có chọn mission không |
| Completion | Mission có vượt qua được không |
| Defeat | Difficulty signal |
| Abandon | Friction/churn signal |
| Duration | Pacing |
| Retry | Learning / frustration signal |
| Dominant build | Balance signal |
| Dominant failure | Counter/readability signal |
| Reward claim | Reward relevance |

## 22.2. Enemy content health

Theo enemy:

- appearance count;
- elimination share;
- threat contribution;
- death cause;
- special ability success;
- player counter usage.

Mục tiêu là kiểm tra enemy có “đúng vai trò” hay không, không phải ép mọi enemy có usage bằng nhau.

---

# XXIII. A/B TESTING & EXPERIMENT RULES

Analytics không mặc định đồng nghĩa A/B testing.

Experiment chỉ dùng khi:

- hypothesis rõ;
- metric chính rõ;
- metric guardrail rõ;
- sample đủ để diễn giải;
- không ảnh hưởng integrity của persistent progression.

## 23.1. Ví dụ hypothesis

“Giảm số bước trong Mission Briefing làm tăng First Mission Start Rate mà không giảm Objective Comprehension.”

Primary metric:

- Mission Start Rate.

Guardrails:

- Mission Abandon Rate;
- Defeat Rate;
- Tutorial misunderstanding signals.

## 23.2. Không experiment trên

Các quyết định core đã khóa nếu chưa có design review riêng:

- Fixed Enemy Roster;
- persistent Outpost;
- Tower architecture;
- core battle duration target;
- visual identity;
- monetization principles.

---

# XXIV. DASHBOARD STRUCTURE

## Dashboard 01 — Executive / Product Health

- DAU/WAU/MAU nếu được sử dụng;
- activation;
- retention;
- mission engagement;
- progression continuity;
- crash-free;
- monetization health.

## Dashboard 02 — Gameplay

- mission duration;
- completion/defeat/abandon;
- wave metrics;
- dominant builds;
- enemy performance;
- Commander usage;
- objective performance.

## Dashboard 03 — Difficulty

- mission difficulty distribution;
- failure causes;
- retry patterns;
- resource pressure;
- stuck points.

## Dashboard 04 — Progression & Outpost

- Outpost stage;
- upgrade cadence;
- Tech Tree;
- Mastery;
- Blueprint;
- progression stall.

## Dashboard 05 — Economy

- resource income;
- spend;
- stock;
- sink/source;
- inflation;
- reward efficiency.

## Dashboard 06 — Monetization

- shop funnel;
- Plus;
- Pro;
- rewarded ads;
- entitlement usage;
- Free/Plus/Pro comparison.

## Dashboard 07 — Live Ops

- event participation;
- event completion;
- Event Token flow;
- event shop;
- season progress;
- comeback.

## Dashboard 08 — Technical

- crash;
- ANR/freeze;
- loading;
- performance;
- save/sync;
- recovery.

---

# XXV. ALERTING & THRESHOLD POLICY

Không đặt hàng trăm alert.

Chia 3 mức:

### P0 — Immediate

Có thể ảnh hưởng:

- mất progression;
- purchase integrity;
- widespread crash;
- save corruption;
- live event broken.

### P1 — Action Required

Có thể ảnh hưởng nghiêm trọng đến:

- mission completion;
- economy stability;
- major UX funnel;
- performance on supported devices.

### P2 — Monitor

Các deviation chưa cần hotfix ngay nhưng cần theo dõi:

- unusual usage pattern;
- content imbalance;
- reward shift;
- shop conversion fluctuation.

Threshold số cụ thể phải dựa trên baseline thực tế sau launch, không bịa trước khi có population đủ lớn.

---

# XXVI. DATA QUALITY

## 26.1. Event quality checks

Mỗi event production phải kiểm tra:

- event name hợp lệ;
- required property có mặt;
- enum hợp lệ;
- version hợp lệ;
- timestamp hợp lệ;
- không duplicate bất thường;
- không gửi PII ngoài policy;
- schema version tương thích.

## 26.2. Impossible state detection

Flag khi gặp các trạng thái phi logic như:

- reward claimed > reward granted;
- mission result trước mission start;
- purchase complete không có purchase start nếu flow yêu cầu;
- blueprint complete nhưng fragment state không khớp;
- Outpost stage giảm ngoài migration/recovery;
- event reward được claim sau expiry mà không có rule cho phép.

---

# XXVII. ANALYTICS & GAME DESIGN REVIEW LOOP

Analytics không tự thay đổi game.

Quy trình đề xuất:

`Signal → Diagnosis → Hypothesis → Design Review → Test → Decision → Documentation`

## 27.1. Signal

Một metric deviates hoặc qualitative feedback lặp lại.

## 27.2. Diagnosis

Tìm nguyên nhân bằng cách kết hợp:

- telemetry;
- replay/test;
- playtest;
- QA;
- UX observation;
- economy review.

## 27.3. Hypothesis

Viết rõ:

- vấn đề;
- nguyên nhân giả định;
- thay đổi dự kiến;
- metric primary;
- guardrails.

## 27.4. Decision

Sau test:

- Keep;
- Adjust;
- Revert;
- Reject.

Nếu thay đổi một design decision đã khóa, phải cập nhật Current Decisions / Master Design Index sau khi được approve.

---

# XXVIII. MINIMUM ANALYTICS FOR VERTICAL SLICE

Vertical Slice không cần toàn bộ analytics production.

P0 tối thiểu:

### Session

- `session_start`
- `session_end`

### Mission

- `mission_view`
- `mission_start`
- `mission_abandon`
- `mission_result`

### Combat

- `wave_start`
- `enemy_spawn_group`
- `enemy_eliminated`
- `tower_place`
- `tower_remove`
- `module_change`
- `hazard_trigger`

### Progression

- `reward_claim`
- `tech_unlock`
- `outpost_upgrade_complete`
- `mastery_progress`

### UX

- `screen_open`
- `alert_shown`
- `alert_action`

### Technical

- `loading_complete`
- `loading_timeout`
- `crash_detected`
- `save_checkpoint`
- `save_sync_success`
- `save_sync_failure`

Mục tiêu của Vertical Slice analytics là chứng minh rằng team có thể trả lời:

1. Player vào mission thế nào?
2. Họ hoàn thành hay thất bại?
3. Họ thất bại vì đâu?
4. Build nào đang được dùng?
5. Reward có dẫn tới progression không?
6. Có friction UX nào rõ ràng không?
7. Có performance/save issue nào không?

---

# XXIX. PRODUCTION MINIMUM ANALYTICS

Sau khi core systems ổn định, mở rộng sang:

- full economy events;
- mastery;
- Blueprint;
- Plus / Pro;
- Rewarded Ads;
- seasonal event;
- World Event;
- comeback;
- detailed AI telemetry;
- content performance;
- cohort retention.

Không cần bật toàn bộ event ngay ngày đầu nếu chưa có dashboard/consumer tương ứng.

---

# XXX. ANALYTICS ACCESS BY ROLE

| Role | Primary analytics |
|---|---|
| Game Designer | Gameplay, difficulty, mission, enemy, tower |
| System Designer | Progression, economy, mastery, blueprint |
| Content Designer | Mission/content performance, event |
| UX/UI | Funnel, friction, alerts, flow |
| Economy Designer | Sources, sinks, inflation, reward |
| Live Ops | Season, event, comeback, reward |
| Monetization | Shop, Plus, Pro, rewarded ads |
| QA | Crash, impossible states, save, reproducibility |
| Production | Product health, release health, blocker trends |
| Claude | Specifications + schema intent; implementation only after design handoff |

---

# XXXI. RED FLAGS

## Gameplay

- Một mission chỉ có một build viable.
- Một enemy gây phần lớn defeat mà player không có counterplay rõ.
- Abandon tăng mạnh nhưng Defeat không tăng tương ứng.
- Duration vượt target do pacing chứ không phải tactical depth.

## UX

- Shop interactions tăng nhưng mission starts giảm.
- Alert count tăng nhưng reaction rate giảm.
- Briefing time tăng nhưng objective understanding không tăng.

## Economy

- Reward tăng nhưng progression speed không tăng.
- Resource stock tăng liên tục nhưng sink không hấp thụ.
- Một resource trở thành bottleneck của gần như toàn bộ player.

## Monetization

- Free player progression bị chặn rõ rệt ở nơi Plus/Pro giải quyết.
- Paid tactical option trở thành requirement để clear mission.
- Monetization pressure tăng ngay trước combat-critical moment.

## Live Ops

- Người chơi phải đăng nhập quá thường xuyên để không mất value.
- Seasonal rewards ảnh hưởng permanent progression quá mạnh.
- Event Token tạo inflation hoặc inventory clutter.

## Technical

- Crash-free giảm theo version mới.
- Save conflict tăng sau content rollout.
- Performance degrade theo swarm size.
- Loading time tăng dù content scope không tăng tương ứng.

---

# XXXII. METRIC INTERPRETATION RULES

1. Một metric không được đọc một mình khi có thể gây hiểu sai.
2. Median và percentile thường quan trọng hơn average cho time-based metrics.
3. Cohort phải giữ đúng population và time window.
4. Free / Plus / Pro phải được tách khi phân tích monetization effect.
5. Mission comparison phải xét mission type/difficulty/progression stage.
6. Live event comparison phải xét event scope và reward design.
7. Technical metrics phải phân tầng platform/device/version.
8. Correlation không được mặc định là causation.
9. Metric movement phải được kiểm tra với qualitative evidence.
10. Analytics chỉ hỗ trợ quyết định; không tự động quyết định design.

---

# XXXIII. DEFINITIONS OF DONE

Analytics framework được xem là đạt khi:

### Product

- [ ] Activation có thể đo.
- [ ] Retention theo cohort có thể đo.
- [ ] Core loop engagement có thể đo.

### Gameplay

- [ ] Mission funnel có thể đo end-to-end.
- [ ] Victory/Defeat/Abandon được phân biệt.
- [ ] Difficulty signals có thể điều tra.
- [ ] Tower/Enemy/Commander usage có context.

### Progression

- [ ] Outpost progression được đo.
- [ ] Tech Tree/Mastery/Blueprint được đo.
- [ ] Progression stall có thể phát hiện.

### Economy

- [ ] Source/Sink có thể đối chiếu.
- [ ] Reward → Spend → Progression có thể theo dõi.
- [ ] Inflation/bottleneck có signal.

### Monetization

- [ ] Plus/Pro funnel có thể đo.
- [ ] Rewarded Ads voluntary usage có thể đo.
- [ ] Free/Plus/Pro advantage có thể so sánh ở cấp tổng hợp.

### Live Ops

- [ ] Event/Season participation được đo.
- [ ] Event Token flow được đo.
- [ ] Returning/Comeback có thể đo.

### Technical

- [ ] Crash/performance/loading được đo.
- [ ] Save/sync/recovery được đo.
- [ ] Impossible state có thể flag.

### Governance

- [ ] Event naming thống nhất.
- [ ] Property naming thống nhất.
- [ ] Event có owner/consumer.
- [ ] Không tracking ngoài product need.

---

# XXXIV. DATA DICTIONARY OWNERSHIP

Mỗi event production cần có:

- Event ID/name;
- Purpose;
- Trigger definition;
- Required properties;
- Optional properties;
- Consumer dashboard;
- Owner;
- Version introduced;
- Version deprecated nếu có;
- Privacy classification;
- QA verification status.

Không tạo event production chỉ bằng cách “bắn log cho tiện”.

---

# XXXV. VERSIONING & MIGRATION

Analytics schema cũng phải version-control.

Khi thay đổi:

- đổi tên event;
- đổi semantic;
- bỏ property;
- đổi enum;
- đổi meaning của metric;

phải ghi changelog.

Không được đổi meaning của một metric đang được dashboard sử dụng mà không đổi version / document.

---

# XXXVI. SOURCE VS PROPOSAL DECISION LOG

## A. Đã có trong tài liệu hiện tại

Các nguyên tắc sau đã có hoặc được củng cố rõ bởi tài liệu hiện tại:

- UI telemetry hooks.
- Core UX metrics.
- Economy telemetry metrics.
- AI telemetry.
- Save/sync/recovery observability needs.
- Mission duration target 5–15 phút.
- Fixed Enemy Roster.
- Persistent Outpost.
- Free / Plus / Pro.
- Rewarded Ads voluntary.
- Live Ops analytics placeholders.

## B. Đề xuất mới trong v1.0

Các nội dung dưới đây là **analytics framework proposal**, không phải gameplay decision mới:

- Event naming convention `domain_action`.
- KPI six-domain hierarchy.
- Dashboard structure.
- Alert severity P0/P1/P2.
- Vertical Slice minimum analytics package.
- Data dictionary ownership convention.
- Privacy-by-default analytics rule.
- Metric interpretation rules.
- Schema versioning convention.
- Stall diagnostic framework.
- Standard event taxonomy mở rộng từ các hooks hiện có.

Các đề xuất này chỉ thay đổi cách **đo và quản lý dữ liệu**, không thay đổi gameplay rules.

---

# XXXVII. ANALYTICS GOLDEN RULE

> **Measure enough to understand the game. Never measure so much that the game is redesigned around numbers alone.**

LAST OUTPOST cần telemetry để biết:

- người chơi đang làm gì;
- họ đang gặp vấn đề gì;
- hệ thống có hoạt động như design không;
- economy có khỏe không;
- content có đang thực hiện đúng vai trò không;
- save/performance có đáng tin không.

Nhưng quyết định cuối cùng vẫn phải xuất phát từ:

**Design Intent + Player Experience + Playtest + Data + Production Reality.**

---

# XXXVIII. STATUS

**Document:** LAST OUTPOST — ANALYTICS & KPI BIBLE  
**Version:** v1.0  
**Status:** Production Design Draft  
**Next Review:** Sau Vertical Slice analytics validation  
**Master Index Entry:** `21 — Analytics & KPI`  
**Implementation:** Không thuộc tài liệu này; Claude chỉ nhận specification sau khi analytics schema/design được duyệt.
