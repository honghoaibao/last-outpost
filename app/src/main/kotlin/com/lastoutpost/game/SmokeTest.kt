package com.lastoutpost.game

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.BuildableNode
import com.lastoutpost.core.battlefield.MountType
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.combat.AttackProfile
import com.lastoutpost.core.combat.DamageResolutionSystem
import com.lastoutpost.core.combat.TowerAttackSystem
import com.lastoutpost.core.entity.DamageTag
import com.lastoutpost.core.enemy.EnemySystem
import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.Command
import com.lastoutpost.core.targeting.EnemyTargetingSystem
import com.lastoutpost.core.targeting.TowerTargetingSystem
import com.lastoutpost.core.tower.ReferenceBaseCatalog
import com.lastoutpost.core.tower.TowerSystem
import com.lastoutpost.core.wave.FixedRoster
import com.lastoutpost.core.wave.SpawnGroup
import com.lastoutpost.core.wave.WaveDirector
import java.util.Locale

/**
 * Chạy một trận headless ngắn để chứng minh :core được nối vào app (không render, không gameplay thật).
 * Battlefield ở đây (1 buildable node + 1 lane entry) là demo cục bộ dựng tay, KHÔNG phải map/mission thật
 * (map/mission thật sẽ được map designer author và nạp từ content — TD-17, chưa có content pipeline cho map).
 * Enemy dùng EMP Stalker vì đây là loài duy nhất trong roster demo có preferredTargetClasses chứa TOWER
 * (đủ để thấy targeting hai chiều hoạt động) — không phải lựa chọn thiết kế, chỉ để minh hoạ pipeline.
 */
internal fun runHeadlessSmokeTest(): List<String> {
    val battlefield = Battlefield(
        nodes = listOf(
            BuildableNode("N_DEMO", WorldPosition(4.0, 4.0), MountType.GROUND),
            BuildableNode("ENTRY_LANE_0", WorldPosition(0.0, 4.0), MountType.GROUND),
        ),
        laneEntryNodes = mapOf(0 to "ENTRY_LANE_0"),
    )

    val roster = FixedRoster(setOf("ENEMY_EMP_STALKER"))
    val director = WaveDirector(roster, listOf(SpawnGroup(atTick = 25, speciesId = "ENEMY_EMP_STALKER", count = 3, lane = 0)))
    // Danh mục barrel/module ở đây là demo cục bộ, KHÔNG phải content chính thức (chưa có roster — OQ-DESIGN-02).
    val towers = TowerSystem(battlefield, barrelCatalog = setOf("BARREL_DEMO"), moduleCatalog = setOf("MODULE_DEMO"))
    val enemies = EnemySystem(battlefield)
    val enemyTargeting = EnemyTargetingSystem()
    // range = 10.0 chỉ để demo chạy được — KHÔNG phải số liệu canon (Tower chưa có range thật, OQ-DESIGN-02).
    val towerTargeting = TowerTargetingSystem(range = 10.0)
    // baseDamage/cooldownTicks demo — KHÔNG phải số liệu canon (OQ-DESIGN-02). Enemy chưa có Health thật
    // (OQ-CONTENT-02) nên phát bắn sẽ ghi "damage_no_effect" — đúng hành vi thật ở mission thật hiện nay.
    val attackProfiles = mapOf("BARREL_DEMO" to AttackProfile(baseDamage = 10.0, cooldownTicks = 5, damageTag = DamageTag.KINETIC))
    val attack = TowerAttackSystem(attackProfiles)
    val resolve = DamageResolutionSystem()
    val world = BattleWorld(
        missionId = "MISSION_TUTORIAL_001",
        contentVersion = "smoke",
        seed = 1L,
        systems = listOf(director, enemies, towers, enemyTargeting, towerTargeting, attack, resolve),
    )
    world.advanceTo(BattlePhase.LOAD_CONTENT)
    world.advanceTo(BattlePhase.BUILD_WORLD)
    world.advanceTo(BattlePhase.PREPARE)
    world.advanceTo(BattlePhase.COMBAT)

    world.commands.submit(Command.BuildTower(ReferenceBaseCatalog.TREADED.id, "BARREL_DEMO", "MODULE_DEMO", nodeId = "N_DEMO"))

    val ticks = world.config.ticksPerSecond * 2
    repeat(ticks) { world.step() }

    val spawnedEnemy = world.entities.enemyIds.firstOrNull()?.let { world.entities.enemy(it) }
    val builtTower = world.entities.towerIds.firstOrNull()?.let { world.entities.tower(it) }

    return listOf(
        "phase = ${world.phase}",
        "tick = ${world.tick} (${"%.1f".format(Locale.ROOT, world.elapsedSeconds)} giây mô phỏng)",
        "tower đã đặt = ${world.entities.towerIds.size} (đang nhắm enemy #${builtTower?.targeting?.currentTargetId}, rule=${builtTower?.targeting?.rule})",
        "enemy đã spawn = ${world.entities.enemyIds.size} (tại ${spawnedEnemy?.position}, đang nhắm tower #${spawnedEnemy?.targeting?.currentTargetId})",
        "tower đã bắn = ${world.eventLog.count { it.type == "tower_fired" }} lần " +
            "(chưa có tác dụng vì enemy chưa có Health thật: ${world.eventLog.count { it.type == "damage_no_effect" }} lần — OQ-CONTENT-02)",
        "event = ${world.eventLog.joinToString { it.type }}",
    )
}
