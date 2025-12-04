package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d

class Day04PrintingDepartment(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val grid by lazy { Grid2d(input) }

    override fun partOne() =
        grid
            .points
            .filter { position -> grid[position] == ROLL_OF_PAPER }
            .filter { position ->
                val adjacentRollsOfPaper =
                    grid
                        .neighborsOf(position, includeDiagonals = true)
                        .map { neighbor -> grid[neighbor] }
                        .count { neighbor -> neighbor == ROLL_OF_PAPER }

                adjacentRollsOfPaper < MAX_ADJACENT_ROLLS_OF_PAPER
            }.size

    companion object {
        private const val ROLL_OF_PAPER = '@'
        private const val MAX_ADJACENT_ROLLS_OF_PAPER = 4
    }
}
