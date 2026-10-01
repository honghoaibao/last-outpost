package com.lastoutpost.core.targeting

import com.lastoutpost.core.enemy.ReferenceEnemyCatalog
import com.lastoutpost.core.enemy.Targeting
import com.lastoutpost.core.entity.TargetClass
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem

/**
 * Enemy → structure targeting — AI & Simulation Design §III ("Target Selection"): "Enemy không nên mặc định
 * luôn đánh Core." Tài liệu chỉ cho khung cộng dồn (Objective + Distance + Vulnerability + Threat +
 * SpecialInterest − PathCost), không cho công thức/hệ số cụ thể cho từng thành phần.
 *
 * CHỈ model 2 thành phần có nguồn rõ:
 * - **Objective:** lọc theo `preferredTargetClasses` của archetype ([ReferenceEnemyCatalog], suy từ AI Sim
 *   §VI) — archetype không có class nào khớp entity thật thì KHÔNG có target (không tự chọn class khác).
 * - **Distance:** khoảng cách Euclid thật (`WorldPosition.distanceTo`) — chọn Tower gần nhất.
 *
 * Vulnerability/Threat/SpecialInterest/PathCost KHÔNG model — không có dữ liệu nguồn để suy ra công thức.
 * Đây là bản triển khai MỘT PHẦN, không phải toàn bộ Target Selection.
 *
 * Hiện chỉ [com.lastoutpost.core.tower.TowerEntity] có entity thật thuộc [TargetClass.TOWER] (xem
 * [TargetClass] KDoc) — nên chỉ archetype có `TOWER` trong `preferredTargetClasses` mới thật sự tìm được
 * mục tiêu ở bước triển khai này.
 */
class EnemyTargetingSystem(
    private val catalog: Map<String, ReferenceEnemyCatalog.Entry> = ReferenceEnemyCatalog.bySpeciesId,
) : TickSystem {
    override val stage = TickStage.TARGET_ACQUISITION

    override fun update(world: BattleWorld) {
        for (enemyId in world.entities.enemyIds) {
            val enemy = world.entities.enemy(enemyId) ?: continue
            val preferred = catalog[enemy.speciesId]?.preferredTargetClasses ?: emptyList()
            if (TargetClass.TOWER !in preferred) continue // archetype này không có entity thật khớp objective của nó

            val nearestTowerId = world.entities.towerIds
                .mapNotNull { id -> world.entities.tower(id)?.let { id to it } }
                .minByOrNull { (_, tower) -> tower.position.distanceTo(enemy.position) }
                ?.first

            if (nearestTowerId != enemy.targeting.currentTargetId) {
                world.entities.updateEnemy(enemyId) { it.copy(targeting = Targeting(nearestTowerId)) }
            }
        }
    }
}
