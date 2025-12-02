package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Math.divisors

class Day20InfiniteElvesAndInfiniteHouses(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    override val name = "Infinite Elves and Infinite Houses"

    private val presents by lazy { input.toInt() }

    override fun partOne() = generateSequence(1, Int::inc).first { house -> house.divisors().sumOf { it * 10 } >= presents }

    override fun partTwo() =
        generateSequence(1, Int::inc)
            .first { house -> house.divisors().filter { divisor -> house / divisor <= 50 }.sumOf { it * 11 } >= presents }
}
