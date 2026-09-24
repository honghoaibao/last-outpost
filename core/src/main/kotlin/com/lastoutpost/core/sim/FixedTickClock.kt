package com.lastoutpost.core.sim

/**
 * Đồng hồ fixed-tick: tách nhịp mô phỏng khỏi nhịp render (Tech Architecture §IV).
 *
 * Lớp render gọi [advance] mỗi frame với thời gian thực đã trôi; nhận về số tick mô phỏng cần chạy.
 */
class FixedTickClock(private val config: SimConfig = SimConfig()) {
    private var accumulatorNanos = 0L

    /** Số nanosecond dư chưa đủ một tick. */
    val pendingNanos: Long get() = accumulatorNanos

    /** Hệ số nội suy 0..1 cho render giữa hai tick. */
    val alpha: Double get() = accumulatorNanos.toDouble() / config.tickNanos

    /**
     * @return số tick cần chạy. Nếu backlog vượt [SimConfig.maxTicksPerFrame] thì phần dư bị bỏ
     * (ưu tiên đáp ứng hơn là chạy bù vô hạn).
     */
    fun advance(frameNanos: Long): Int {
        require(frameNanos >= 0) { "frameNanos không được âm" }
        accumulatorNanos += frameNanos
        val due = accumulatorNanos / config.tickNanos
        return if (due > config.maxTicksPerFrame) {
            accumulatorNanos = 0L
            config.maxTicksPerFrame
        } else {
            accumulatorNanos -= due * config.tickNanos
            due.toInt()
        }
    }

    fun reset() {
        accumulatorNanos = 0L
    }
}
