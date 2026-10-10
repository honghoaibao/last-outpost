package com.lastoutpost.core.enemy

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem

/**
 * Movement — AI & Simulation Design §IV (Pathfinding), TickStage.MOVEMENT.
 *
 * - **GROUND**: đi theo [com.lastoutpost.core.battlefield.PathGraph] (§IV.1: "navigation graph/cost map
 *   thay vì mỗi enemy chạy A* độc lập") — mỗi tick tái tính đường ngắn nhất tới Core (đủ rẻ ở quy mô Beta
 *   01), bước tối đa `speed` về phía node kế tiếp trên đường đó, snap khi đủ gần.
 * - **FLYING**: "không bị ràng buộc bởi ground path" (§IV.2) — bay thẳng tới vị trí Core. "Attack corridor"/
 *   obstacle avoidance riêng CHƯA có tiêu chí cụ thể trong tài liệu → CHƯA model (TECH_DEBT).
 * - **BURROW**: "dùng underground graph simplification, chỉ cập nhật điểm nổi lên hợp lệ" (§IV.3) — CHƯA có
 *   underground graph nào tồn tại → KHÔNG di chuyển ở hệ thống này (đứng yên tại điểm spawn, TECH_DEBT).
 *
 * Không có [battlefield] hoặc chưa cấu hình `coreNodeId`/entry node → enemy đứng yên (không crash, không
 * bịa đường đi). Khi enemy tới Core, dừng lại và phát `enemy_reached_core` — Mission Objective (CHƯA triển
 * khai) sẽ xử lý hệ quả thật (Base Core chưa có Health/entity — TECH_DEBT).
 */
class MovementSystem(private val battlefield: Battlefield? = null) : TickSystem {
    override val stage = TickStage.MOVEMENT

    override fun update(world: BattleWorld) {
        for (enemyId in world.entities.enemyIds) {
            val enemy = world.entities.enemy(enemyId) ?: continue
            val speed = enemy.movement.speed ?: continue // chưa có số liệu tốc độ (OQ-CONTENT-02) → không di chuyển

            when (enemy.movement.type) {
                MovementType.FLYING -> moveFlying(world, enemyId, enemy, speed)
                MovementType.GROUND -> moveOnGraph(world, enemyId, enemy, speed)
                MovementType.BURROW -> Unit // underground graph riêng chưa tồn tại (AI Sim §IV.3) — xem KDoc lớp này
            }
        }
    }

    private fun moveFlying(world: BattleWorld, id: Long, enemy: EnemyEntity, speed: Double) {
        val target = battlefield?.corePosition ?: return
        val next = stepToward(enemy.position, target, speed)
        world.entities.updateEnemy(id) { it.copy(position = next) }
        if (next == target) world.emit("enemy_reached_core", "enemy=$id")
    }

    private fun moveOnGraph(world: BattleWorld, id: Long, enemy: EnemyEntity, speed: Double) {
        val field = battlefield ?: return
        val coreId = field.coreNodeId ?: return
        val currentNodeId = enemy.movement.currentNodeId ?: return // enemy không được gắn vào Path Graph (không spawn qua battlefield) → không path được
        if (currentNodeId == coreId) return // đã tới Core

        val path = field.paths.shortestPath(currentNodeId, coreId) ?: return // đồ thị đứt (barricade chặn hết) → đứng yên
        if (path.size < 2) return
        val nextNodeId = path[1]
        val nextNode = field.paths.node(nextNodeId) ?: return

        val next = stepToward(enemy.position, nextNode.position, speed)
        if (next == nextNode.position) {
            world.entities.updateEnemy(id) { it.copy(position = next, movement = it.movement.copy(currentNodeId = nextNodeId)) }
            if (nextNodeId == coreId) world.emit("enemy_reached_core", "enemy=$id")
        } else {
            world.entities.updateEnemy(id) { it.copy(position = next) }
        }
    }

    /** Bước tối đa [speed] từ [current] hướng về [target]; trả về đúng [target] nếu đã đủ gần (tránh vượt quá). */
    private fun stepToward(current: WorldPosition, target: WorldPosition, speed: Double): WorldPosition {
        val distance = current.distanceTo(target)
        if (distance <= speed || distance == 0.0) return target
        val ratio = speed / distance
        return WorldPosition(current.x + (target.x - current.x) * ratio, current.y + (target.y - current.y) * ratio)
    }
}
