package com.lastoutpost.core.mission

import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.ResultState
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem
import com.lastoutpost.core.wave.WaveDirector

/**
 * Mission Objective — System & Gameplay Design v1.1 §17.3–17.4 (TickStage.OBJECTIVES, stage 12):
 * - **Defeat (mặc định):** `Core Destroyed`. Defeat phụ chỉ là objective penalty trừ khi mission nói rõ —
 *   CHƯA có defeat phụ nào.
 * - **Victory (mặc định):** `Survive Final Wave AND Core Alive`.
 * Defeat được xét TRƯỚC Victory (Core đã sập thì không thể "Core Alive").
 *
 * "Survive Final Wave" được diễn giải là: [waves] đã phát hết kế hoạch spawn ([WaveDirector.isPlanExhausted])
 * VÀ không còn enemy nào trên sân VÀ hàng đợi spawn rỗng. Đây là DIỄN GIẢI (tài liệu không định nghĩa "survive"
 * — có thể là hết giờ thay vì tiêu diệt sạch) — xem OQ-DESIGN-11. Không có [waves] thì không bao giờ tự Victory.
 *
 * Mission chưa cấu hình `BattleWorld.core` thì hệ thống này không làm gì (không tự kết thúc trận).
 * Kết thúc qua [BattleWorld.endCombat] → CALCULATE_RESULT; Battle Report/commit phần thưởng thuộc milestone sau.
 */
class MissionObjectiveSystem(private val waves: WaveDirector? = null) : TickSystem {
    override val stage = TickStage.OBJECTIVES

    override fun update(world: BattleWorld) {
        if (world.phase != BattlePhase.COMBAT) return
        val core = world.core ?: return

        if (core.isDestroyed) {
            world.emit("mission_defeat", "Core Destroyed")
            world.endCombat(ResultState.DEFEAT)
            return
        }

        val director = waves ?: return
        if (director.isPlanExhausted && world.spawnQueue.isEmpty() && world.entities.enemyIds.isEmpty()) {
            world.emit("mission_victory", "Final Wave cleared, Core Alive (${core.health.current}/${core.health.max})")
            world.endCombat(ResultState.VICTORY)
        }
    }
}
