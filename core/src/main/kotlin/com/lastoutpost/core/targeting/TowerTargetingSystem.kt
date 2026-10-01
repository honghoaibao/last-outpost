package com.lastoutpost.core.targeting

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.enemy.EnemyEntity
import com.lastoutpost.core.enemy.EnemyRole
import com.lastoutpost.core.enemy.MovementType
import com.lastoutpost.core.enemy.ReferenceEnemyCatalog
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem
import com.lastoutpost.core.tower.PrimaryOrder
import com.lastoutpost.core.tower.TargetFilter
import com.lastoutpost.core.tower.Targeting
import com.lastoutpost.core.tower.TieBreaker
import com.lastoutpost.core.tower.TowerEntity

/**
 * Tower → enemy targeting — System & Gameplay Design v1.1 §6.1–6.2:
 * `Acquire Target → Filter Valid Target → Rank Targets → Lock Target → Fire → ...`
 * Chỉ làm tới **Lock** ở đây — Fire/Resolve Hit/Apply Effect/Update Heat-Ammo-Power thuộc milestone
 * Attack/Projectile riêng (TickStage.ATTACK_PROJECTILE), CHƯA triển khai.
 *
 * Rank theo Custom Priority Beta 01 (`TargetingRule` — FILTER → PRIMARY ORDER → TIE BREAKER, canon quyết
 * định Bảo 2026-09-27):
 * - **Filter:** [TargetFilter.AIR]/[TargetFilter.GROUND] dùng `MovementType` thật; [TargetFilter.SWARM] và
 *   [TargetFilter.BOSS_ELITE] dùng `EnemyRole`/`isElite` thật ([ReferenceEnemyCatalog]).
 *   [TargetFilter.SPECIAL_THREAT] CHƯA có tiêu chí nguồn nào → luôn khớp RỖNG (không tự đặt tiêu chí).
 * - **Primary order:** NEAREST/STRONGEST/LOWEST_HP dùng dữ liệu thật khi có (Health hiện luôn `null` —
 *   OQ-CONTENT-02 — nên STRONGEST/LOWEST_HP hiện không phân biệt được các candidate thiếu Health, xếp cuối).
 *   FIRST dùng khoảng cách Euclid tới [Battlefield.corePosition] (xấp xỉ "tiến xa nhất trên lane" bằng
 *   đường thẳng, KHÔNG phải hop-count trên Path Graph thật — xem TECH_DEBT); nếu battlefield chưa cấu hình
 *   `coreNodeId`, fallback về thứ tự entity id (không sụp đổ, nhưng không có ý nghĩa "tiến xa").
 * - **Tie breaker:** áp dụng khi hai candidate bằng điểm primary; mặc định cuối cùng luôn là entity id để
 *   đảm bảo kết quả tất định tuyệt đối (không phải một phần của quyết định thiết kế, chỉ để ổn định).
 *
 * [range] là bán kính Acquire — CHƯA có trong `baseStats` (`null`, OQ-DESIGN-02) nên PHẢI truyền từ ngoài.
 */
class TowerTargetingSystem(
    private val range: Double,
    private val battlefield: Battlefield? = null,
    private val catalog: Map<String, ReferenceEnemyCatalog.Entry> = ReferenceEnemyCatalog.bySpeciesId,
) : TickSystem {
    override val stage = TickStage.TARGET_ACQUISITION

    override fun update(world: BattleWorld) {
        val enemies = world.entities.enemyIds.mapNotNull { id -> world.entities.enemy(id)?.let { id to it } }
        val goal = battlefield?.corePosition

        for (towerId in world.entities.towerIds) {
            val tower = world.entities.tower(towerId) ?: continue
            val rule = tower.targeting.rule

            val candidates = enemies
                .filter { (_, enemy) -> tower.position.distanceTo(enemy.position) <= range }
                .filter { (_, enemy) -> matchesFilter(enemy, rule.filter) }

            val ranked = candidates.sortedWith(
                compareBy<Pair<Long, EnemyEntity>> { (id, enemy) -> primaryKey(rule.primaryOrder, tower, id, enemy, goal) }
                    .thenBy { (id, enemy) -> tieKey(rule.tieBreaker, tower, id, enemy, goal) }
                    .thenBy { (id, _) -> id }, // tie-break cuối cùng: chỉ để tất định, không phải rule thiết kế
            )

            val lockedId = ranked.firstOrNull()?.first
            if (lockedId != tower.targeting.currentTargetId) {
                world.entities.updateTower(towerId) { it.copy(targeting = Targeting(lockedId, rule)) }
            }
        }
    }

    private fun matchesFilter(enemy: EnemyEntity, filter: TargetFilter): Boolean {
        val entry = catalog[enemy.speciesId]
        return when (filter) {
            TargetFilter.ALL -> true
            TargetFilter.AIR -> enemy.movement.type == MovementType.FLYING
            TargetFilter.GROUND -> enemy.movement.type != MovementType.FLYING
            TargetFilter.SWARM -> entry?.role == EnemyRole.SWARM
            TargetFilter.BOSS_ELITE -> enemy.isElite || entry?.role == EnemyRole.BOSS_SIEGE || entry?.role == EnemyRole.BOSS_SPAWNER
            TargetFilter.SPECIAL_THREAT -> false // chưa có tiêu chí nguồn — xem KDoc lớp này
        }
    }

    private fun primaryKey(order: PrimaryOrder, tower: TowerEntity, id: Long, enemy: EnemyEntity, goal: WorldPosition?): Double =
        when (order) {
            PrimaryOrder.NEAREST -> tower.position.distanceTo(enemy.position)
            PrimaryOrder.FIRST -> goal?.let { enemy.position.distanceTo(it) } ?: id.toDouble()
            PrimaryOrder.STRONGEST -> -(enemy.health?.max?.toDouble() ?: Double.NEGATIVE_INFINITY) // chưa rõ Max HP → xếp cuối
            PrimaryOrder.LOWEST_HP -> enemy.health?.current?.toDouble() ?: Double.POSITIVE_INFINITY // chưa rõ Current HP → xếp cuối
        }

    private fun tieKey(breaker: TieBreaker?, tower: TowerEntity, id: Long, enemy: EnemyEntity, goal: WorldPosition?): Double =
        when (breaker) {
            null -> 0.0
            TieBreaker.NEAREST -> tower.position.distanceTo(enemy.position)
            TieBreaker.FIRST -> goal?.let { enemy.position.distanceTo(it) } ?: id.toDouble()
        }
}
