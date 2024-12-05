package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day05PrintQueue(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val rules by lazy {
        input.split("\n\n").first().lines().map { line -> line.split("|").map(String::toInt) }
    }

    private val updates by lazy {
        input.split("\n\n").last().lines().map { line -> line.split(",").map(String::toInt) }
    }

    override fun partOne() =
        updates
            .mapNotNull { update ->
                val filteredRules = rules.filter { rule -> update.containsAll(rule) }

                if (filteredRules.containsAll(update.windowed(2))) {
                    update[update.size / 2]
                } else {
                    null
                }
            }
            .sum()
}
