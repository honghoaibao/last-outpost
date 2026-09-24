# LAST OUTPOST — MASTER DESIGN INDEX v1.2

## 0. PURPOSE
Source-of-truth map cho Beta 01. Claude phải bắt đầu tại `00_START_HERE/CLAUDE_READ_FIRST.md`, sau đó đọc theo Read Order. Legacy không được dùng để tự khôi phục tính năng.

## 1. AUTHORITY ORDER
1. Current Decisions v1.2 — non-negotiable/current decisions.
2. Core Design / Optimized Master v1.3 — macro/core design.
3. Beta 01 Design & Implementation Scope v1.0 — Beta-specific scope and newly approved depth/social boundaries.
4. Domain source documents — detailed rules.
5. New Bibles — narrative/audio/asset/live ops/analytics.
6. Visual Reference — visual reference.
7. Playtest / Production — validation and production constraints.
8. Legacy Archive — history only.

## 2. CURRENT CANONICAL MAP
| ID | Document | Authority | Main use |
|---|---|---|---|
| C01 | CURRENT_DECISIONS_v1.2 | CANONICAL | current decisions |
| C02 | CORE_DESIGN_v1.3 | CANONICAL | macro design |
| C03 | GAMEPLAY_MASTER_MAP_v1.0 | CANONICAL | player-facing gameplay map |
| C04 | BETA_01_DESIGN_SCOPE_v1.0 | CANONICAL FOR BETA | Beta scope |
| C05 | SYSTEM_GAMEPLAY_v1.1 | DOMAIN | gameplay rules |
| C06 | CORE_COMBAT_v1.0 | DOMAIN | combat |
| C07 | CONTENT_SPECIFICATION_v1.0 | DOMAIN | content |
| C08 | ECONOMY_v1.0 | DOMAIN | economy |
| C09 | PLAYER_UX_FLOW_v1.0 | DOMAIN | player flow |
| C10 | UI_UX_v1.0 | DOMAIN | interface |
| C11 | AI_SIMULATION_v1.0 | DOMAIN | AI/simulation |
| C12 | SAVE_DATA_v1.0 | DOMAIN | persistence |
| C13 | TECHNICAL_ARCHITECTURE_v1.0 | DOMAIN | implementation boundary |
| C14 | PRODUCTION_ROADMAP_v1.0 | PRODUCTION | build order |
| C15 | VISUAL_IDENTITY_v1.0 | VISUAL | art direction |
| C16 | VERTICAL_SLICE_v1.0 | VALIDATION | quality reference |
| C17 | PLAYTEST_BALANCE_v1.0 | VALIDATION | balance/playtest |
| C18 | PRODUCTION_CONTENT_BIBLE_v1.0 | PRODUCTION | content quality |
| C19 | CONTENT_PRODUCTION_PACK_v1.0 | PRODUCTION | content templates |
| C20 | WORLD_NARRATIVE_v1.0 | DOMAIN | world/lore |
| C21 | AUDIO_BIBLE_v1.0 | DOMAIN | audio |
| C22 | ASSET_PRODUCTION_SPEC_v1.0 | PRODUCTION | asset handoff |
| C23 | LIVE_OPS_BIBLE_v1.0 | DOMAIN | live ops |
| C24 | ANALYTICS_KPI_BIBLE_v1.0 | DOMAIN | analytics |
| C25 | CLAUDE_HANDOFF_v1.2 | HANDOFF | Claude contract |

## 3. STATUS MODEL
- **CANONICAL:** current authority.
- **DOMAIN:** current detailed source.
- **SUPPORTING:** informs production but should not override canonical design.
- **REFERENCE:** visual/reference only.
- **LEGACY:** historical; never revive without explicit decision.

## 4. DEPENDENCY RULE
When a change occurs:
1. identify source-of-truth;
2. change it;
3. update affected downstream docs;
4. update version + changelog note;
5. re-run conflict scan.

## 5. BETA IMPLEMENTATION PRIORITY
P0: Core PvE + Outpost depth + Expedition/Intel + Guild/PvP foundation + save/analytics.
P1: 2-player co-op defense + expanded social operations.
P2: post-beta scale.

## 6. LEGACY PROTECTION
The following legacy design families are explicitly archived, not active:
- territory war / hex-grid PvP;
- 24h siege;
- nuclear war monuments;
- complex diplomacy;
- unrestricted multi-surface placement beyond optimized beta scope;
- any monetization idea that bypasses Current Decisions.
