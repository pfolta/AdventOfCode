package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.year2020.Day12RainRisk.Companion.Action.EAST
import adventofcode.year2020.Day12RainRisk.Companion.Action.FORWARD
import adventofcode.year2020.Day12RainRisk.Companion.Action.LEFT
import adventofcode.year2020.Day12RainRisk.Companion.Action.NORTH
import adventofcode.year2020.Day12RainRisk.Companion.Action.RIGHT
import adventofcode.year2020.Day12RainRisk.Companion.Action.SOUTH
import adventofcode.year2020.Day12RainRisk.Companion.Action.WEST
import kotlin.math.absoluteValue

class Day12RainRisk(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val navigationInstructions by lazy { input.lines().map(::NavigationInstruction) }

    override fun partOne() =
        navigationInstructions
            .fold(NavigationDirection.EAST to Pair(0, 0)) { (direction, position), instruction ->
                when (instruction.action) {
                    EAST -> direction to position.copy(first = position.first + instruction.value)
                    WEST -> direction to position.copy(first = position.first - instruction.value)
                    NORTH -> direction to position.copy(second = position.second + instruction.value)
                    SOUTH -> direction to position.copy(second = position.second - instruction.value)
                    FORWARD ->
                        when (direction) {
                            NavigationDirection.EAST -> direction to position.copy(first = position.first + instruction.value)
                            NavigationDirection.WEST -> direction to position.copy(first = position.first - instruction.value)
                            NavigationDirection.NORTH -> direction to position.copy(second = position.second + instruction.value)
                            NavigationDirection.SOUTH -> direction to position.copy(second = position.second - instruction.value)
                        }
                    RIGHT -> NavigationDirection(direction.heading + instruction.value) to position
                    LEFT -> NavigationDirection(direction.heading - instruction.value) to position
                }
            }
            .second
            .toList()
            .sumOf { it.absoluteValue }

    override fun partTwo() =
        navigationInstructions
            .fold(Pair(Pair(10, 1), Pair(0, 0))) { acc, instruction ->
                val value = instruction.value
                val waypoint = acc.first
                val position = acc.second

                when (instruction.action) {
                    EAST -> acc.copy(first = waypoint.copy(first = waypoint.first + value))
                    WEST -> acc.copy(first = waypoint.copy(first = waypoint.first - value))
                    NORTH -> acc.copy(first = waypoint.copy(second = waypoint.second + value))
                    SOUTH -> acc.copy(first = waypoint.copy(second = waypoint.second - value))
                    FORWARD -> acc.copy(second = Pair(position.first + value * waypoint.first, position.second + value * waypoint.second))
                    RIGHT -> {
                        var wx = waypoint.first
                        var wy = waypoint.second

                        repeat(value / 90) {
                            val oldwx = wx
                            wx = wy
                            wy = -oldwx
                        }

                        acc.copy(first = Pair(wx, wy))
                    }
                    LEFT -> {
                        var wx = waypoint.first
                        var wy = waypoint.second

                        repeat(value / 90) {
                            val oldwx = wx
                            wx = -wy
                            wy = oldwx
                        }

                        acc.copy(first = Pair(wx, wy))
                    }
                }
            }
            .second
            .toList()
            .sumOf { it.absoluteValue }

    companion object {
        private enum class NavigationDirection(val heading: Int) {
            EAST(0),
            SOUTH(90),
            WEST(180),
            NORTH(270),
            ;

            companion object {
                operator fun invoke(heading: Int) = entries.associateBy(NavigationDirection::heading)[Math.floorMod(heading, 360)]!!
            }
        }

        private enum class Action(val action: String) {
            NORTH("N"),
            SOUTH("S"),
            EAST("E"),
            WEST("W"),
            LEFT("L"),
            RIGHT("R"),
            FORWARD("F"),
            ;

            companion object {
                operator fun invoke(action: String) = entries.associateBy(Action::action)[action]!!
            }
        }

        private data class NavigationInstruction(
            val action: Action,
            val value: Int,
        ) {
            constructor(action: String) : this(Action(action.substring(0, 1)), action.substring(1).toInt())
        }
    }
}
