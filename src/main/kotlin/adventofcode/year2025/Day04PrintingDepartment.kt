package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d
import adventofcode.common.spatial.Point2d

class Day04PrintingDepartment(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): Grid2d<Char> = Grid2d(input)

    private fun Grid2d<Char>.accessibleRollsOfPaper(removedRolls: Set<Point2d> = emptySet()) =
        points
            .filter { position -> this[position] == ROLL_OF_PAPER }
            .filterNot { position -> position in removedRolls }
            .filter { position ->
                val adjacentRollsOfPaper =
                    neighborsOf(position, includeDiagonals = true)
                        .filterNot { neighbor -> neighbor in removedRolls }
                        .map { neighbor -> this[neighbor] }
                        .count { neighbor -> neighbor == ROLL_OF_PAPER }

                adjacentRollsOfPaper < MAX_ADJACENT_ROLLS_OF_PAPER
            }

    override fun partOne() = parseInput().accessibleRollsOfPaper().size

    override fun partTwo() =
        parseInput()
            .let { grid ->
                generateSequence(emptySet<Point2d>()) { removedRolls ->
                    val rollsToRemove = grid.accessibleRollsOfPaper(removedRolls)
                    (removedRolls + rollsToRemove).takeIf { rollsToRemove.isNotEmpty() }
                }
            }.last()
            .size

    companion object {
        private const val ROLL_OF_PAPER = '@'
        private const val MAX_ADJACENT_ROLLS_OF_PAPER = 4
    }
}
