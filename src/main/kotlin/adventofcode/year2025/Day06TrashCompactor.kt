package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product
import adventofcode.common.spatial.Grid2d

class Day06TrashCompactor(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput() = Grid2d(input.lines().map { line -> line.replace("""\s+""".toRegex(), " ").trim().split(" ") })

    override fun partOne() =
        parseInput()
            .columns()
            .sumOf { problem ->
                val operation = problem.last().first()
                val numbers = problem.dropLast(1).map(String::toLong)

                when (operation) {
                    '+' -> numbers.sum()
                    '*' -> numbers.product()
                    else -> throw IllegalArgumentException("$operation is not a valid operation")
                }
            }
}
