package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d
import adventofcode.common.spatial.Point2d
import adventofcode.year2024.Day06GuardGallivant.Companion.Direction.UP

class Day06GuardGallivant(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val grid by lazy { Grid2d(input) }
    private val obstructions by lazy { grid.find('#') }
    private val guard by lazy { grid.find('^').first() to UP }

    override fun partOne() = grid.guardPath(obstructions, guard).size

    override fun partTwo() =
        grid
            .guardPath(obstructions, guard)
            .filterNot { position -> position == guard.first }
            .mapNotNull { position ->
                try {
                    grid.guardPath(obstructions + position, guard)
                    null
                } catch (e: LoopException) {
                    position
                }
            }
            .size

    companion object {
        private enum class Direction(val direction: Point2d) {
            UP(Point2d(0, -1)),
            RIGHT(Point2d(1, 0)),
            DOWN(Point2d(0, 1)),
            LEFT(Point2d(-1, 0)),
            ;

            fun turnRight() =
                when (this) {
                    UP -> RIGHT
                    RIGHT -> DOWN
                    DOWN -> LEFT
                    LEFT -> UP
                }
        }

        private class LoopException(position: Point2d) : Exception("Loop detected at $position")

        private fun Grid2d<Char>.guardPath(
            obstructions: Set<Point2d>,
            guard: Pair<Point2d, Direction>,
        ): Set<Point2d> {
            val seen = mutableSetOf<Pair<Point2d, Direction>>()
            var position = guard

            while (position.first in this) {
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
