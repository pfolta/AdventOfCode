package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.common.transpose

class Day05SupplyStacks(customInput: String? = null) : Puzzle(customInput) {
    private val stacks by lazy {
        val stackRows = input
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
            .map {
                PROCEDURE_REGEX.find(it)?.destructured ?: throw IllegalArgumentException("$it is not a valid rearrangement instruction")
            }
            .map { (quantity, from, to) -> Triple(quantity.toInt(), from.toInt() - 1, to.toInt() - 1) }
    }

    private fun rearrangeStacks(moveMultipleCrates: Boolean = false) = generateSequence(0 to stacks) { (step, stacks) ->
        val (quantity, fromIndex, toIndex) = procedure[step]

        val cratesToMove = stacks[fromIndex].takeLast(quantity)

        val newStacks = stacks
            .mapIndexed { index, stack ->
                when (index) {
                    fromIndex -> stack.dropLast(quantity)
                    toIndex -> stack + if (moveMultipleCrates) cratesToMove else cratesToMove.reversed()
                    else -> stack
                }
            }

        step + 1 to newStacks
    }
        .drop(1)
        .take(procedure.size)
        .last()
        .second
        .map { stack -> stack.last() }
        .joinToString("")

    override fun partOne() = rearrangeStacks()

    override fun partTwo() = rearrangeStacks(moveMultipleCrates = true)

    companion object {
        private val STACK_REGEX = """([A-Z])+""".toRegex()
        private val PROCEDURE_REGEX = """move (\d+) from (\d+) to (\d+)""".toRegex()
    }
}
