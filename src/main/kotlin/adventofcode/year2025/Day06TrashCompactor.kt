package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product
import adventofcode.common.spatial.Grid2d

class Day06TrashCompactor(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val operations by lazy {
        input
            .lines()
            .last()
            .trim()
            .split(whitespaceRegex)
            .map(String::single)
    }

    private fun solveProblem(
        operation: Char,
        numbers: List<Long>,
    ) = when (operation) {
        '+' -> numbers.sum()
        else -> numbers.product()
    }

    override fun partOne() =
        Grid2d(input.lines().dropLast(1).map { line -> line.trim().split(whitespaceRegex).map(String::toLong) })
            .columns()
            .zip(operations)
            .sumOf { (numbers, operation) -> solveProblem(operation, numbers) }

    override fun partTwo() =
        Grid2d(input.lines().dropLast(1).joinToString("\n"))
            .columns()
            .map { column -> column.joinToString("").replace(whitespaceRegex, "").toLongOrNull() }
            .fold(emptyList<List<Long>>()) { numbers, maybeNumber ->
                when {
                    maybeNumber == null -> numbers + listOf(emptyList())
                    numbers.isEmpty() -> listOf(listOf(maybeNumber))
                    else -> numbers.dropLast(1) + listOf((numbers.last() + listOf(maybeNumber)))
                }
            }.zip(operations)
            .sumOf { (numbers, operation) -> solveProblem(operation, numbers) }

    companion object {
        private val whitespaceRegex = """\s+""".toRegex()
    }
}
