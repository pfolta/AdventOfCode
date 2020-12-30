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

    private val guests = input.lines().map { INPUT_REGEX.find(it)!!.destructured.component1() }.distinct()

    override fun partOne() = guests.findOptimalSeatingArrangement(happiness)

    override fun partTwo() = (guests + "me").findOptimalSeatingArrangement(happiness)

    companion object {
        private val INPUT_REGEX = """(\w+) would (gain|lose) (\d+) happiness units by sitting next to (\w+).""".toRegex()

        private fun List<String>.neighbors(guest: String) = when (val position = indexOf(guest)) {
            0 -> listOf(last(), this[1])
            size - 1 -> listOf(this[size - 2], first())
            else -> listOf(this[position - 1], this[position + 1])
        }

        private fun List<String>.findOptimalSeatingArrangement(happiness: Map<Pair<String, String>, Int>) = this
            .permutations()
            .map { arrangement -> arrangement.map { guest -> Pair(guest, arrangement.neighbors(guest)) } }
            .map { it.map { guest -> guest.second.sumBy { neighbor -> happiness[guest.first to neighbor] ?: 0 } } }
            .map { it.sum() }
            .maxOrNull() ?: 0
    }
}
