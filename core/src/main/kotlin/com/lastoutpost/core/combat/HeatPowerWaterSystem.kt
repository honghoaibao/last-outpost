package com.lastoutpost.core.combat

import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem

/**
 * Heat / Power / Water — TickStage.HEAT_POWER_WATER (Tech Architecture §IV, stage 11). CHỈ triển khai
 * Cooling của Heat System (System Gameplay §12.1: `New Heat = Old Heat + Shot Heat − Cooling`; Shot Heat đã
 * được cộng ở [TowerAttackSystem] lúc bắn, ở stage trước).
 *
 * Power (§10.3) và Water (§10.4) CHƯA có công thức update cụ thể trong tài liệu — chỉ mô tả khái niệm
 * (generation/consumption, priority allocation Life Support > Critical Cooling > Optional Cooling >
 * Utility) mà không cho per-tick formula nào — KHÔNG model ở đây để tránh bịa cơ chế (TECH_DEBT).
 *
 * [coolingPerTick] keyed theo `barrelId`, PHẢI truyền từ ngoài — Cooling thật phụ thuộc Base/Module/Water/
 * Environment/Commander (§12.1), các thành phần đó CHƯA model riêng, gộp vào một số duy nhất (OQ-DESIGN-02).
 */
class HeatPowerWaterSystem(private val coolingPerTick: Map<String, Int>) : TickSystem {
    override val stage = TickStage.HEAT_POWER_WATER

    override fun update(world: BattleWorld) {
        for (towerId in world.entities.towerIds) {
            val tower = world.entities.tower(towerId) ?: continue
            val heat = tower.heat ?: continue
            val cooling = coolingPerTick[tower.barrel.barrelId] ?: continue
            if (cooling <= 0 || heat.current <= 0) continue

            val newCurrent = (heat.current - cooling).coerceAtLeast(0)
            world.entities.updateTower(towerId) { it.copy(heat = heat.copy(current = newCurrent)) }
        }
    }
}
