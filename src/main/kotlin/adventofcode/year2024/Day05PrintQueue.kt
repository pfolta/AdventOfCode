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
            .filter { update -> update.isSorted(rules) }
            .sumOf { update -> update[update.size / 2] }

    override fun partTwo() =
        updates
            .filterNot { update -> update.isSorted(rules) }
            .map { update -> update.sortedWith { a, b -> if (rules.contains(listOf(a, b))) -1 else 1 } }
            .sumOf { update -> update[update.size / 2] }

    companion object {
        private fun List<Int>.isSorted(rules: List<List<Int>>) = rules.containsAll(windowed(2))
    }
}
