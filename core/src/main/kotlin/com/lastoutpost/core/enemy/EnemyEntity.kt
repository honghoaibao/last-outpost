package com.lastoutpost.core.enemy

import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.entity.DamageTag
import com.lastoutpost.core.entity.Health

/**
 * Kháng theo [DamageTag] (Combat Resolution §6.4) — CHƯA có số liệu canon (OQ-CONTENT-02); `null` cho tới
 * khi có. Quy ước giá trị (CHƯA xác nhận — tài liệu không nêu công thức chính xác, xem
 * `com.lastoutpost.core.combat.DamageFormula`): hệ số nhân trực tiếp lên damage của tag đó — 0.5 = kháng
 * 50%, 1.5 = yếu (dễ tổn thương) 50%, thiếu tag trong map = trung tính (Target Interaction = 1.0).
 */
data class Resistances(val values: Map<DamageTag, Double>)

/**
 * Status effect đang áp dụng. Hệ thống Status Effects thật (TickStage.STATUS_EFFECTS) CHƯA triển khai —
 * danh sách luôn rỗng cho tới đó, KHÔNG tự mô phỏng hiệu ứng giả.
 */
data class StatusEffects(val activeIds: List<String> = emptyList())

/**
 * [speed] — OPEN, chưa có số liệu canon (OQ-CONTENT-02), phải truyền từ ngoài để enemy di chuyển
 * ([com.lastoutpost.core.enemy.MovementSystem]). [currentNodeId] — node hiện tại trên Path Graph (chỉ có
 * nghĩa với GROUND, gắn khi spawn qua entry node của lane — `null` nếu enemy không spawn qua Battlefield).
 */
data class Movement(val type: MovementType, val speed: Double? = null, val currentNodeId: String? = null)

/**
 * Mục tiêu hiện tại của enemy. [com.lastoutpost.core.targeting.EnemyTargetingSystem] cập nhật field này —
 * hiện chỉ model 2/5 thành phần của công thức AI Sim §III (Objective qua lọc class ưu tiên + Distance thật);
 * Vulnerability/Threat/SpecialInterest/PathCost CHƯA có dữ liệu nguồn để model.
 */
data class Targeting(val currentTargetId: Long? = null)

/** Hành vi hiện tại trong AI state machine (AI Sim §I mô hình 4 lớp: Intent/Navigation/Action/Reaction; §II state machine). */
data class Behavior(val archetypeId: String, val state: AIState = AIState.SPAWN)

/**
 * Threat Budget cost — AI & Simulation Design §VII, bảng "Threat Cost" (tác giả thiết kế ghi rõ đây là
 * baseline/prototype để balance, không phải giá trị cuối — nhưng LÀ số có nguồn, không phải tự đặt).
 * `null` với species chưa có trong bảng gốc (vd Boss — dùng Boss AI Framework riêng, không qua Threat Budget thường).
 */
data class ThreatProfile(val threatCost: Int? = null)

/**
 * Trạng thái cooldown tấn công của enemy — mở rộng so với danh sách thành phần Tech Architecture §V (không có
 * Attack riêng cho enemy) vì [com.lastoutpost.core.mission.CoreAssaultSystem] cần đếm lùi giữa các đòn đánh Core.
 */
data class EnemyAttack(val cooldownRemainingTicks: Int = 0)

/**
 * EnemyEntity — Tech Architecture §V (Transform, Health, Movement, Resistances, Status, Targeting, Behavior,
 * ThreatProfile). [position] là vị trí thế giới hiện tại (ADR-0005); di chuyển thật đi theo Path Graph/lane
 * (`com.lastoutpost.core.battlefield.PathGraph`), không phải nội suy tự do theo toạ độ. [health]/[resistances]
 * là `null` cho tới khi content có số liệu canon (OQ-CONTENT-02).
 *
 * [isElite] — Content Specification nói tới "elite ratio"/"elite threat" như một MODIFIER khi spawn (không
 * phải thuộc tính cố định của species), nhưng không có cơ chế elite-spawn thật nào được triển khai
 * (WaveDirector chưa hỗ trợ) — luôn `false` cho tới khi có. Dùng cho `TargetFilter.BOSS_ELITE`.
 *
 * [armor] — "Effective Armor" trong Damage Model (§6.3: `Damage After Armor = max(1, Raw − Effective Armor)`)
 * CHƯA có số liệu canon nào (OQ-CONTENT-02) — `null` = coi như 0 (không giảm damage) khi tính damage.
 */
data class EnemyEntity(
    val id: Long,
    val speciesId: String,
    val position: WorldPosition,
    val movement: Movement,
    val health: Health? = null,
    val resistances: Resistances? = null,
    val armor: Double? = null,
    val status: StatusEffects = StatusEffects(),
    val targeting: Targeting = Targeting(),
    val behavior: Behavior,
    val threatProfile: ThreatProfile = ThreatProfile(),
    val isElite: Boolean = false,
    val attack: EnemyAttack = EnemyAttack(),
)
