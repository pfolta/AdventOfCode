package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Tuple.plus
import adventofcode.year2024.Day06GuardGallivant.Companion.Direction.UP

class Day06GuardGallivant(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val grid by lazy { input.lines().map(String::toList) }
    private val obstructions by lazy { grid.filter('#') }
    private val guard by lazy { grid.filter('^').first() to UP }

    override fun partOne() = guardPath(grid.size, obstructions, guard).size

    override fun partTwo() =
        guardPath(grid.size, obstructions, guard)
            .filterNot { position -> position == guard.first }
            .mapNotNull { position ->
                try {
                    guardPath(grid.size, obstructions + position, guard)
                    null
                } catch (e: LoopException) {
                    position
                }
            }
            .size

    companion object {
        private fun List<List<Char>>.filter(char: Char) =
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

        private class LoopException(position: Pair<Int, Int>) : Exception("Loop detected at $position")

        private fun guardPath(
            gridSize: Int,
            obstructions: Set<Pair<Int, Int>>,
            guard: Pair<Pair<Int, Int>, Direction>,
        ): Set<Pair<Int, Int>> {
            val seen = mutableSetOf<Pair<Pair<Int, Int>, Direction>>()
            var position = guard

            while (position.first.toList().all { it in 0 until gridSize }) {
                if (seen.contains(position)) {
                    throw LoopException(position.first)
                }

                seen.add(position)

                val nextPosition = position.first + position.second.direction

                position =
                    when {
                        nextPosition in obstructions -> position.first to position.second.turnRight()
                        else -> nextPosition to position.second
                    }
            }

            return seen.map { it.first }.toSet()
        }
    }
}
