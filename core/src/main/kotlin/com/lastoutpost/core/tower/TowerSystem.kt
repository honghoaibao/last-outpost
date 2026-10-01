package com.lastoutpost.core.tower

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.BuildableNode
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.Command
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem

/**
 * Kết quả kiểm tra đặt Tower. Player UX Flow §8.3 mô tả 3 mức (xanh/vàng/đỏ); mức "vàng" (hợp lệ nhưng
 * có trade-off) cần baseStats để tính nghĩa thật — CHƯA model (OQ-DESIGN-02). Ở đây chỉ có Valid/Invalid.
 */
sealed interface PlacementCheck {
    data object Valid : PlacementCheck
    data class Invalid(val reason: String) : PlacementCheck
}

/**
 * Tower system — Tech Architecture Gameplay Runtime. Xử lý [Command.BuildTower]/[Command.CancelBuild] của
 * đúng tick hiện tại ([BattleWorld.commandsThisTick]), tạo/xoá entity qua [BattleWorld.entities], và
 * chiếm/nhả [BuildableNode] qua [battlefield] (ADR-0005 — đặt Tower vào node, không phải toạ độ tự do).
 *
 * Validation theo System & Gameplay Design §4.2 ("node hợp lệ", "không chồng công trình cấm", "đáp ứng mount
 * requirement"). CHƯA kiểm: đủ Scrap (chưa nối Ledger), Tech requirement (chưa có Tech Tree), lane bắt buộc
 * của mission (chưa có khái niệm "khoá lane"), và compatibility base/barrel/module (OQ-DESIGN-02).
 */
class TowerSystem(
    private val battlefield: Battlefield,
    private val bases: Map<String, BaseDefinition> = ReferenceBaseCatalog.all,
    private val barrelCatalog: Set<String> = emptySet(),
    private val moduleCatalog: Set<String> = emptySet(),
) : TickSystem {
    override val stage = TickStage.INPUT_COMMANDS

    override fun update(world: BattleWorld) {
        for (command in world.commandsThisTick) {
            when (command) {
                is Command.BuildTower -> build(world, command)
                is Command.CancelBuild -> cancel(world, command)
                else -> Unit
            }
        }
    }

    /** Kiểm tra độc lập với việc áp dụng — dùng cho UI feedback (xanh/đỏ) trước khi gửi command. */
    fun validate(command: Command.BuildTower): PlacementCheck {
        val base = bases[command.baseId]
            ?: return PlacementCheck.Invalid("Base ${command.baseId} không tồn tại")
        if (command.barrelId !in barrelCatalog) {
            return PlacementCheck.Invalid("Barrel ${command.barrelId} không tồn tại")
        }
        if (command.moduleId !in moduleCatalog) {
            return PlacementCheck.Invalid("Module ${command.moduleId} không tồn tại")
        }
        val node = battlefield.node(command.nodeId)
            ?: return PlacementCheck.Invalid("Node ${command.nodeId} không tồn tại")
        if (node.isOccupied) {
            return PlacementCheck.Invalid("Node ${command.nodeId} đã có công trình")
        }
        if (node.mountType !in base.allowedMounts) {
            return PlacementCheck.Invalid("Không hỗ trợ ${node.mountType.label}")
        }
        return PlacementCheck.Valid
    }

    private fun build(world: BattleWorld, command: Command.BuildTower) {
        when (val check = validate(command)) {
            is PlacementCheck.Invalid -> world.emit("build_rejected", check.reason)
            PlacementCheck.Valid -> {
                val node: BuildableNode = battlefield.node(command.nodeId)!! // validate() đã xác nhận tồn tại
                val id = world.entities.createTower(
                    position = node.position,
                    base = BaseState(command.baseId),
                    barrel = BarrelState(command.barrelId),
                    module = ModuleState(command.moduleId),
                )
                battlefield.occupy(command.nodeId, id)
                world.emit("build_accepted", "tower=$id base=${command.baseId} node=${command.nodeId}")
            }
        }
    }

    private fun cancel(world: BattleWorld, command: Command.CancelBuild) {
        if (world.entities.destroyTower(command.entityId)) {
            vacateNodeOf(command.entityId)
            world.emit("build_cancelled", "tower=${command.entityId}")
        } else {
            world.emit("build_cancel_rejected", "tower=${command.entityId} không tồn tại")
        }
    }

    /** Tìm node đang bị chiếm bởi entity vừa xoá và trả nó về trạng thái trống. */
    private fun vacateNodeOf(entityId: Long) {
        val nodeId = battlefield.nodeIds.firstOrNull { battlefield.node(it)?.occupiedBy == entityId } ?: return
        battlefield.vacate(nodeId)
    }
}
