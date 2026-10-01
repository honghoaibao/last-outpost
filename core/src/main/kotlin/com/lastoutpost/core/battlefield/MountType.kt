package com.lastoutpost.core.battlefield

/**
 * Loại mount của một [BuildableNode] — System & Gameplay Design v1.1 §3.2 (Buildable Nodes), khớp Core
 * Combat Gameplay §2.2 (Base family ↔ mount compatibility). [label] khớp cách gọi trong Player UX Flow
 * (vd thông báo "Không hỗ trợ Wall Mount").
 */
enum class MountType(val label: String) {
    GROUND("mặt đất"),
    WALL("Wall Mount"),
    CLIFF("Cliff Mount"),
    RAIL("Rail Mount"),
}
