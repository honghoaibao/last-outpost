package com.lastoutpost.core.battlefield

/**
 * Buildable Node — System & Gameplay Design v1.1 §3.2, đúng schema gốc: mountType, occupied, allowedTags,
 * height, stability, coverage, specialInteraction. Đây là abstraction gameplay CHÍNH cho việc đặt Tower
 * (ADR-0005) — người chơi chọn node hợp lệ, không tự do đặt theo toạ độ integer.
 *
 * [occupiedBy] thay cho `occupied` (boolean) trong tài liệu gốc — lưu ID entity đang chiếm node để
 * [Battlefield] có thể trả lại đúng entity khi cần (vd cancel build); `occupiedBy != null` tương đương
 * `occupied = true`.
 *
 * [stability]/[coverage]: tài liệu chỉ cho một ví dụ số (`stability = 80`), không nêu thang đo hay công
 * thức tiêu thụ — kiểu `Double?` ở đây là DIỄN GIẢI kỹ thuật tạm, KHÔNG phải giá trị/thang đo canon.
 * [specialInteraction] là tag tự do (vd `CLIFF_ANCHOR`) — hành vi thật của tag CHƯA triển khai.
 */
data class BuildableNode(
    val id: String,
    val position: WorldPosition,
    val mountType: MountType,
    val allowedTags: Set<String> = emptySet(),
    val height: HeightLayer? = null,
    val stability: Double? = null,
    val coverage: Double? = null,
    val specialInteraction: String? = null,
    val occupiedBy: Long? = null,
) {
    val isOccupied: Boolean get() = occupiedBy != null
}
