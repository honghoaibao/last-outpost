package com.lastoutpost.core.mission

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.enemy.EnemyAttack
import com.lastoutpost.core.enemy.EnemyEntity
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem

/**
 * Số liệu enemy đánh Base Core, keyed theo `speciesId` — CHƯA có trong content thật (OQ-CONTENT-02), PHẢI
 * truyền từ ngoài (cùng mẫu với `AttackProfile` của Tower). [damage]/[cooldownTicks] KHÔNG phải số liệu canon.
 */
data class CoreAssaultProfile(val damage: Int, val cooldownTicks: Int)

/**
 * Enemy tấn công Base Core — System & Gameplay Design v1.1 §17.1–17.2 (TickStage.ATTACK_PROJECTILE, cùng stage
 * với tấn công của Tower). Enemy đã tới Core (GROUND: đứng ở `coreNodeId`; FLYING: đứng đúng `corePosition`)
 * và có [profiles] sẽ đánh Core mỗi [CoreAssaultProfile.cooldownTicks] tick; HP Core giảm DẦN, không chết
 * tức thì ("Không nên tạo instant death bất ngờ" — §17.2). Enemy KHÔNG biến mất khi tới Core: vẫn có thể bị
 * Tower bắn.
 *
 * Species không có profile thì không gây damage cho Core (không bịa số liệu). Chưa có: Shield, ngưỡng
 * emergency, và enemy đánh Tower/công trình khác (TECH_DEBT).
 */
class CoreAssaultSystem(
    private val battlefield: Battlefield,
    private val profiles: Map<String, CoreAssaultProfile>,
) : TickSystem {
    override val stage = TickStage.ATTACK_PROJECTILE

    override fun update(world: BattleWorld) {
        val core = world.core ?: return
        if (core.isDestroyed) return
        val corePosition = battlefield.corePosition ?: return

        var health = core.health
        for (enemyId in world.entities.enemyIds) {
            if (health.current <= 0) break // Core đã sập trong tick này — không đánh thêm
            val enemy = world.entities.enemy(enemyId) ?: continue
            val profile = profiles[enemy.speciesId] ?: continue
            if (!isAtCore(enemy, corePosition)) continue

            val cooldown = enemy.attack.cooldownRemainingTicks
            if (cooldown > 0) {
                world.entities.updateEnemy(enemyId) { it.copy(attack = EnemyAttack(cooldown - 1)) }
                continue
            }

            health = health.copy(current = (health.current - profile.damage).coerceAtLeast(0))
            world.entities.updateEnemy(enemyId) { it.copy(attack = EnemyAttack(profile.cooldownTicks)) }
            world.emit("core_damaged", "enemy=$enemyId amount=${profile.damage} core=${health.current}/${health.max}")
        }
        if (health != core.health) world.core = core.copy(health = health)
    }

    private fun isAtCore(enemy: EnemyEntity, corePosition: WorldPosition): Boolean =
        enemy.movement.currentNodeId == battlefield.coreNodeId || enemy.position == corePosition
}
