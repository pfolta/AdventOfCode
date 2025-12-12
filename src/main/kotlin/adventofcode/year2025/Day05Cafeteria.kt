package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.max

class Day05Cafeteria(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): Pair<List<LongRange>, List<Long>> {
        val (ranges, ids) = input.split("\n\n", limit = 2).map(String::lines)

        return Pair(
            ranges.map { range ->
                val (from, to) = range.split("-", limit = 2).map(String::toLong)
                LongRange(from, to)
            },
            ids.map(String::toLong),
        )
    }

    override fun partOne() =
        parseInput()
            .let { (ranges, ids) -> ids.count { id -> ranges.any { range -> range.contains(id) } } }

    override fun partTwo() =
        parseInput()
            .first
            .sortedBy { range -> range.first }
            .fold(emptyList<LongRange>()) { ranges, range ->
                when {
                    ranges.isEmpty() -> listOf(range)

                    range.first - 1 <= ranges.last().last ->
                        ranges.dropLast(1) + listOf(ranges.last().first..max(ranges.last().last, range.last))

                    else -> ranges + listOf(range)
                }
            }.sumOf { range -> range.last - range.first + 1 }
}
