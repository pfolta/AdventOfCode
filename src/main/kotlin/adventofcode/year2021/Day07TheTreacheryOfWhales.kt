package adventofcode.year2021

import adventofcode.Puzzle
import kotlin.math.abs

class Day07TheTreacheryOfWhales(customInput: String? = null) : Puzzle(customInput) {
    override val name = "The Treachery of Whales"

    private val crabs by lazy { input.split(",").map(String::toInt) }

    override fun partOne() = (crabs.minOrNull()!!..crabs.maxOrNull()!!).minOf { crabs.sumOf { crab -> abs(it - crab) } }

    override fun partTwo() = (crabs.minOrNull()!!..crabs.maxOrNull()!!).minOf { crabs.sumOf { crab -> (1..abs(it - crab)).sum() } }
}
