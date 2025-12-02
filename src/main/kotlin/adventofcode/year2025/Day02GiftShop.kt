package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Math.isEven

class Day02GiftShop(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private fun parseInput(): List<LongRange> =
        input
            .split(",")
            .map { range ->
                val (start, end) = range.split("-").map(String::toLong)
                LongRange(start, end)
            }

    override fun partOne() =
        parseInput()
            .flatMap { range -> range.map(Long::toString) }
            .filter { id -> id.length.isEven() && id.take(id.length / 2) == id.substring(id.length / 2) }
            .sumOf(String::toLong)
}
