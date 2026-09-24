# LAST OUTPOST — GAMEPLAY MASTER MAP v1.0

## PURPOSE
Bản đồ gameplay nhìn từ phía người chơi. Không thay thế GDD; dùng để kiểm tra mọi hệ thống có thực sự tạo ra **Action / Decision / Feedback / Impact** hay chỉ là menu/support.

## 1. PLAYER MASTER LOOP
```text
OUTPOST
  ↓
DISCOVER / PREPARE
  ↓
CAMPAIGN / EXPEDITION / SOCIAL ACTIVITY
  ↓
MISSION BRIEFING
  ↓
INTEL
  ↓
LOADOUT
  ↓
PREPARE
  ↓
BUILD + LOGISTICS
  ↓
COMBAT
  ↓
ADAPT
  ↓
VICTORY / DEFEAT
  ↓
BATTLE REPORT
  ↓
REWARD / MASTERY / BLUEPRINT
  ↓
OUTPOST GROWTH / NEW OPTIONS
  ↓
DISCOVER / TRY AGAIN
```

## 2. OUTPOST — PLAYER ACTIONS
Người chơi có thể:
- di chuyển/khám phá các khu chức năng;
- chọn Campaign / Mission;
- lắp và test Tower build;
- nghiên cứu Tech Tree;
- quản lý Workshop/Blueprint;
- chuẩn bị Expedition;
- gán Specialist;
- đọc Intel/Codex;
- quản lý Guild/Social;
- xem mục tiêu tiếp theo.

### Functional Spaces
| Space | Player sees | Player does | Gameplay impact |
|---|---|---|---|
| Command Center | Outpost state, objectives | Chọn hướng tiến triển | High |
| Workshop / Test Range | Base/Barrel/Module combinations | Prototype/test build | High |
| Research | Tech nodes + prerequisites | Chọn priority | High |
| Logistics Hub | supply status / route | chuẩn bị logistics | Medium–High |
| Expedition Board | world map / risk | scout, route, team | High |
| Intel Archive | known/unknown threats | inspect, learn counters | Medium–High |
| Survivor Quarter | specialist assignment | assign role | Medium |

## 3. EXPEDITION LOOP
```text
SELECT SITE
→ RECON / INTEL
→ CHOOSE TEAM
→ CHOOSE LOADOUT
→ CHOOSE ROUTE / RISK
→ EXPEDITION
→ INCIDENT (optional)
→ DECISION
→ RESULT
→ RESOURCE / INTEL / BLUEPRINT
→ RETURN TO OUTPOST
```

Expedition không được thành waiting-only reward. Mỗi expedition đáng kể phải có ít nhất một **meaningful pre-departure decision**; incident chỉ xuất hiện ở scope kiểm soát.

## 4. COMBAT PLAYER ACTIONS
### Direct
- Tap: select / inspect / target / ability.
- Hold: inspect / range / preview.
- Drag: place / route interaction khi mode cho phép.
- Pinch: zoom.
- Two-finger drag: pan.
- Long press: contextual action.

### Tactical decisions
1. Build what?
2. Build where?
3. Which Base + Barrel + Module?
4. Which enemy first?
5. Which resource problem first?
6. Which Commander ability now?
7. Which logistics route needs attention?
8. When to recover/rebuild/reposition?

## 5. COMBAT FEEDBACK CHAIN
```text
EVENT
→ VISUAL CUE
→ AUDIO CUE
→ UI STATE / ALERT
→ PLAYER UNDERSTANDS
→ PLAYER DECIDES
→ PLAYER ACTS
→ RESULT FEEDBACK
```
Một alert/action feedback tốt phải giúp người chơi trả lời: **đang xảy ra gì / nguy hiểm ở đâu / tôi làm gì được ngay**.

## 6. POST-MISSION LOOP
Battle Report tối thiểu phải cho thấy:
- result;
- stars/objectives;
- resource outcome;
- major failure/success reason;
- meaningful tactical events;
- Mastery / Blueprint progress;
- next milestone.

Mục tiêu: người chơi biết **trận vừa rồi dạy mình điều gì** và có lý do thử lại.

## 7. META DEPTH
### Permanent
Outpost, Tech Tree, Blueprint, Tower/Module ownership, Commander, Mastery, Story unlocks.
### Temporary
Season challenge, event currency/progress, temporary leaderboard.

## 8. SOCIAL LOOP
### Support
Gửi/nhận trợ giúp có giới hạn.
### Cooperation
Guild Operation / Co-op Defense.
### Competition
PvP Raid/Challenge Base.

## 9. WHAT IS NOT CORE GAMEPLAY
Technical architecture, save implementation, asset naming, telemetry plumbing, UI component implementation, VFX production convention, audio file naming. Những thứ này support gameplay chứ không thay đổi player rules.

## 10. OPEN DESIGN REVIEW ITEMS
Các điểm cần review trước khi coi là canonical detail:
- exact Commander skill kit;
- exact Tower roster names beyond archetypes;
- exact PvP normalization values;
- exact Co-op Defense reward/economy limits;
- exact Expedition incident pool;
- exact Specialist roster/bonuses.
