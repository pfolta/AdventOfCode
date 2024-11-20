package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.cartesianProduct
import adventofcode.common.product

class Day01ReportRepair(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val expenses by lazy { input.lines().map(String::toInt) }

    override fun partOne() =
        listOf(expenses, expenses)
            .cartesianProduct()
            .first { it.sum() == 2020 }
            .product()

    override fun partTwo() =
        listOf(expenses, expenses, expenses)
            .cartesianProduct()
            .first { it.sum() == 2020 }
            .product()
}
