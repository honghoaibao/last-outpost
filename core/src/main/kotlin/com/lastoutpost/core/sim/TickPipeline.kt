package com.lastoutpost.core.sim

/** 13 bước của một tick, đúng thứ tự Tech Architecture §IV. */
enum class TickStage {
    INPUT_COMMANDS,
    WAVE_DIRECTOR,
    SPAWN_DESPAWN,
    MOVEMENT,
    TARGET_ACQUISITION,
    ATTACK_PROJECTILE,
    STATUS_EFFECTS,
    DAMAGE_DEATH,
    CORPSE_TERRAIN_EFFECTS,
    LOGISTICS,
    HEAT_POWER_WATER,
    OBJECTIVES,
    EVENT_SAMPLING,
}

/** Một hệ thống mô phỏng, gắn vào đúng một [TickStage]. */
interface TickSystem {
    val stage: TickStage
    fun update(world: BattleWorld)
}

/**
 * Chạy các system theo thứ tự stage ổn định (cùng stage thì giữ thứ tự đăng ký),
 * để dễ debug và replay.
 */
class TickPipeline(systems: List<TickSystem>) {
    private val ordered: List<TickSystem> = systems.sortedBy { it.stage.ordinal } // sortedBy là stable sort

    fun run(world: BattleWorld) {
        for (system in ordered) system.update(world)
    }

    fun stagesInOrder(): List<TickStage> = ordered.map { it.stage }
}

/** Stage 1: nhận command trong hàng đợi. Hiện chỉ ghi nhận (log + event); xử lý thật thuộc các hệ thống P0 sau. */
internal class CommandIntakeSystem : TickSystem {
    override val stage = TickStage.INPUT_COMMANDS

    override fun update(world: BattleWorld) {
        for (command in world.commands.drain()) {
            world.commandLog += CommandRecord(world.tick, command)
            world.emit("command", command::class.simpleName ?: "Command")
        }
    }
}
