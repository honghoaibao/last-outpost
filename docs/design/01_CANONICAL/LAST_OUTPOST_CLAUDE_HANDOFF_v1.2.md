# LAST OUTPOST — CLAUDE HANDOFF v1.2

## ROLE SPLIT
### Design side
Defines:
- Game Design
- System Design
- Gameplay
- Content
- Progression
- Economy
- UX/UI
- Visual Direction
- Audio Direction
- Production Documentation
- Reference/Specification

### Claude
Implements the approved design. Claude must not silently redesign systems.

## FIRST RULE
Read in this order:
`CURRENT_DECISIONS → CORE_DESIGN → GAMEPLAY_MASTER_MAP → BETA_01_SCOPE → domain docs → production/reference docs`.

## NON-NEGOTIABLES
1. Core loop: BUILD → PREPARE → SURVIVE → ADAPT → GROW → TRY AGAIN.
2. Stylized 2.5D Cartoon + Post-Apocalyptic Adventure + Tactical Strategy.
3. Mission target: 5–15 minutes.
4. Fixed Enemy Roster per mission.
5. Tower = Base + Barrel + Module.
6. Outpost progression is persistent; Seasons do not reset it.
7. PvE is priority over social/PvP.
8. Counterplay/readability matter more than raw stat inflation.
9. Bosses must create decisions, not HP-sponge encounters.
10. UI must not become generic mobile card-RPG UI.
11. Free users can play the core game.
12. Plus/Pro advantages are limited and PvP sensitive stats are normalized/limited.
13. Save/progression must be recovery-safe.
14. Content must be data-driven.
15. Do not reintroduce legacy territory-war/siege/diplomacy systems.
16. Do not invent new monetization systems without explicit design approval.
17. Do not change terminology without documenting a design change.

## BETA 01 MUST HAVE
- Full core PvE loop.
- Outpost functional activity.
- Workshop/Test Range.
- Research/Tech Tree.
- Expedition route/risk decisions.
- Intel/Codex used for preparation.
- Limited Specialist assignments.
- Guild foundation + Operations + Async Support.
- PvP Raid/Challenge Base with scout/result/redesign loop.
- Save/recovery/analytics.

## BETA 01 P1
- 2-player Co-op Defense prototype.
- Guild Defense role assignment.
- Additional trade/social depth.

## WHEN A DESIGN IS MISSING
Do not guess. Report:
- missing rule;
- impacted system;
- safest placeholder if implementation must continue;
- exact design question to resolve.

## DEFINITION OF DONE
Implementation is not considered done when the screen exists. It is done when:
- player flow is complete;
- feedback is readable;
- state persists correctly;
- edge cases recover;
- telemetry hooks exist where required;
- design intent is preserved;
- no legacy system was reintroduced accidentally.
