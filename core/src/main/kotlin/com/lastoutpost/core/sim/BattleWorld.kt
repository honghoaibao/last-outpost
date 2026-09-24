package com.lastoutpost.core.sim

import kotlin.random.Random

/** Sự kiện mô phỏng đã lấy mẫu — điểm móc cho analytics/replay (Tech Architecture §XXVI). */
data class WorldEvent(val tick: Long, val type: String, val detail: String)

/** Yêu cầu spawn do Wave Director phát ra; hệ thống Spawn (chưa triển khai) sẽ tiêu thụ. */
data class SpawnIntent(val tick: Long, val speciesId: String, val count: Int, val lane: Int?)

/**
 * BattleWorld — Tech Architecture §III.
 * Không mutate account persistent trực tiếp: kết quả đi qua Reward Calculator → Transaction layer
 * (xem [com.lastoutpost.core.economy.Ledger]).
 */
class BattleWorld(
    val missionId: String,
    val contentVersion: String,
    val seed: Long,
    val config: SimConfig = SimConfig(),
    systems: List<TickSystem> = emptyList(),
) {
    private val pipeline = TickPipeline(listOf(CommandIntakeSystem()) + systems)

    var phase: BattlePhase = BattlePhase.BOOT
        private set

    /** Chỉ số tick hiện tại (tick đầu tiên là 0). */
    var tick: Long = 0L
        private set

    var currentWave: Int = 0

    var resultState: ResultState = ResultState.NONE
        private set

    val elapsedSeconds: Double get() = tick.toDouble() / config.ticksPerSecond

    /** RNG có seed — cùng seed + cùng command → cùng kết quả (deterministic-friendly). */
    val rng: Random = Random(seed)

    val commands = CommandQueue()
    val commandLog = mutableListOf<CommandRecord>()
    val eventLog = mutableListOf<WorldEvent>()
    val spawnQueue = ArrayDeque<SpawnIntent>()

    fun emit(type: String, detail: String = "") {
        eventLog += WorldEvent(tick, type, detail)
    }

    /** Chuyển sang phase kế tiếp; chỉ cho phép đi tuần tự đúng vòng đời. */
    fun advanceTo(target: BattlePhase) {
        check(phase.next() == target) { "Chuyển phase không hợp lệ: $phase → $target (hợp lệ: ${phase.next()})" }
        check(target != BattlePhase.CALCULATE_RESULT) { "Dùng endCombat(result) để rời COMBAT" }
        phase = target
    }

    /** Kết thúc combat với một kết quả và chuyển sang CALCULATE_RESULT. */
    fun endCombat(result: ResultState) {
        check(phase == BattlePhase.COMBAT) { "Chỉ kết thúc combat khi đang ở COMBAT (hiện tại: $phase)" }
        require(result != ResultState.NONE) { "Kết quả trận không được là NONE" }
        resultState = result
        phase = BattlePhase.CALCULATE_RESULT
    }

    /**
     * Chạy đúng một tick nếu đang ở COMBAT.
     * @return true nếu tick đã chạy.
     */
    fun step(): Boolean {
        if (phase != BattlePhase.COMBAT) return false
        pipeline.run(this)
        tick += 1
        return true
    }
}
