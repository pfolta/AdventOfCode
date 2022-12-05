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
            .map { (quantity, from, to) -> Triple(quantity.toInt(), from.toInt(), to.toInt()) }
    }

    override fun partOne() = generateSequence(0 to stacks) { (step, stacks) ->
        val (quantity, from, to) = procedure[step]

        val toMove = stacks[from - 1].takeLast(quantity).reversed()

        val newStacks = stacks
            .mapIndexed { index, stack ->
                when (index) {
                    from - 1 -> stack.dropLast(quantity)
                    to - 1 -> stack + toMove
                    else -> stack
                }
            }

        step + 1 to newStacks
    }
        .take(procedure.size + 1)
        .last()
        .second
        .map { stack -> stack.last() }
        .joinToString("")

    companion object {
        private val PROCEDURE_REGEX = """move (\d+) from (\d+) to (\d+)""".toRegex()
    }
}
