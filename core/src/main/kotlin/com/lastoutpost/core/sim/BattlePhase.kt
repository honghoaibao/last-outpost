package com.lastoutpost.core.sim

/**
 * Vòng đời một trận — Tech Architecture §III:
 * Boot → Load Content → Build World → Prepare → Combat → Calculate Result → Commit Result → Unload.
 */
enum class BattlePhase {
    BOOT,
    LOAD_CONTENT,
    BUILD_WORLD,
    PREPARE,
    COMBAT,
    CALCULATE_RESULT,
    COMMIT_RESULT,
    UNLOAD;

    /** Phase kế tiếp hợp lệ, hoặc null nếu đã ở cuối vòng đời. */
    fun next(): BattlePhase? = entries.getOrNull(ordinal + 1)
}

enum class ResultState { NONE, VICTORY, DEFEAT }
