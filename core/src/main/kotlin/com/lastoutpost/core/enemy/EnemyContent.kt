package com.lastoutpost.core.enemy

import com.lastoutpost.core.entity.TargetClass

/**
 * Movement type + Threat Cost + Role + preferred target class tham chiếu cho từng species trong
 * `content/enemies/`.
 *
 * - `movementType`: suy trực tiếp từ mô tả hành vi (AI & Simulation Design §IV, §VI) — Scavenger Harpy bay,
 *   Tunnel Burrower đào hầm, còn lại mặc định Ground. Hai Boss không có mô tả di chuyển riêng → mặc định
 *   Ground (giả định an toàn nhất, KHÔNG có nguồn trực tiếp, gắn cờ ở đây để dễ sửa).
 * - `threatCost`: **CANON Beta 01** (quyết định Bảo, 2026-09-27) — dùng AI & Simulation Design §VII làm
 *   source of truth (baseline balance, có thể tune sau playtest nhưng phải cập nhật tại đây, không tạo bảng
 *   thứ hai). Bảng khác ở System & Gameplay Design v1.1 §8.3 là legacy/sample values, KHÔNG dùng để
 *   implementation. Boss dùng Boss Profile riêng (không qua Threat Budget thường) → `null`.
 * - `role`: Core Combat Gameplay §VI (Enemy Design Framework) — xem [EnemyRole].
 * - `preferredTargetClasses`: suy TRỰC TIẾP, theo đúng thứ tự, từ mô tả hành vi archetype ở AI Sim §VI (vd
 *   "Crawler ưu tiên Core" → `[BASE_CORE]`). Danh sách RỖNG nghĩa là archetype không có mục tiêu tấn công cụ
 *   thể trong mô tả (support/avoid-combat — Corpse Weaver, Siren Screamer, Hive Mother, Tunnel Burrower —
 *   Tunnel Burrower chỉ nói "theo target value" chung chung, không nêu class). Đây là DIỄN GIẢI kỹ thuật,
 *   không phải canon — cập nhật khi có thiết kế Target Selection chính thức (OQ-DESIGN-02).
 */
object ReferenceEnemyCatalog {
    data class Entry(
        val speciesId: String,
        val movementType: MovementType,
        val threatCost: Int?,
        val role: EnemyRole,
        val preferredTargetClasses: List<TargetClass> = emptyList(),
    )

    val entries: List<Entry> = listOf(
        Entry("ENEMY_CRAWLER", MovementType.GROUND, 1, EnemyRole.SWARM, listOf(TargetClass.BASE_CORE)),
        Entry("ENEMY_ACID_BLOATER", MovementType.GROUND, 5, EnemyRole.STRUCTURE_PUNISHER, listOf(TargetClass.TOWER)),
        Entry("ENEMY_IRON_HIDE_BEHEMOTH", MovementType.GROUND, 12, EnemyRole.HEAVY, listOf(TargetClass.WALL_GATE, TargetClass.BASE_CORE)),
        Entry("ENEMY_TUNNEL_BURROWER", MovementType.BURROW, 9, EnemyRole.INFILTRATOR, emptyList()),
        Entry("ENEMY_EMP_STALKER", MovementType.GROUND, 10, EnemyRole.SYSTEM_DISRUPTOR, listOf(TargetClass.TOWER)),
        Entry("ENEMY_CORPSE_WEAVER", MovementType.GROUND, 8, EnemyRole.SUPPORT, emptyList()),
        Entry("ENEMY_SCAVENGER_HARPY", MovementType.FLYING, 7, EnemyRole.AIR_HARASSER, listOf(TargetClass.DRONE, TargetClass.LOGISTICS_HUB)),
        Entry("ENEMY_SPORE_SPREADER", MovementType.GROUND, 8, EnemyRole.AREA_DENIAL, listOf(TargetClass.TOWER)),
        Entry("ENEMY_RIOT_MECH_MUTATE", MovementType.GROUND, 11, EnemyRole.FRONT_SHIELD, listOf(TargetClass.TOWER)),
        Entry("ENEMY_SIREN_SCREAMER", MovementType.GROUND, 9, EnemyRole.BUFFER, emptyList()),
        Entry("BOSS_COLOSSUS", MovementType.GROUND, null, EnemyRole.BOSS_SIEGE, listOf(TargetClass.WALL_GATE, TargetClass.TOWER, TargetClass.BASE_CORE)),
        Entry("BOSS_HIVE_MOTHER", MovementType.GROUND, null, EnemyRole.BOSS_SPAWNER, emptyList()),
    )

    val bySpeciesId: Map<String, Entry> = entries.associateBy { it.speciesId }
}
