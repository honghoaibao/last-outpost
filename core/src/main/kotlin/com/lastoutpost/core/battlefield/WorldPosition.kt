package com.lastoutpost.core.battlefield

import kotlin.math.sqrt

/**
 * Toạ độ thế giới liên tục (Cartesian 2D) — lớp biểu diễn không gian THẤP NHẤT của battlefield (ADR-0005).
 *
 * Đây KHÔNG phải abstraction gameplay chính: luật gameplay (đặt Tower, enemy di chuyển) dựa trên
 * [BuildableNode] và [PathGraph]/lane, không phải trực tiếp lên toạ độ này. [WorldPosition] chỉ dùng để:
 * định vị node/lane trong không gian, và đo khoảng cách khi luật cần (range/radius/AOE) qua [distanceTo].
 */
data class WorldPosition(val x: Double, val y: Double) {
    /** Khoảng cách Euclid — dùng cho range/radius/AOE khi rule cần (ADR-0005 điểm 3), không phải rule duy nhất. */
    fun distanceTo(other: WorldPosition): Double {
        val dx = x - other.x
        val dy = y - other.y
        return sqrt(dx * dx + dy * dy)
    }
}
