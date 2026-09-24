package com.lastoutpost.core.sim

/**
 * Input không mutate runtime trực tiếp: Player Input → Command → Simulation Queue → System Apply
 * (Tech Architecture §XIX). Lợi ích: input buffering, replay/debug, test tự động.
 *
 * Danh sách command lấy từ tài liệu. Trường dữ liệu chi tiết là KHUNG — sẽ chốt khi có
 * quyết định về grid/toạ độ và Commander skill kit (OPEN, xem docs/open-questions).
 */
sealed interface Command {
    /** Tower = Base + Barrel + Module (Current Decisions §2). */
    data class BuildTower(val baseId: String, val barrelId: String, val moduleId: String, val x: Int, val y: Int) : Command
    data class MoveCommander(val x: Int, val y: Int) : Command
    data class UseAbility(val abilityId: String, val x: Int? = null, val y: Int? = null) : Command
    data class Repair(val entityId: Long) : Command
    data class PrioritizeTarget(val entityId: Long) : Command
    data class CancelBuild(val entityId: Long) : Command
}

/** Command đã được áp dụng, kèm tick áp dụng — phục vụ replay/debug. */
data class CommandRecord(val tick: Long, val command: Command)

class CommandQueue {
    private val pending = ArrayDeque<Command>()

    val size: Int get() = pending.size

    fun submit(command: Command) {
        pending.addLast(command)
    }

    /** Lấy toàn bộ command đang chờ theo thứ tự gửi và làm rỗng hàng đợi. */
    fun drain(): List<Command> {
        val out = pending.toList()
        pending.clear()
        return out
    }
}
