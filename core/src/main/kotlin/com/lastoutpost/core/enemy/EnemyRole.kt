package com.lastoutpost.core.enemy

/**
 * Role — Core Combat Gameplay §VI (Enemy Design Framework: mỗi enemy có 6 trường Role/Threat/Defense/
 * Signature Ability/Counter A/Counter B). Chỉ model [EnemyRole] vì đây là phần dùng cho Custom Priority
 * Filter của Tower (`SWARM`, `BOSS_ELITE` — xem `com.lastoutpost.core.tower.TargetFilter`). Threat/Defense/
 * Signature Ability/Counter A/B là thiết kế combat/balance, không phải dữ liệu targeting — không model ở đây.
 */
enum class EnemyRole {
    SWARM, STRUCTURE_PUNISHER, HEAVY, SYSTEM_DISRUPTOR, INFILTRATOR, SUPPORT,
    AIR_HARASSER, AREA_DENIAL, FRONT_SHIELD, BUFFER, BOSS_SIEGE, BOSS_SPAWNER,
}
