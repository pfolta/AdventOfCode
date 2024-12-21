package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d
import adventofcode.common.spatial.Point2d
import adventofcode.common.spatial.Point2d.Companion.EAST
import adventofcode.common.spatial.Point2d.Companion.NORTH
import adventofcode.common.spatial.Point2d.Companion.SOUTH
import adventofcode.common.spatial.Point2d.Companion.WEST

class Day16ReindeerMaze(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val maze by lazy { Grid2d(input) }

    override fun partOne() = maze.shortestPath(maze['S'], maze['E'])

    companion object {
        private const val STEP_SCORE = 1
        private const val TURN_SCORE = 1000

        private val clockwiseTurns = mapOf(NORTH to EAST, EAST to SOUTH, SOUTH to WEST, WEST to NORTH)
        private val counterClockwiseTurns = clockwiseTurns.entries.associate { (from, to) -> to to from }

        private fun Grid2d<Char>.shortestPath(
            start: Point2d,
            end: Point2d,
        ): Int {
            val scores = mutableMapOf<Point2d, Int>()
            val queue = ArrayDeque(setOf(Triple(start, EAST, 0)))

            while (queue.isNotEmpty()) {
                val (position, direction, score) = queue.removeFirst()

                if (scores.getOrDefault(position, Integer.MAX_VALUE) > score) {
                    scores[position] = score

                    val clockwiseTurn = clockwiseTurns[direction]!!
                    val counterClockwiseTurn = counterClockwiseTurns[direction]!!

                    val neighbors =
                        setOf(
                            Triple(position + direction, direction, score + STEP_SCORE),
                            Triple(position + clockwiseTurn, clockwiseTurn, score + TURN_SCORE + STEP_SCORE),
                            Triple(position + counterClockwiseTurn, counterClockwiseTurn, score + TURN_SCORE + STEP_SCORE),
                        )
                            .filterNot { (position) -> this[position] == '#' }

                    queue.addAll(neighbors)
                }
            }

            return scores.getValue(end)
        }
    }
}
