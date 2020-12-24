package adventofcode.year2020

import adventofcode.Day
import adventofcode.year2020.Action.EAST
import adventofcode.year2020.Action.FORWARD
import adventofcode.year2020.Action.LEFT
import adventofcode.year2020.Action.NORTH
import adventofcode.year2020.Action.RIGHT
import adventofcode.year2020.Action.SOUTH
import adventofcode.year2020.Action.WEST
import kotlin.math.absoluteValue

object Day12RainRisk : Day() {
    private val navigationInstructions = input.lines().map(::NavigationInstruction)

    override fun partOne() = navigationInstructions
        .fold(Triple(NavigationDirection.EAST, 0, 0)) { position, instruction ->
            when (instruction.action) {
                EAST -> position.copy(second = position.second + instruction.value)
                WEST -> position.copy(second = position.second - instruction.value)
                NORTH -> position.copy(third = position.third + instruction.value)
                SOUTH -> position.copy(third = position.third - instruction.value)
                FORWARD -> when (position.first) {
                    NavigationDirection.EAST -> position.copy(second = position.second + instruction.value)
                    NavigationDirection.WEST -> position.copy(second = position.second - instruction.value)
                    NavigationDirection.NORTH -> position.copy(third = position.third + instruction.value)
                    NavigationDirection.SOUTH -> position.copy(third = position.third - instruction.value)
                }
                RIGHT -> position.copy(first = NavigationDirection.fromHeading(position.first.heading + instruction.value)!!)
                LEFT -> position.copy(first = NavigationDirection.fromHeading(position.first.heading - instruction.value)!!)
            }
        }
        .toList()
        .subList(1, 3)
        .sumBy { (it as Int).absoluteValue }

    override fun partTwo() = navigationInstructions
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

                    repeat(value / 90) { val oldwx = wx; wx = wy; wy = -oldwx }

                    acc.copy(first = Pair(wx, wy))
                }
                LEFT -> {
                    var wx = waypoint.first
                    var wy = waypoint.second

                    repeat(value / 90) { val oldwx = wx; wx = -wy; wy = oldwx }

                    acc.copy(first = Pair(wx, wy))
                }
            }
        }
        .second
        .toList()
        .sumBy { it.absoluteValue }
}

private enum class NavigationDirection(val heading: Int) {
    EAST(0),
    SOUTH(90),
    WEST(180),
    NORTH(270);

    companion object {
        fun fromHeading(heading: Int) = values().associateBy(NavigationDirection::heading)[Math.floorMod(heading, 360)]
    }
}

private enum class Action(val action: String) {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W"),
    LEFT("L"),
    RIGHT("R"),
    FORWARD("F");

    companion object {
        fun fromString(action: String) = values().associateBy(Action::action)[action]
    }
}

private data class NavigationInstruction(
    val action: Action,
    val value: Int
) {
    constructor(action: String) : this(Action.fromString(action.substring(0, 1))!!, action.substring(1).toInt())
}
