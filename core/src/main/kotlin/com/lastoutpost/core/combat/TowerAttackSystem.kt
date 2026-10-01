package com.lastoutpost.core.combat

import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.DamageEvent
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem
import com.lastoutpost.core.tower.Attack

/**
 * Tower Attack — Combat Resolution §6.1, phần **Fire → Resolve Hit** (đúng TickStage.ATTACK_PROJECTILE).
 * Beta 01 KHÔNG mô phỏng travel time/projectile bay nhiều tick — Tech Architecture §V chỉ liệt kê
 * TowerEntity/EnemyEntity, không có ProjectileEntity riêng, nên Fire→Resolve Hit coi là tức thời (hitscan)
 * trong cùng tick khi hết cooldown và có target khoá. Apply Effect/Update Heat-Ammo-Power thuộc bước sau
 * ([DamageResolutionSystem], TickStage.DAMAGE_DEATH) hoặc milestone Logistics riêng (Ammo/Heat/Power CHƯA
 * trừ ở đây — TECH_DEBT).
 *
 * [attackProfiles] keyed theo `barrelId` — Tower có barrel chưa có profile thì KHÔNG bắn (không bịa số liệu).
 */
class TowerAttackSystem(private val attackProfiles: Map<String, AttackProfile>) : TickSystem {
    override val stage = TickStage.ATTACK_PROJECTILE

    override fun update(world: BattleWorld) {
        for (towerId in world.entities.towerIds) {
            val tower = world.entities.tower(towerId) ?: continue
            val targetId = tower.targeting.currentTargetId ?: continue
            if (world.entities.enemy(targetId) == null) continue // mục tiêu đã mất — Target Acquisition sẽ khoá lại target khác ở tick sau

            val profile = attackProfiles[tower.barrel.barrelId] ?: continue

            val cooldown = tower.attack.cooldownRemainingTicks
            if (cooldown > 0) {
                world.entities.updateTower(towerId) { it.copy(attack = Attack(cooldown - 1)) }
                continue
            }

            world.damageQueue.addLast(DamageEvent(towerId, targetId, profile.baseDamage, profile.damageTag))
            world.entities.updateTower(towerId) { it.copy(attack = Attack(profile.cooldownTicks)) }
            world.emit("tower_fired", "tower=$towerId target=$targetId barrel=${tower.barrel.barrelId}")
        }
    }
}
