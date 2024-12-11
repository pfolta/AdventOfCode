package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day11PlutonianPebbles(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val stones by lazy { input.split(" ").map(String::toLong) }

    private val cache = mutableMapOf<Pair<Long, Int>, Long>()

    override fun partOne() = stones.sumOf { stone -> stone.countStones(cache, 25) }

    override fun partTwo() = stones.sumOf { stone -> stone.countStones(cache, 75) }

    companion object {
        private fun Long.countStones(
            cache: MutableMap<Pair<Long, Int>, Long>,
            rounds: Int,
        ): Long {
            if (rounds == 0) {
                return 1
            }

            if (cache.contains(this to rounds)) {
                return cache[this to rounds]!!
            }

            val result =
                when {
                    this == 0L -> 1L.countStones(cache, rounds - 1)
                    hasEvenDigitCount() -> halves().sumOf { half -> half.countStones(cache, rounds - 1) }
                    else -> (this * 2024).countStones(cache, rounds - 1)
                }

            cache[this to rounds] = result
            return result
        }

        private fun Long.hasEvenDigitCount() = toString().length % 2 == 0

        private fun Long.halves() =
            listOf(toString().substring(0, toString().length / 2), toString().substring(toString().length / 2)).map(String::toLong)
    }
}
