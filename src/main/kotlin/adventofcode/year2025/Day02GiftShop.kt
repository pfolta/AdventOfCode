package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day02GiftShop(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): List<Long> =
        input
            .split(",")
            .flatMap { range ->
                val (start, end) = range.split("-").map(String::toLong)
                LongRange(start, end).asSequence()
            }

    override fun partOne() =
        parseInput()
            .filter { id -> id.toString().matches(singleRepeatRegex) }
            .sum()

    override fun partTwo() =
        parseInput()
            .filter { id -> id.toString().matches(multiRepeatRegex) }
            .sum()

    companion object {
        private val singleRepeatRegex = """^(\d+)\1$""".toRegex()
        private val multiRepeatRegex = """^(\d+)\1+$""".toRegex()
    }
}
