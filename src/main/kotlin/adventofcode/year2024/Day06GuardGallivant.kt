package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Tuple.plus
import adventofcode.year2024.Day06GuardGallivant.Companion.Direction.UP

class Day06GuardGallivant(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val grid by lazy { input.lines().map(String::toList) }
    private val obstructions by lazy { grid.filter('#') }
    private val guard by lazy { grid.filter('^').first() to UP }

    override fun partOne() =
        generateSequence(guard to setOf(guard.first)) { (previousGuard, previousVisited) ->
            val nextPosition = previousGuard.first + previousGuard.second.direction

            when {
                nextPosition in obstructions -> previousGuard.first to previousGuard.second.turnRight() to previousVisited
                else -> nextPosition to previousGuard.second to previousVisited + previousGuard.first
            }
        }
            .first { (guard, _) -> guard.first.toList().any { it !in grid.indices } }
            .second
            .size

    companion object {
        fun List<List<Char>>.filter(char: Char) =
            mapIndexed { y, row -> row.mapIndexedNotNull { x, c -> if (c == char) x to y else null } }
                .flatten()
                .toSet()

        private enum class Direction(val direction: Pair<Int, Int>) {
            UP(0 to -1),
            RIGHT(1 to 0),
            DOWN(0 to 1),
            LEFT(-1 to 0),
            ;

            fun turnRight() =
                when (this) {
                    UP -> RIGHT
                    RIGHT -> DOWN
                    DOWN -> LEFT
                    LEFT -> UP
                }
        }
    }
}
