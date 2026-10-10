package com.lastoutpost.core

import com.lastoutpost.core.battlefield.Battlefield
import com.lastoutpost.core.battlefield.LaneEdge
import com.lastoutpost.core.battlefield.LaneNode
import com.lastoutpost.core.battlefield.PathGraph
import com.lastoutpost.core.battlefield.WorldPosition
import com.lastoutpost.core.enemy.EnemySystem
import com.lastoutpost.core.enemy.Movement
import com.lastoutpost.core.enemy.MovementSystem
import com.lastoutpost.core.enemy.MovementType
import com.lastoutpost.core.entity.BaseCore
import com.lastoutpost.core.entity.Health
import com.lastoutpost.core.mission.CoreAssaultProfile
import com.lastoutpost.core.mission.CoreAssaultSystem
import com.lastoutpost.core.mission.MissionObjectiveSystem
import com.lastoutpost.core.sim.BattlePhase
import com.lastoutpost.core.sim.BattleWorld
import com.lastoutpost.core.sim.ResultState
import com.lastoutpost.core.sim.TickSystem
import com.lastoutpost.core.wave.FixedRoster
import com.lastoutpost.core.wave.SpawnGroup
import com.lastoutpost.core.wave.WaveDirector
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MissionObjectiveTest {
    private val profiles = mapOf("ENEMY_CRAWLER" to CoreAssaultProfile(damage = 10, cooldownTicks = 2))

    /** Core nằm tại (10,0); lane A(0,0) → CORE(10,0). */
    private fun field() = Battlefield(
        nodes = emptyList(),
        paths = PathGraph(
            listOf(LaneNode("A", WorldPosition(0.0, 0.0)), LaneNode("CORE", WorldPosition(10.0, 0.0))),
            listOf(LaneEdge("A", "CORE")),
        ),
        laneEntryNodes = mapOf(0 to "A"),
        coreNodeId = "CORE",
    )

    private fun combatWorld(core: BaseCore?, systems: List<TickSystem>) =
        BattleWorld("MISSION_TEST_001", "test", seed = 1L, systems = systems, core = core).apply {
            advanceTo(BattlePhase.LOAD_CONTENT)
            advanceTo(BattlePhase.BUILD_WORLD)
            advanceTo(BattlePhase.PREPARE)
            advanceTo(BattlePhase.COMBAT)
        }

    private fun coreAt(hp: Int, max: Int = 100) = BaseCore(Health(hp, max))

    private fun assaultWorld(field: Battlefield, core: BaseCore?) = combatWorld(core, listOf(CoreAssaultSystem(field, profiles)))

    private fun BattleWorld.enemyAtCore(speciesId: String = "ENEMY_CRAWLER"): Long =
        entities.createEnemy(speciesId, WorldPosition(10.0, 0.0), Movement(MovementType.GROUND, currentNodeId = "CORE"))

    // ---------- CoreAssaultSystem ----------

    @Test
    fun `enemy tai Core danh Core mat dan HP khong chet tuc thi`() {
        val world = assaultWorld(field(), coreAt(100))
        world.enemyAtCore()
        world.step()
        assertEquals(90, world.core!!.health.current) // chỉ -10, không phải 0
        assertTrue(world.eventLog.any { it.type == "core_damaged" })
    }

    @Test
    fun `cooldown duoc tuan thu giua cac don danh Core`() {
        val world = assaultWorld(field(), coreAt(100))
        world.enemyAtCore()
        world.step() // đánh, cooldown=2
        world.step() // 2→1
        world.step() // 1→0
        assertEquals(90, world.core!!.health.current)
        world.step() // đánh lần 2
        assertEquals(80, world.core!!.health.current)
    }

    @Test
    fun `enemy chua toi Core thi khong gay damage`() {
        val world = assaultWorld(field(), coreAt(100))
        world.entities.createEnemy("ENEMY_CRAWLER", WorldPosition(3.0, 0.0), Movement(MovementType.GROUND, currentNodeId = "A"))
        world.step()
        assertEquals(100, world.core!!.health.current)
    }

    @Test
    fun `species khong co profile thi khong gay damage`() {
        val world = assaultWorld(field(), coreAt(100))
        world.enemyAtCore("ENEMY_ACID_BLOATER")
        world.step()
        assertEquals(100, world.core!!.health.current)
    }

    @Test
    fun `enemy bay dung tai corePosition cung danh duoc Core`() {
        val world = assaultWorld(field(), coreAt(100))
        world.entities.createEnemy("ENEMY_CRAWLER", WorldPosition(10.0, 0.0), Movement(MovementType.FLYING))
        world.step()
        assertEquals(90, world.core!!.health.current)
    }

    @Test
    fun `HP Core khong xuong am va khong danh them khi da sap`() {
        val world = assaultWorld(field(), coreAt(5))
        world.enemyAtCore()
        world.enemyAtCore()
        world.step()
        assertEquals(0, world.core!!.health.current)
        assertEquals(1, world.eventLog.count { it.type == "core_damaged" }) // đòn thứ hai bị chặn vì Core đã sập
    }

    @Test
    fun `mission chua cau hinh Core thi khong crash va khong co su kien`() {
        val world = assaultWorld(field(), core = null)
        world.enemyAtCore()
        world.step()
        assertNull(world.core)
        assertTrue(world.eventLog.none { it.type == "core_damaged" })
    }

    // ---------- MissionObjectiveSystem ----------

    private fun directorWith(vararg groups: SpawnGroup) = WaveDirector(FixedRoster(setOf("ENEMY_CRAWLER")), groups.toList())

    @Test
    fun `Core sap thi Defeat va chuyen sang CALCULATE_RESULT`() {
        val world = combatWorld(coreAt(0), listOf(MissionObjectiveSystem()))
        world.step()
        assertEquals(ResultState.DEFEAT, world.resultState)
        assertEquals(BattlePhase.CALCULATE_RESULT, world.phase)
        assertTrue(world.eventLog.any { it.type == "mission_defeat" })
    }

    @Test
    fun `het Final Wave, het enemy, Core con song thi Victory`() {
        val director = directorWith(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1))
        val world = combatWorld(coreAt(50), listOf(director, EnemySystem(), MissionObjectiveSystem(director)))
        world.step() // spawn 1 enemy — vẫn còn enemy trên sân → chưa Victory
        assertEquals(ResultState.NONE, world.resultState)

        world.entities.destroyEnemy(world.entities.enemyIds.first())
        world.step()
        assertEquals(ResultState.VICTORY, world.resultState)
        assertTrue(world.eventLog.any { it.type == "mission_victory" })
    }

    @Test
    fun `chua phat het ke hoach spawn thi chua Victory du san trong`() {
        val director = directorWith(SpawnGroup(atTick = 100, speciesId = "ENEMY_CRAWLER", count = 1))
        val world = combatWorld(coreAt(50), listOf(director, EnemySystem(), MissionObjectiveSystem(director)))
        world.step()
        assertEquals(ResultState.NONE, world.resultState)
        assertEquals(BattlePhase.COMBAT, world.phase)
    }

    @Test
    fun `Defeat uu tien hon Victory khi ca hai cung dung`() {
        val director = directorWith() // kế hoạch rỗng → đã "phát hết"
        val world = combatWorld(coreAt(0), listOf(director, MissionObjectiveSystem(director)))
        world.step()
        assertEquals(ResultState.DEFEAT, world.resultState)
    }

    @Test
    fun `khong co WaveDirector thi khong bao gio tu Victory`() {
        val world = combatWorld(coreAt(50), listOf(MissionObjectiveSystem(waves = null)))
        repeat(5) { world.step() }
        assertEquals(ResultState.NONE, world.resultState)
    }

    @Test
    fun `mission chua cau hinh Core thi khong tu ket thuc tran`() {
        val director = directorWith()
        val world = combatWorld(core = null, systems = listOf(director, MissionObjectiveSystem(director)))
        repeat(3) { world.step() }
        assertEquals(BattlePhase.COMBAT, world.phase)
        assertEquals(ResultState.NONE, world.resultState)
    }

    // ---------- Tích hợp toàn pipeline ----------

    @Test
    fun `tich hop - enemy spawn, di chuyen toi Core, danh Core den khi Defeat`() {
        val f = field()
        val director = directorWith(SpawnGroup(atTick = 0, speciesId = "ENEMY_CRAWLER", count = 1, lane = 0))
        val world = combatWorld(
            coreAt(hp = 20),
            listOf(director, EnemySystem(f), MovementSystem(f), CoreAssaultSystem(f, profiles), MissionObjectiveSystem(director)),
        )
        world.step() // spawn tại A(0,0)
        val enemyId = world.entities.enemyIds.first()
        world.entities.updateEnemy(enemyId) { it.copy(movement = it.movement.copy(speed = 5.0)) }

        var guard = 0
        while (world.phase == BattlePhase.COMBAT && guard++ < 50) world.step()

        assertEquals(ResultState.DEFEAT, world.resultState)
        assertEquals(0, world.core!!.health.current)
        assertEquals(2, world.eventLog.count { it.type == "core_damaged" }) // 20 HP / 10 damage = 2 đòn
        assertTrue(world.eventLog.any { it.type == "enemy_reached_core" })
    }
}
