# LAST OUTPOST — SAVE / DATA DESIGN v1.0

## 0. Mục tiêu

Save/Data Design là lớp dữ liệu bảo đảm toàn bộ tiến trình dài hạn của Last Outpost:
- Trụ Sở không bị reset ngoài chủ đích.
- Campaign, Tower, Module, Blueprint, Mastery và Economy được lưu nhất quán.
- Trận đấu có state tạm thời, không làm bẩn dữ liệu tài khoản khi chưa hoàn tất.
- Cloud Save có thể khôi phục tiến trình.
- Offline play có thể hoạt động trong phạm vi cho phép.
- Transaction quan trọng có thể idempotent (gửi lại không tạo phần thưởng hai lần).
- Có versioning/migration để cập nhật game lâu dài.

Nguyên tắc:
> Persistent Account Data != Match Runtime State != Presentation State.

---

## I. BA LỚP DỮ LIỆU

### 1. Persistent Account Data
Dữ liệu sống xuyên suốt tài khoản:
- Account/Profile
- Outpost
- Buildings
- Tech Tree
- Tower collection
- Module collection
- Blueprint
- Blueprint fragments
- Commander
- Mastery
- Campaign progress
- Mission stars/objectives
- Expedition progress
- Inventory
- Currency
- Achievements
- Collection
- Cosmetics
- Plus/Pro entitlement
- Season entitlement/progress
- Settings/accessibility

### 2. Match Runtime State
Chỉ tồn tại trong một trận:
- Map seed
- Current wave
- Enemy instances
- Tower instances
- HP/heat/ammo hiện tại
- Power/water supply hiện tại
- Temporary hazards
- Temporary buffs/debuffs
- Tactical cooldown
- Commander combat state
- Destroyed/repaired objects
- Mission timer

Runtime state chỉ được ghi theo checkpoint khi cần recovery; không ghi từng frame.

### 3. Presentation / Client State
Không có giá trị gameplay trực tiếp:
- Camera position
- Last selected menu
- UI filter
- Sort mode
- Animation state
- Audio volume
- Control preferences

Client state có thể lưu local và không cần cloud.

---

## II. SOURCE OF TRUTH

### Account Authority

Cloud/server là source of truth cho:
- Premium currency
- Purchases
- Plus/Pro entitlement
- Important inventory transactions
- Competitive/PvP records
- Season rewards
- Claim history

### Local Authority

Local save có thể là source tạm thời cho:
- Settings
- Presentation preferences
- Cached content
- Offline-safe progression trước khi sync

### Hybrid

Campaign/Outpost gameplay:
1. Client đọc local snapshot.
2. Người chơi chơi.
3. Transaction hoàn thành được ghi local.
4. Sync lên cloud/server.
5. Server xác nhận.
6. Client cập nhật synced snapshot.

Không được coi một UI animation hoặc optimistic reward là transaction đã thành công.

---

## III. ACCOUNT DATA MODEL

```text
Account
├── accountId
├── profile
├── progression
├── outpost
├── collection
├── inventory
├── commanders
├── mastery
├── campaign
├── expeditions
├── economy
├── seasons
├── cosmetics
├── entitlements
├── settings
└── metadata
```

### Profile
- displayName
- avatar
- frame
- title
- account creation timestamp
- last login
- language
- age-rating relevant consent flags nếu cần

### Progression
- account XP nếu có
- Outpost stage
- unlocked systems
- research unlocks
- milestone flags

### Outpost
- Outpost level/stage
- building instances
- building level
- building placement
- building upgrade state
- unlocked areas
- current workers/assignments
- cosmetic layout

---

## IV. ITEM / COLLECTION MODEL

Mỗi collectible nên có:

```text
ItemDefinition
├── definitionId
├── version
├── category
├── rarity
├── tags
├── gameplayRules
└── cosmeticRules
```

Còn account sở hữu:

```text
OwnedItem
├── ownerAccountId
├── definitionId
├── quantity
├── unlockState
├── masteryState
└── acquisitionMetadata
```

