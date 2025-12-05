package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day05Cafeteria(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): Pair<List<LongRange>, List<Long>> {
        val (ranges, ids) = input.split("\n\n").map(String::lines)

        return Pair(
            ranges.map { range ->
                val (start, end) = range.split("-").map(String::toLong)
                LongRange(start, end)
            },
            ids.map(String::toLong),
        )
    }

    override fun partOne() =
        parseInput()
            .let { (ranges, ids) -> ids.count { id -> ranges.any { range -> range.contains(id) } } }
}
