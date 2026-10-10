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
 * trong cùng tick khi hết cooldown và có target khoá. Apply Effect thuộc bước sau ([DamageResolutionSystem],
 * TickStage.DAMAGE_DEATH).
 *
 * **Ammo/Heat gate** (System Gameplay §10.2/§XII): Tower không bắn khi hết Ammo hoặc đang Jam (Heat ở mức
 * 100%, §12.2/§12.3). Chỉ áp dụng khi `TowerEntity.ammo`/`heat` đã có giá trị — `null` (mặc định, chưa có
 * content canon — OQ-DESIGN-02) nghĩa là không giới hạn. Cooling (giảm Heat theo thời gian) thuộc
 * [HeatPowerWaterSystem] (TickStage.HEAT_POWER_WATER, chạy sau). Resupply Ammo thuộc Logistics System
 * (§XI, mạng lưới Storage→Hub→Route→Cluster riêng) — CHƯA triển khai, Ammo chỉ giảm không bao giờ tự đầy.
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

            val heat = tower.heat
            if (heat != null && heat.current >= heat.max) {
                world.emit("tower_heat_locked", "tower=$towerId heat=${heat.current}/${heat.max}")
                continue // chờ Cooling (HeatPowerWaterSystem) hạ nhiệt dưới mức Jam
            }

            val ammo = tower.ammo
            if (ammo != null && ammo.current <= 0) {
                world.emit("tower_out_of_ammo", "tower=$towerId")
                continue
            }

            world.damageQueue.addLast(DamageEvent(towerId, targetId, profile.baseDamage, profile.damageTag))
            val newAmmo = ammo?.let { it.copy(current = (it.current - profile.ammoPerShot).coerceAtLeast(0)) }
            val newHeat = heat?.let { it.copy(current = (it.current + profile.shotHeat).coerceAtMost(it.max)) }
            world.entities.updateTower(towerId) { it.copy(attack = Attack(profile.cooldownTicks), ammo = newAmmo, heat = newHeat) }
            world.emit("tower_fired", "tower=$towerId target=$targetId barrel=${tower.barrel.barrelId}")
        }
    }
}
