package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.common.divisors

class Day20InfiniteElvesAndInfiniteHouses(customInput: String? = null) : Puzzle(customInput) {
    override val name = "Infinite Elves and Infinite Houses"

    private val presents by lazy { input.toInt() }

    override fun partOne() = generateSequence(1, Int::inc).first { house -> house.divisors().map { it * 10 }.sum() >= presents }
}
