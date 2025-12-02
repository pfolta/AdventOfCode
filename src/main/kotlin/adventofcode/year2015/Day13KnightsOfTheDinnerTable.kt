package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Permutations.permutations

class Day13KnightsOfTheDinnerTable(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    override val name = "Knights of the Dinner Table"

    private val happiness by lazy {
        input
            .lines()
            .associate {
                val (a, gainOrLose, amount, b) = INPUT_REGEX.find(it)!!.destructured
                Pair(a, b) to amount.toInt() * if (gainOrLose == "lose") -1 else 1
            }
    }

    private val guests by lazy { input.lines().map { INPUT_REGEX.find(it)!!.destructured.component1() }.toSet() }

    override fun partOne() = guests.findOptimalSeatingArrangement(happiness)

    override fun partTwo() = (guests + "me").findOptimalSeatingArrangement(happiness)

    companion object {
        private val INPUT_REGEX = """(\w+) would (gain|lose) (\d+) happiness units by sitting next to (\w+).""".toRegex()

        private fun List<String>.neighbors(guest: String) =
            when (val position = indexOf(guest)) {
                0 -> listOf(last(), this[1])
                size - 1 -> listOf(this[size - 2], first())
                else -> listOf(this[position - 1], this[position + 1])
            }

        private fun Set<String>.findOptimalSeatingArrangement(happiness: Map<Pair<String, String>, Int>) =
            this
                .permutations()
                .map { arrangement -> arrangement.map { guest -> Pair(guest, arrangement.neighbors(guest)) } }
                .map { it.map { guest -> guest.second.sumOf { neighbor -> happiness[guest.first to neighbor] ?: 0 } } }
                .maxOfOrNull { it.sum() } ?: 0
    }
}
