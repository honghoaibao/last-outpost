# LAST OUTPOST — DOCUMENTATION AUDIT v1.0

**Status:** COMPLETE — Post-Documentation Audit
**Date:** 2026-09-24
**Scope:** Full design/document package supplied in `Last Outpost.zip` + six newly created v1.0 production bibles.
**Purpose:** Kiểm tra conflict, duplication, authority, terminology và handoff readiness sau khi hoàn thành 6 tài liệu ưu tiên.

---

## 1. AUDIT RESULT

### Overall status

**DESIGN STABLE — DOCUMENTATION MAINTENANCE REQUIRED, NO CORE REWORK REQUIRED.**

Không phát hiện conflict cấp P0 làm thay đổi core gameplay, visual identity hoặc persistence model.

Các điểm phát hiện chủ yếu là **document-control / authority issues**, không phải lý do để rework hệ thống.

---

## 2. FINDINGS

### 2.1. Optimized v1.2 có mismatch version title

File hiện hành:

`Last_Outpost_Toi_Uu_v1.2.md`

nhưng title nội bộ vẫn là:

`BẢN TỐI ƯU CƠ CHẾ v1.1`

**Action:** chuẩn hóa title thành `v1.2`. Nội dung gameplay không được thay đổi trong lần maintenance này.

**Authority:** v1.2 là current optimized design; v1.1 trở thành historical predecessor.

---

### 2.2. Claude READ_ORDER cũ đã stale

`READ_ORDER.md` chỉ đi tới Claude Handoff và chưa đưa vào:

- World & Narrative Bible
- Audio Direction & Sound Bible
- Asset Production Specification
- Live Ops & Seasonal Content Bible
- Analytics & KPI Bible
- Master Design Index

**Action:** thay bằng `READ_ORDER v1.1` với authority-first order và domain order mới.

---

### 2.3. CURRENT_DECISIONS chưa phản ánh đầy đủ current canonical layers

Danh sách cũ ghi progression chủ yếu là:

`Outpost + Tech Tree + Mastery + Blueprint`

Trong Optimized v1.2, domain progression còn bao gồm:

- Commander
- Expedition
- Blueprint Fragment / Blueprint Crafting
- Mission / Challenge progression
- Seasonal progression là lớp temporary riêng

**Action:** mở rộng Current Decisions nhưng không thay đổi luật gameplay.

---

### 2.4. Core loop và gameplay pillars cần được phân tầng rõ

Các tài liệu dùng hai cách diễn đạt:

**Macro player loop:**

`BUILD → PREPARE → SURVIVE → ADAPT → GROW → TRY AGAIN`

**Four gameplay pillars:**

`BUILD → PREPARE → SURVIVE → ADAPT`

Đây **không phải conflict**.

Quy ước sau maintenance:

- Macro Core Loop = player lifecycle.
- Four Pillars = design lens dùng để kiểm tra system.

---

### 2.5. Monetization history có một điểm superseded

`Toi_Uu_v1.1` ghi rõ **không bán lợi thế chiến thuật trực tiếp**.

`Toi_Uu_v1.2` đã thay bằng:

- Free → Outpost Plus → Outpost Pro;
- lợi thế PvE nhẹ có giới hạn;
- ưu tiên QoL + tactical options;
- không bán raw power trực tiếp kiểu +damage/+HP;
- PvP normalization.

**Action:** v1.1 được đánh dấu historical. v1.2 là authority hiện hành.

Không cần chỉnh ngược v1.1; historical documents phải được giữ nguyên để truy lịch sử.

---

## 3. DUPLICATION AUDIT

### Intentional duplication — KEEP

Một số rule xuất hiện ở nhiều tài liệu vì cần được đọc độc lập:

- Outpost persistent
- Fixed Enemy Roster
- Tower = Base + Barrel + Module
- 5–15 minute mission
- Stylized 2.5D visual identity
- Plus/Pro guardrails

Đây là **constraint duplication có chủ đích**, không phải documentation error.

### Redundant historical duplication — DO NOT EXTEND

Các bản:

- Optimized v1
- Optimized v1.1
- System & Gameplay v1.0

không được tiếp tục cập nhật song song.

Chỉ current version mới nhận design changes.

---

## 4. SOURCE OF TRUTH RULE AFTER AUDIT

1. `CURRENT_DECISIONS` cho project-wide non-negotiables.
2. Optimized Core Design v1.2 cho current high-level gameplay/system direction.
3. Domain documents cho detailed rules trong domain của chúng.
4. New production bibles cho Narrative / Audio / Asset / Live Ops / Analytics.
5. Production/Reference documents cho execution guidance.
6. Legacy/Historical chỉ dùng cho history và change context.

Khi có conflict mới: **không tự hòa giải**. Tạo change decision trước, sau đó update source + downstream documents.

---

## 5. TERMINOLOGY AUDIT

### Canonical

- Outpost
- Commander
- Tower
- Base
- Barrel
- Module
- Fixed Roster / Fixed Enemy Roster
- Threat Budget
- Mastery
- Blueprint
- Blueprint Fragment
- Expedition
- World Event
- Season
- Outpost Plus
- Outpost Pro

### Restricted / avoid as replacement terminology

Không tự thay các thuật ngữ canonical bằng các tên mới chỉ vì “nghe hay hơn”.

Các nhãn mô tả như “HQ”, “Hero”, “Class”, “RPG gear” chỉ dùng khi tài liệu hiện hành đã dùng chính thuật ngữ đó trong đúng context.

---

## 6. NEW BIBLE QUALITY CHECK

### World & Narrative
- Scope: PASS
- Core identity alignment: PASS
- Lore/gameplay boundary: PASS
- New proposals labeled: PASS

### Audio
- Tactical readability: PASS
- Audio hierarchy: PASS
- Accessibility: PASS
- Production handoff: PASS

### Asset Production
- Asset categories: PASS
- State/animation/VFX/SFX requirements: PASS
- Naming/versioning/handoff: PASS
- Engine-agnostic boundary: PASS

### Live Ops
- Persistent vs temporary progression: PASS
- Event economy: PASS
- New/returning/comeback: PASS
- No mandatory daily engagement design: PASS

### Analytics
- Product/gameplay/UX/economy/monetization/technical split: PASS
- Event taxonomy: PASS
- Save/crash/performance coverage: PASS
- Data minimization principle: PASS

### Master Index
- Document map: PASS
- Dependencies: PASS
- Source of truth: PASS
- Read order: PASS
- Legacy handling: PASS

---

## 7. FINAL DECISION

**Do not create more top-level design bibles at this stage.**

LAST OUTPOST should now move to:

`STABLE DESIGN → CONTROLLED CHANGE → PRODUCTION / IMPLEMENTATION HANDOFF`

Future additions should normally be one of:

- revision to an existing source document;
- change decision / ADR;
- content record;
- balance/playtest result;
- production asset record;
- implementation note inside Claude handoff.

Avoid creating new top-level documents unless an existing domain genuinely becomes too large to maintain.

---

## 8. DEFINITION OF DONE — DOCUMENTATION PHASE

- [x] Six requested priority documents completed.
- [x] Full document package re-audited.
- [x] Core conflicts checked.
- [x] Historical precedence identified.
- [x] Source-of-truth model defined.
- [x] Read order requires update.
- [x] Current decisions requires update.
- [x] Optimized v1.2 title mismatch identified and corrected in maintenance copy.
- [x] No unnecessary new gameplay system introduced.
- [x] No code produced.

**Documentation phase status: COMPLETE.**
