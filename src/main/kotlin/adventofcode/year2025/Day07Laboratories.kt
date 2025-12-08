package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d

class Day07Laboratories(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): Pair<Long, List<Set<Long>>> {
        val grid = Grid2d(input)
        val start = grid[START].x
        val splittersByRow =
            grid
                .rows()
                .map { row ->
                    row
                        .mapIndexedNotNull { x, char ->
                            when (char) {
                                SPLITTER -> x.toLong()
                                else -> null
                            }
                        }.toSet()
                }

        return Pair(start, splittersByRow)
    }

    override fun partOne(): Int {
        val (start, splittersByRow) = parseInput()

        return splittersByRow
            .fold(Pair(setOf(start), 0)) { (beam, splitCount), splittersInRow ->
                val reachedSplitters = beam intersect splittersInRow
                Pair(beam - reachedSplitters + reachedSplitters.flatMap { x -> setOf(x - 1, x + 1) }, splitCount + reachedSplitters.size)
            }.second
    }

    override fun partTwo(): Long {
        val (start, splittersByRow) = parseInput()
        val beam = mutableMapOf(start to 1L)

        for (splittersInRow in splittersByRow) {
            val reachedSplitters = splittersInRow intersect beam.keys

            for (reachedSplitter in reachedSplitters) {
                val pathCount = beam.getValue(reachedSplitter)

                if (pathCount > 0) {
                    beam[reachedSplitter - 1] = (beam[reachedSplitter - 1] ?: 0) + pathCount
                    beam[reachedSplitter + 1] = (beam[reachedSplitter + 1] ?: 0) + pathCount
                    beam[reachedSplitter] = 0
                }
            }
        }

        return beam.values.sum()
    }

    companion object {
        private const val START = 'S'
        private const val SPLITTER = '^'
    }
}
