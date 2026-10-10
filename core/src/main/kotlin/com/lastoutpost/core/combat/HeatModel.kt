package com.lastoutpost.core.combat

import com.lastoutpost.core.tower.Heat

/** Heat band — System & Gameplay Design v1.1 §12.2, đúng bảng gốc (0–70% Normal, 70–90% Warm, 90–100% Critical, 100% Jam). */
enum class HeatBand { NORMAL, WARM, CRITICAL, JAM }

object HeatModel {
    /** Phân loại band theo % heat hiện tại (`current/max`). `max <= 0` coi như NORMAL (không có dữ liệu hợp lệ để chia). */
    fun band(heat: Heat): HeatBand {
        if (heat.max <= 0) return HeatBand.NORMAL
        val percent = heat.current.toDouble() / heat.max.toDouble() * 100.0
        return when {
            percent >= 100.0 -> HeatBand.JAM
            percent >= 90.0 -> HeatBand.CRITICAL
            percent >= 70.0 -> HeatBand.WARM
            else -> HeatBand.NORMAL
        }
    }
}
