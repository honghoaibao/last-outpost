package com.lastoutpost.game

import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.wave.FixedRoster
import com.lastoutpost.core.wave.SpawnGroup
import com.lastoutpost.core.wave.WaveDirector
import java.util.Locale

/**
 * Chạy một trận headless ngắn để chứng minh :core được nối vào app (không render, không gameplay thật).
 * Dữ liệu ở đây chỉ phục vụ debug — mission thật sẽ được nạp từ content/ (milestone Content Loader).
 */
internal fun runHeadlessSmokeTest(): List<String> {
    val roster = FixedRoster(setOf("ENEMY_CRAWLER"))
    val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 25, speciesId = "ENEMY_CRAWLER", count = 3)))
    val world = BattleWorld(
        missionId = "MISSION_TUTORIAL_001",
        contentVersion = "smoke",
        seed = 1L,
        systems = listOf(director),
    )
    world.advanceTo(BattlePhase.LOAD_CONTENT)
    world.advanceTo(BattlePhase.BUILD_WORLD)
    world.advanceTo(BattlePhase.PREPARE)
    world.advanceTo(BattlePhase.COMBAT)

    val ticks = world.config.ticksPerSecond * 2
    repeat(ticks) { world.step() }

    return listOf(
        "phase = ${world.phase}",
        "tick = ${world.tick} (${"%.1f".format(Locale.ROOT, world.elapsedSeconds)} giây mô phỏng)",
        "spawn intent = ${world.spawnQueue.size}",
        "event = ${world.eventLog.joinToString { it.type }}",
    )
}
