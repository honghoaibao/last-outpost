package com.lastoutpost.core.wave

import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.SpawnIntent
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem

/**
 * Fixed Enemy Roster của một mission (Current Decisions §1–2).
 * Wave Director chỉ được chọn species nằm trong roster này.
 */
class FixedRoster(speciesIds: Set<String>) {
    val speciesIds: Set<String> = speciesIds.toSet()

    init {
        require(this.speciesIds.isNotEmpty()) { "Fixed Roster không được rỗng" }
    }

    fun allows(speciesId: String): Boolean = speciesId in speciesIds
}

/** Một nhóm spawn trong kế hoạch wave. Số lượng/thời điểm/lane được phép đổi; species thì không ngoài roster. */
data class SpawnGroup(val atTick: Long, val speciesId: String, val count: Int, val lane: Int? = null) {
    init {
        require(atTick >= 0) { "atTick không được âm" }
        require(count >= 1) { "count phải >= 1" }
    }
}

class RosterViolationException(val violations: List<SpawnGroup>, val roster: FixedRoster) :
    IllegalArgumentException(
        "Wave plan spawn species ngoài Fixed Roster: " +
            violations.joinToString { it.speciesId } +
            " (roster hợp lệ: ${roster.speciesIds.sorted().joinToString()})",
    )

sealed interface SpawnDecision {
    data class Accepted(val intent: SpawnIntent) : SpawnDecision
    data class Rejected(val group: SpawnGroup, val reason: String) : SpawnDecision
}

/**
 * Wave Director — Tech Architecture §XII. Độc lập với Enemy AI.
 *
 * LUẬT BẮT BUỘC: không spawn species ngoài Fixed Roster của mission.
 * - Kế hoạch tĩnh ([plan]) được kiểm tra ngay khi khởi tạo (fail-fast).
 * - Spawn động qua [tryQueue] bị từ chối nếu ngoài roster.
 *
 * Phạm vi hiện tại: phát [SpawnIntent] theo kế hoạch. Threat Budget, escalation, lane và special event
 * là việc của milestone Combat (docs/production/BETA_01_STATUS.md).
 */
class WaveDirector(
    private val roster: FixedRoster,
    plan: List<SpawnGroup>,
) : TickSystem {
    override val stage = TickStage.WAVE_DIRECTOR

    private val plan: List<SpawnGroup> = plan.sortedBy { it.atTick }
    private var cursor = 0

    init {
        val violations = this.plan.filterNot { roster.allows(it.speciesId) }
        if (violations.isNotEmpty()) throw RosterViolationException(violations, roster)
    }

    override fun update(world: BattleWorld) {
        while (cursor < plan.size && plan[cursor].atTick <= world.tick) {
            val group = plan[cursor]
            cursor += 1
            val decision = tryQueue(world, group.copy(atTick = world.tick))
            if (decision is SpawnDecision.Accepted) {
                world.emit("spawn_intent", "${group.speciesId} x${group.count}")
            }
        }
    }

    /** Đưa một nhóm spawn vào hàng đợi của world nếu hợp lệ theo roster. */
    fun tryQueue(world: BattleWorld, group: SpawnGroup): SpawnDecision {
        if (!roster.allows(group.speciesId)) {
            world.emit("spawn_rejected", "${group.speciesId} ngoài Fixed Roster")
            return SpawnDecision.Rejected(group, "Species ${group.speciesId} không thuộc Fixed Roster của mission")
        }
        val intent = SpawnIntent(world.tick, group.speciesId, group.count, group.lane)
        world.spawnQueue.addLast(intent)
        return SpawnDecision.Accepted(intent)
    }
}
