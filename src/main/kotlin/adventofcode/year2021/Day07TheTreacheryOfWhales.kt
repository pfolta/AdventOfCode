package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.abs

class Day07TheTreacheryOfWhales(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "The Treachery of Whales"

    private val crabs by lazy { input.split(",").map(String::toInt) }

    override fun partOne() = (crabs.minOrNull()!!..crabs.maxOrNull()!!).minOf { crabs.sumOf { crab -> abs(it - crab) } }

    override fun partTwo() =
        (crabs.minOrNull()!!..crabs.maxOrNull()!!).minOf { crabs.sumOf { crab -> abs(it - crab).sumOfNaturalNumbers() } }

    private fun Int.sumOfNaturalNumbers() = (this * (this + 1)) / 2
}