Tách Definition khỏi Ownership để:
- patch balance mà không sửa toàn bộ account;
- thêm item mới;
- migrate data dễ;
- giảm save size.

---

## V. TOWER / MODULE DATA

### Tower Definition
- towerId
- archetype
- allowedBases
- allowedBarrels
- allowedModules
- targetingRules
- damageModel
- heatModel
- ammoModel
- powerModel

### Player Collection
- unlocked
- owned variants
- mastery XP
- favorite
- preset references

### Match Instance
- tower instance ID
- definition references
- position
- current HP
- current ammo
- current heat
- temporary effects

Không lưu toàn bộ definition vào save runtime; chỉ lưu ID + state cần thiết.

---

## VI. BLUEPRINT DATA

Blueprint có hai trạng thái:

### Unlock Progress
```text
BlueprintProgress
├── blueprintId
├── fragments
└── unlocked
```

### Crafted Asset
Khi craft/unlock:
- blueprint ownership
- unlocked variant
- cosmetic/functional metadata

Nguyên tắc:
> Fragment transaction phải atomic (thành công toàn bộ hoặc không thay đổi gì).

Ví dụ:
```text
10 Fragments
→ -10 Fragments
→ +1 Blueprint
```
Không được xảy ra trạng thái:
```text
-10 Fragments
nhưng không +1 Blueprint
```

---

## VII. CAMPAIGN DATA

```text
CampaignProgress
├── currentRegion
├── unlockedMissionIds
├── completedMissionIds
├── starRecords
├── objectiveRecords
├── bestTime
├── bestScore
└── firstClearRewardsClaimed
```

Mission completion phải có:
- unique completion ID
- mission version
- result checksum/validation metadata nếu server-side verification cần

### Quan trọng

Nếu designer thay đổi một màn đã hoàn thành:
- không xóa lịch sử hoàn thành;
- record cũ vẫn tồn tại;
- chỉ content version mới áp dụng cho lần chơi tiếp theo.

---

## VIII. OUTPOST PERSISTENCE

Trụ Sở là persistent progression.

Lưu:
- stage
- level
- building unlocks
- building levels
- placement
- production state
- worker assignments
- storage
- research state
- cosmetic layout

### Building Transaction

Upgrade:
```text
Validate requirements
→ reserve resources
→ apply upgrade
→ persist
→ emit event
```

Không:
```text
UI says upgrade complete
→ backend later decides whether to save
```

UI chỉ hiển thị trạng thái đã được hệ thống xác nhận.

---

## IX. ECONOMY TRANSACTION LEDGER

Mọi giao dịch quan trọng nên có transaction ID.

```text
Transaction
├── transactionId
├── accountId
├── timestamp
├── type
├── source
├── delta
├── referenceId
├── clientRequestId
└── status
```

### Idempotency

Nếu cùng `clientRequestId` gửi hai lần:
> Chỉ được tính một lần.

Áp dụng cho:
- Reward claim
- Purchase
- Blueprint craft
- Mission reward
- Season reward
- Expedition completion
- Ad reward

Đây là lớp cực quan trọng để tránh:
- double reward
- double spend
- rollback exploit

---

## X. MATCH RESULT PIPELINE

Khi người chơi thắng:

```text
Combat End
→ Generate Result
→ Validate Result
→ Calculate Rewards
→ Create Transaction Batch
→ Persist Account Changes
→ Confirm Sync
→ Show Result UI
```

Không hiển thị:
> “+500 Scrap”

trước khi transaction được ghi thành công, trừ khi hệ thống dùng optimistic presentation và có rollback UX rõ ràng.

### Reward Batch

Một batch có thể gồm:
- Mission completion
- Scrap
- Materials
- Biomass
- Blueprint fragments
- Mastery XP
- Stars
- Achievement progress

Tất cả phải được commit nhất quán.

---

## XI. OFFLINE PLAY

