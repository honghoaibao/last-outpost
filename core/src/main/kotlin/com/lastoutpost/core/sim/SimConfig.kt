package com.lastoutpost.core.sim

/**
 * Cấu hình mô phỏng.
 *
 * Tech Architecture §IV: simulation chạy fixed tick "khoảng 20–30 ticks/s".
 * Giá trị mặc định 25 là GIẢ ĐỊNH tạm thời — xem OPEN QUESTION OQ-TECH-04 (docs/open-questions).
 */
data class SimConfig(
    val ticksPerSecond: Int = DEFAULT_TICKS_PER_SECOND,
    /** Chặn "spiral of death": tối đa số tick chạy bù trong một frame render. */
    val maxTicksPerFrame: Int = DEFAULT_MAX_TICKS_PER_FRAME,
) {
    init {
        require(ticksPerSecond in MIN_TICKS_PER_SECOND..MAX_TICKS_PER_SECOND) {
            "ticksPerSecond phải trong khoảng $MIN_TICKS_PER_SECOND–$MAX_TICKS_PER_SECOND (Tech Architecture §IV), nhận $ticksPerSecond"
        }
        require(maxTicksPerFrame >= 1) { "maxTicksPerFrame phải >= 1" }
    }

    /** Độ dài một tick tính bằng nanosecond (số nguyên để tránh trôi số thực). */
    val tickNanos: Long get() = NANOS_PER_SECOND / ticksPerSecond

    companion object {
        const val MIN_TICKS_PER_SECOND = 20
        const val MAX_TICKS_PER_SECOND = 30
        const val DEFAULT_TICKS_PER_SECOND = 25
        const val DEFAULT_MAX_TICKS_PER_FRAME = 5
        const val NANOS_PER_SECOND = 1_000_000_000L
    }
}
