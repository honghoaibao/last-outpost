package com.lastoutpost.core.enemy

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.TickStage
import com.lastoutpost.core.sim.TickSystem

/**
 * Enemy system (phần intake) — tiêu thụ [BattleWorld.spawnQueue] do WaveDirector phát ra ở TickStage.WAVE_DIRECTOR
 * (chạy trước trong cùng tick) và tạo EnemyEntity thật qua [BattleWorld.entities] ở TickStage.SPAWN_DESPAWN.
 *
 * Vị trí spawn lấy từ entry node của lane trong [battlefield] (ADR-0005, System Gameplay §3.1 "Enemy Spawn
 * Points") nếu mission có khai báo; nếu không (mission chưa cấu hình, hoặc `lane = null`) thì fallback về
 * `(0.0, 0.0)` — placeholder kỹ thuật, KHÔNG phải vị trí spawn thật.
 *
 * CHƯA làm ở đây: Movement/Pathfinding theo Path Graph, Target Selection, Attack/Damage, Status Effects —
 * các milestone riêng (Target Acquisition, Projectile/Damage) theo đúng thứ tự tick stage sau này.
 */
class EnemySystem(
    private val battlefield: Battlefield? = null,
    private val catalog: Map<String, ReferenceEnemyCatalog.Entry> = ReferenceEnemyCatalog.bySpeciesId,
) : TickSystem {
    override val stage = TickStage.SPAWN_DESPAWN

    override fun update(world: BattleWorld) {
        while (world.spawnQueue.isNotEmpty()) {
            val intent = world.spawnQueue.removeFirst()
            val entry = catalog[intent.speciesId]
            val position = battlefield?.spawnPositionForLane(intent.lane) ?: WorldPosition(0.0, 0.0)
            repeat(intent.count) {
                val id = world.entities.createEnemy(
                    speciesId = intent.speciesId,
                    position = position,
                    movement = Movement(entry?.movementType ?: MovementType.GROUND),
                    threatProfile = ThreatProfile(entry?.threatCost),
                )
                world.emit("enemy_spawned", "id=$id species=${intent.speciesId}")
            }
        }
    }
}
