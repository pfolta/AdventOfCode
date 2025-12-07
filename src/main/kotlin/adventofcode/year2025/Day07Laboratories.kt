package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d

class Day07Laboratories(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput() = Grid2d(input)

    override fun partOne(): Int {
        val grid = parseInput()
        val start = grid[START].x.toInt()
        val splittersByRow =
            grid
                .rows()
                .map { row ->
                    row
                        .mapIndexedNotNull { x, char ->
                            when (char) {
                                SPLITTER -> x
                                else -> null
                            }
                        }.toSet()
                }

        return splittersByRow
            .fold(Pair(setOf(start), 0)) { (beam, splitCount), splittersInRow ->
                val reachedSplitters = beam intersect splittersInRow
                Pair(beam - reachedSplitters + reachedSplitters.flatMap { x -> setOf(x - 1, x + 1) }, splitCount + reachedSplitters.size)
            }.second
    }

    companion object {
        private const val START = 'S'
        private const val SPLITTER = '^'
    }
}
