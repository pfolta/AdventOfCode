package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Math.isEven

class Day11PlutonianPebbles(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val stones by lazy { input.split(" ").map(String::toLong) }

    private val cache = mutableMapOf<Pair<Long, Int>, Long>()

    override fun partOne() = stones.sumOf { stone -> blink(stone, cache, 25) }

    override fun partTwo() = stones.sumOf { stone -> blink(stone, cache, 75) }

    companion object {
        private fun blink(
            stone: Long,
            cache: MutableMap<Pair<Long, Int>, Long>,
            count: Int,
        ): Long =
            when {
                count == 0 -> 1
                stone to count in cache -> cache.getValue(stone to count)
                else -> {
                    val result =
                        when {
                            stone == 0L -> blink(1L, cache, count - 1)
                            stone.hasEvenDigits() -> stone.halves().sumOf { half -> blink(half, cache, count - 1) }
                            else -> blink(stone * 2024, cache, count - 1)
                        }

                    cache[stone to count] = result
                    result
                }
            }

        private fun Long.hasEvenDigits() = toString().length.isEven()

        private fun Long.halves() =
            listOf(toString().substring(0, toString().length / 2), toString().substring(toString().length / 2)).map(String::toLong)
    }
}