### Cho phép offline:
- Campaign đã tải dữ liệu
- Combat PvE đã chuẩn bị
- Outpost xem/lập kế hoạch local
- Settings
- Một số non-premium interactions

### Hạn chế offline:
- Purchases
- Premium currency spending
- Plus/Pro entitlement changes
- PvP
- Guild synchronized actions
- Claim các reward nhạy cảm
- Season leaderboard

### Offline Queue

```text
PendingAction
├── actionId
├── type
├── payload
├── createdAt
├── localSequence
└── retryCount
```

Khi online:
```text
Replay pending actions
→ server validation
→ accepted/rejected
→ mark synced
```

---

## XII. CLOUD SAVE / SNAPSHOT

Dùng hai lớp:

### Snapshot
Lưu trạng thái tài khoản hiện tại.

### Transaction Log
Lưu các thay đổi quan trọng gần đây.

Lợi ích:
- Snapshot để load nhanh.
- Transaction log để audit/recovery.

### Snapshot fields
- schemaVersion
- accountRevision
- updatedAt
- payload
- checksum

Nếu có conflict:
> revision cao hơn + transaction hợp lệ được ưu tiên theo server authority.

---

## XIII. CONFLICT RESOLUTION

### Không merge mù

Ví dụ:
- Device A: +500 Scrap
- Device B: +1 Blueprint

Không được tự động lấy snapshot của B rồi bỏ mất A.

Quy trình:
1. Detect revision conflict.
2. Pull latest server state.
3. Reconcile transaction history.
4. Rebuild current snapshot.
5. Push only valid local transactions.
6. Update client snapshot.

### Currency

Currency không merge bằng:
> max(A,B)

Mà merge bằng transaction ledger đã xác nhận.

---

## XIV. ROLLBACK / RECOVERY

### Save corruption

Giữ:
- Current snapshot
- Previous snapshot
- Last known good snapshot

Ví dụ:
```text
Snapshot R100
Snapshot R101
Snapshot R102
```

Nếu R102 invalid:
→ rollback R101
→ replay valid transactions sau R101 nếu có.

### Client crash during reward

Nếu crash sau battle nhưng trước Result UI:
- transaction server vẫn tồn tại nếu đã commit;
- khi mở lại game → recovery banner:
  > Reward recovered.

Không được bắt người chơi chơi lại một trận đã commit.

---

## XV. VERSIONING & MIGRATION

Mọi save có:

```text
schemaVersion
dataVersion
contentVersion
```

Migration ví dụ:

```text
v1 → v2
- rename field
- split inventory
- add mastery
```

Migration phải:
- deterministic
- idempotent
- có backup
- có validation sau migration

Không được overwrite save cũ trước khi migration thành công.

---

## XVI. SEASON DATA

### Persistent
- Season cosmetics đã sở hữu
- Season achievements
- Permanent unlocks

### Reset/rotate
- Seasonal points
- Seasonal missions
- Seasonal leaderboard
- Seasonal event progress
- Temporary event currency

Season rollover:
```text
Finalize
→ Grant rewards
→ Archive result
→ Convert/expire temporary currency
→ Create new season state
```

Trụ Sở và core progression **không reset**.

---

## XVII. GUILD / SOCIAL DATA

### Guild data
- guildId
- member list
- permissions
- shared progress
- guild missions
- guild cosmetics
- shared storage if implemented

### PvP
Kết quả PvP nên server-authoritative.

Client không được tự xác nhận:
- win
- score
- rating
- reward

---

## XVIII. ENTITLEMENT / PLUS / PRO

Entitlement object:

```text
Entitlement
├── type
├── source
├── startAt
├── endAt
├── status
└── verification
```

Plus/Pro benefits được tính từ entitlement hợp lệ.

Không lưu:
> `isPro = true`

một cách đơn độc mà không có source/reference transaction.

---

## XIX. SECURITY PRINCIPLES

Client không được tự quyết định:
- premium balance
- purchase success
- PvP result
- competitive reward
- Plus/Pro entitlement
- high-value transaction

