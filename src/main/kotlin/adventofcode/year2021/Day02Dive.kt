package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.year2021.Day02Dive.Companion.Direction.DOWN
import adventofcode.year2021.Day02Dive.Companion.Direction.FORWARD
import adventofcode.year2021.Day02Dive.Companion.Direction.UP

class Day02Dive(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Dive!"

    private val commands by lazy { input.lines().map(::Command) }

    override fun partOne() = commands.fold(Position()) { acc, command ->
        when (command.direction) {
            FORWARD -> acc.copy(horizontal = acc.horizontal + command.value)
            DOWN -> acc.copy(depth = acc.depth + command.value)
            UP -> acc.copy(depth = acc.depth - command.value)
        }
    }.multiply()

    override fun partTwo() = commands.fold(Position()) { acc, command ->
        when (command.direction) {
            FORWARD -> acc.copy(horizontal = acc.horizontal + command.value, depth = acc.depth + acc.aim * command.value)
            DOWN -> acc.copy(aim = acc.aim + command.value)
            UP -> acc.copy(aim = acc.aim - command.value)
        }
    }.multiply()

    companion object {
        data class Position(
            val horizontal: Int = 0,
            val depth: Int = 0,
            val aim: Int = 0
        ) {
            fun multiply() = horizontal * depth
        }

        data class Command(
            val direction: Direction,
            val value: Int
        ) {
            constructor(input: String) : this(Direction.fromString(input.split(" ").first())!!, input.split(" ").last().toInt())
        }

        enum class Direction(val direction: String) {
            FORWARD("forward"),
            DOWN("down"),
            UP("up");

            companion object {
                fun fromString(direction: String) = values().associateBy(Direction::direction)[direction]
            }
        }
    }
}
