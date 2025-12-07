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
        val splitters =
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

        return splitters
            .fold(Pair(setOf(start), 0)) { (beam, splitCount), rowSplitters ->
                val reachedSplitters = beam intersect rowSplitters
                Pair(beam - reachedSplitters + reachedSplitters.flatMap { x -> listOf(x - 1, x + 1) }, splitCount + reachedSplitters.size)
            }.second
    }

    companion object {
        private const val START = 'S'
        private const val SPLITTER = '^'
    }
}
