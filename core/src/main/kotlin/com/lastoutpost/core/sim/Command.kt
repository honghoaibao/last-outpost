package com.lastoutpost.core.sim

import com.lastoutpost.core.battlefield.WorldPosition

/**
 * Input không mutate runtime trực tiếp: Player Input → Command → Simulation Queue → System Apply
 * (Tech Architecture §XIX). Lợi ích: input buffering, replay/debug, test tự động.
 *
 * Trường dữ liệu chi tiết là KHUNG — sẽ chốt khi có Commander skill kit (OPEN, xem docs/open-questions).
 * Không gian/vị trí theo ADR-0005: đặt Tower dùng Buildable Node (không phải toạ độ tự do); di chuyển tự do
 * (Commander, ability nhắm vị trí) dùng [WorldPosition] (Cartesian, layer thấp nhất — ADR-0005 điểm 3).
 */
sealed interface Command {
    /** Tower = Base + Barrel + Module (Current Decisions §2). Đặt vào Buildable Node — ADR-0005 điểm 4. */
    data class BuildTower(val baseId: String, val barrelId: String, val moduleId: String, val nodeId: String) : Command
    data class MoveCommander(val target: WorldPosition) : Command
    data class UseAbility(val abilityId: String, val target: WorldPosition? = null) : Command
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
