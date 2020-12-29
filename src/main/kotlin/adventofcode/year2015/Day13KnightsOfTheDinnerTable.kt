package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.common.permutations

class Day13KnightsOfTheDinnerTable(customInput: String? = null) : Puzzle(customInput) {
    override val name = "Knights of the Dinner Table"

    private val happiness = input
        .lines()
        .map {
            val (a, gainOrLose, amount, b) = INPUT_REGEX.find(it)!!.destructured
            Pair(a, b) to amount.toInt() * if (gainOrLose == "lose") -1 else 1
        }
        .toMap()

    private val guests = happiness.keys.map { it.first }.distinct()

    override fun partOne() = guests
        .permutations()
        .map { arrangement -> arrangement.map { Pair(it, arrangement.neighbors(it)) } }
        .map { arrangement -> arrangement.map { guest -> guest.second.sumBy { neighbor -> happiness[guest.first to neighbor]!! } } }
        .map { arrangement -> arrangement.sum() }
        .maxOrNull() ?: 0

    companion object {
        private val INPUT_REGEX = """(\w+) would (gain|lose) (\d+) happiness units by sitting next to (\w+).""".toRegex()

        private fun List<String>.neighbors(guest: String) = when (val position = indexOf(guest)) {
            0 -> listOf(last(), this[1])
            size - 1 -> listOf(this[size - 2], first())
            else -> listOf(this[position - 1], this[position + 1])
        }
    }
}