Client có thể tự xử lý local:
- UI
- camera
- settings
- presentation

Server validation mạnh hơn với:
- economy
- reward
- purchase
- PvP
- guild shared state

---

## XX. SAVE SIZE / PERFORMANCE

Mục tiêu:
- Fast load: snapshot compact.
- Không serialize toàn bộ content definitions.
- Không lưu runtime enemy state sau mỗi frame.
- Không lưu duplicate cosmetic metadata.
- Compress snapshot nếu cần.
- Chunk dữ liệu lớn:
  - core account
  - outpost
  - collection
  - history
  - settings

---

## XXI. ANALYTICS EVENTS

Các event quan trọng:
- account_created
- tutorial_step_completed
- mission_started
- mission_completed
- mission_failed
- reward_claimed
- blueprint_crafted
- building_upgraded
- tech_unlocked
- purchase_completed
- plus_activated
- pro_activated
- sync_conflict
- save_recovery
- migration_completed

Không gửi dữ liệu cá nhân không cần thiết.

---

## XXII. TEST MATRIX

### Save
- App force close
- Device reboot
- Network loss
- Duplicate request
- Interrupted reward
- Interrupted purchase
- Corrupted snapshot
- Migration failure
- Two-device login
- Old client + new data

### Economy
- Reward exactly once
- Spend exactly once
- Insufficient currency
- Refund/reversal
- Event expiry

### Match
- Victory then crash
- Defeat then disconnect
- Mid-match reconnect
- Retry after committed result

### Season
- Rollover
- Reward grant
- Expiry
- New season initialization

---

## XXIII. OBSERVABILITY / DEBUG TOOLS

Internal tools nên có:
- View account revision
- Inspect transaction
- Grant test reward
- Revoke test reward
- Simulate migration
- Force sync
- Compare local/server snapshot
- Restore snapshot
- Replay transaction
- Inspect entitlement
- Simulate season rollover

Production admin tools cần audit log.

---

## XXIV. DEFINITION OF DONE

Save/Data Design hoàn thành khi:

- [ ] Persistent / Runtime / Presentation data tách rõ.
- [ ] Server authority xác định cho dữ liệu quan trọng.
- [ ] Mission reward atomic.
- [ ] Transaction idempotent.
- [ ] Offline queue hoạt động đúng.
- [ ] Cloud snapshot + revision hoạt động.
- [ ] Conflict resolution có thiết kế.
- [ ] Crash recovery có.
- [ ] Migration có versioning.
- [ ] Season rollover không reset Outpost.
- [ ] Plus/Pro entitlement có verification.
- [ ] PvP server-authoritative.
- [ ] Save corruption có recovery path.
- [ ] Test matrix pass.

---

# XXV. IMPLEMENTATION PRIORITY

## P0 — bắt buộc
- Account model
- Persistent Outpost
- Campaign progress
- Inventory
- Currency
- Mission result transaction
- Local save
- Cloud sync
- Revision
- Idempotency

## P1
- Offline queue
- Snapshot history
- Recovery
- Entitlement
- Season state
- Migration system

## P2
- Transaction audit UI
- Advanced conflict tools
- Reconciliation tools
- Admin debugging

## P3
- Advanced replay/audit
- Extended analytics
- Data repair automation

---

# XXVI. CORE RULES

1. Trụ Sở là persistent.
2. Match state không được làm hỏng account state.
3. Reward chỉ được commit một lần.
4. Client không phải authority cho dữ liệu giá trị cao.
5. Offline không được trở thành đường bypass economy/security.
6. Migration phải an toàn và có rollback.
7. Season reset content theo mùa, không reset core account.
8. PvP phải server-authoritative.
9. Transaction phải có ID và audit.
10. Người chơi không bao giờ nên mất progression chỉ vì crash, disconnect hoặc đổi thiết bị.

> **SAVE/DATA GOAL: Người chơi có thể chơi lâu dài mà tin rằng Trụ Sở và công sức của mình luôn an toàn.**
