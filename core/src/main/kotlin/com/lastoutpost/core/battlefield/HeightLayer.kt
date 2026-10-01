package com.lastoutpost.core.battlefield

/**
 * Lớp độ cao — Visual Identity & Art Direction §"Height Layers":
 * Z0 = mặt đất/trap/terrain/weather; Z1 = tường/tower/commander/enemy mặt đất;
 * Z2 = wall-mounted/ceiling-mounted/cable/enemy bay/upper FX.
 * Khớp ví dụ Buildable Node ở System & Gameplay Design §3.2 (`height = Z1`).
 */
enum class HeightLayer(val z: Int) {
    GROUND(0),
    STRUCTURE(1),
    AERIAL(2),
}
