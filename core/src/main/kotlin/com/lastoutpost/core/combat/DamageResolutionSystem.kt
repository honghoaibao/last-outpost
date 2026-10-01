package com.lastoutpost.core.combat

import com.lastoutpost.core.entity.Health
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem

/**
 * Damage Resolution — Combat Resolution §6.1, phần **Apply Effect** (TickStage.DAMAGE_DEATH, đúng thứ tự
 * sau Status Effects trong Tech Architecture §IV). Tiêu thụ [BattleWorld.damageQueue] do [TowerAttackSystem]
 * phát ra, áp dụng Damage Model §6.3 ([DamageFormula]), trừ Health, và xoá entity khi HP về 0.
 *
 * Target đã chết/mất trước khi resolve (vd trúng nhiều phát trong cùng tick) thì bỏ qua sự kiện đó — không lỗi.
 * Target chưa có `health` (luôn `null` trong gameplay thật — OQ-CONTENT-02) thì phát bắn coi như không có
 * tác dụng (ghi sự kiện `damage_no_effect`), KHÔNG tự đặt HP giả để trừ.
 */
class DamageResolutionSystem : TickSystem {
    override val stage = TickStage.DAMAGE_DEATH

    override fun update(world: BattleWorld) {
        while (world.damageQueue.isNotEmpty()) {
            val event = world.damageQueue.removeFirst()
            val target = world.entities.enemy(event.targetId) ?: continue

            val health = target.health
            if (health == null) {
                world.emit("damage_no_effect", "target=${event.targetId} chưa có Health (OQ-CONTENT-02)")
                continue
            }

            val targetInteraction = target.resistances?.values?.get(event.tag) ?: 1.0
            val raw = DamageFormula.finalDamage(baseDamage = event.baseDamage, targetInteraction = targetInteraction)
            val applied = DamageFormula.damageAfterArmor(raw, target.armor ?: 0.0)

            val newCurrent = (health.current - applied).coerceAtLeast(0)
            world.entities.updateEnemy(event.targetId) { it.copy(health = Health(newCurrent, health.max)) }
            world.emit("damage_applied", "target=${event.targetId} amount=$applied tag=${event.tag}")

            if (newCurrent <= 0) {
                world.entities.destroyEnemy(event.targetId)
                world.emit("enemy_died", "target=${event.targetId} source=${event.sourceId}")
            }
        }
    }
}
