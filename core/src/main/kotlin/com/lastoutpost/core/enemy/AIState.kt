package com.lastoutpost.core.enemy

/**
 * State machine của AI enemy — AI & Simulation Design §II (danh sách state mở rộng: Spawn, Assess, Move,
 * Search Target, Engage, Attack, Cast Ability, Reposition, Retreat, Burrow, Unburrow, Stagger, Death).
 */
enum class AIState {
    SPAWN, ASSESS, MOVE, SEARCH_TARGET, ENGAGE, ATTACK, CAST_ABILITY,
    REPOSITION, RETREAT, BURROW, UNBURROW, STAGGER, DEATH,
}
