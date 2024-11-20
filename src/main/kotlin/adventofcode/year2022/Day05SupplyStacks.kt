package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.transpose

class Day05SupplyStacks(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val stacks by lazy {
        val stackRows =
            input
                .split("\n\n")
                .first()
                .lines()
                .dropLast(1)
                .map { row -> row.filterIndexed { index, _ -> index % 4 == 1 }.toList() }
                .map { row -> row.map { if (it == ' ') null else it } }

        val stackCount = stackRows.last().size

        stackRows
            .map { row -> row + (1..stackCount - row.size).map { null } }
            .transpose()
            .map { stack -> stack.filterNotNull() }
    }

    private val procedure by lazy {
        input
            .split("\n\n")
            .last()
            .lines()
            .map { PROCEDURE_REGEX.find(it)!!.destructured }
            .map { (quantity, from, to) -> Triple(quantity.toInt(), from.toInt() - 1, to.toInt() - 1) }
    }

    private fun rearrangeStacks(moveMultipleCrates: Boolean = false) =
        procedure.fold(stacks) { stacks, (quantity, fromIndex, toIndex) ->
            val cratesToMove = stacks[fromIndex].takeLast(quantity)

            stacks.mapIndexed { index, stack ->
                when (index) {
                    fromIndex -> stack.dropLast(quantity)
                    toIndex -> stack + if (moveMultipleCrates) cratesToMove else cratesToMove.reversed()
                    else -> stack
                }
            }
        }
            .map { stack -> stack.last() }
            .joinToString("")

    override fun partOne() = rearrangeStacks()

    override fun partTwo() = rearrangeStacks(moveMultipleCrates = true)

    companion object {
        private val PROCEDURE_REGEX = """move (\d+) from (\d+) to (\d+)""".toRegex()
    }
}
