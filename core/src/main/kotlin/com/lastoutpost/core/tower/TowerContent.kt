package com.lastoutpost.core.tower

import com.lastoutpost.core.battlefield.MountType

/**
 * Base family — Core Combat Gameplay §2.2 (chassis: survivability/heat capacity/ammo capacity/mount
 * compatibility/mobility/logistics dependency). KHÁC với "Tower archetype" (6 vai trò combat: Single Target,
 * Anti Swarm, Armor Break, Control, Support/Logistics, Special/Environmental — System Gameplay §V.4/Core
 * Combat §III), vốn do Barrel quyết định. Hai trục độc lập của cùng một Tower. Số liệu cụ thể CHƯA canon
 * (OQ-DESIGN-02).
 */
enum class BaseFamily { TREADED, HEAVY_ANCHOR, HEAT_SINK, WALL_MOUNT, RAIL_MOUNT }

/**
 * TowerDefinition (một phần) — Tech Architecture §VI liệt kê thêm targeting/barrelRules/moduleRules/effects
 * và "archetype" (6 vai trò combat ở trên), nhưng schema các trường đó CHƯA được chốt (OQ-DESIGN-02) nên
 * KHÔNG model ở đây để tránh bịa cấu trúc giả. Chỉ model phần có quy tắc rõ ràng: family + mount compatibility.
 *
 * @property baseStats null cho tới khi có số liệu canon.
 */
data class BaseDefinition(
    val id: String,
    val contentVersion: Int,
    val family: BaseFamily,
    val allowedMounts: Set<MountType>,
    val baseStats: Map<String, Double>? = null,
)

/**
 * Danh mục Base tham chiếu, suy ra TRỰC TIẾP từ tên + mô tả định tính của 5 family trong
 * Core Combat Gameplay §2.2 (không phải số liệu balance, chỉ là family nào đi với mount nào).
 * Đây là diễn giải kỹ thuật tạm thời — cập nhật ngay khi có roster Tower chính thức (OQ-DESIGN-02).
 */
object ReferenceBaseCatalog {
    val TREADED = BaseDefinition("BASE_TREADED", 1, BaseFamily.TREADED, setOf(MountType.GROUND))
    val HEAVY_ANCHOR = BaseDefinition("BASE_HEAVY_ANCHOR", 1, BaseFamily.HEAVY_ANCHOR, setOf(MountType.GROUND))
    val HEAT_SINK = BaseDefinition("BASE_HEAT_SINK", 1, BaseFamily.HEAT_SINK, setOf(MountType.GROUND))
    val WALL_MOUNT = BaseDefinition("BASE_WALL_MOUNT", 1, BaseFamily.WALL_MOUNT, setOf(MountType.WALL))
    val RAIL_MOUNT = BaseDefinition("BASE_RAIL_MOUNT", 1, BaseFamily.RAIL_MOUNT, setOf(MountType.RAIL))

    val all: Map<String, BaseDefinition> =
        listOf(TREADED, HEAVY_ANCHOR, HEAT_SINK, WALL_MOUNT, RAIL_MOUNT).associateBy { it.id }
}
