package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.common.cartesianProduct
import adventofcode.year2015.Day06ProbablyAFireHazard.Companion.Action.TOGGLE
import adventofcode.year2015.Day06ProbablyAFireHazard.Companion.Action.TURN_OFF
import adventofcode.year2015.Day06ProbablyAFireHazard.Companion.Action.TURN_ON
import kotlin.math.max

class Day06ProbablyAFireHazard(customInput: String? = null) : Puzzle(customInput) {
    override val name = "Probably a Fire Hazard"

    private val instructions by lazy {
        input
            .lines()
            .map {
                val (action, left, top, right, bottom) = INPUT_REGEX.find(it)!!.destructured
                Instruction(Action.fromString(action)!!, left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
            }
    }

    override fun partOne() = instructions.fold(mutableMapOf<Pair<Int, Int>, Boolean>()) { lights, instruction ->
        when (instruction.action) {
            TURN_ON -> instruction.lights.forEach { light -> lights[light] = true }
            TURN_OFF -> instruction.lights.forEach { light -> lights[light] = false }
            TOGGLE -> instruction.lights.forEach { light -> lights[light] = !lights.getOrDefault(light, false) }
        }

        lights
    }.count { (_, state) -> state }

    override fun partTwo() = instructions.fold(mutableMapOf<Pair<Int, Int>, Int>()) { lights, instruction ->
        when (instruction.action) {
            TURN_ON -> instruction.lights.forEach { light -> lights[light] = lights.getOrDefault(light, 0) + 1 }
            TURN_OFF -> instruction.lights.forEach { light -> lights[light] = max(0, lights.getOrDefault(light, 0) - 1) }
            TOGGLE -> instruction.lights.forEach { light -> lights[light] = lights.getOrDefault(light, 0) + 2 }
        }

        lights
    }.values.sum()

    companion object {
        private val INPUT_REGEX = """(turn on|turn off|toggle) (\d+),(\d+) through (\d+),(\d+)""".toRegex()

        data class Instruction(
            val action: Action,
            val left: Int,
            val top: Int,
            val right: Int,
            val bottom: Int
        ) {
            val lights by lazy { listOf((left..right).toList(), (top..bottom).toList()).cartesianProduct().map { it.first() to it.last() } }
        }

        enum class Action(val action: String) {
            TURN_ON("turn on"),
            TURN_OFF("turn off"),
            TOGGLE("toggle");

            companion object {
                fun fromString(action: String) = values().associateBy(Action::action)[action]
            }
        }
    }
}
