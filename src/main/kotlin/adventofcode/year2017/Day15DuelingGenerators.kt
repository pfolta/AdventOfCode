package adventofcode.year2017

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day15DuelingGenerators(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val generators by lazy { input.lines().map { it.split(" ").last().toLong() }.zipWithNext().first() }

    override fun partOne() = generateSequence(generators) { (a, b) ->
        a * A_FACTOR % DIVISOR to b * B_FACTOR % DIVISOR
    }
        .take(ROUNDS)
        .map { (a, b) -> a.toString(2).padStart(BITS_TO_COMPARE, '0') to b.toString(2).padStart(BITS_TO_COMPARE, '0') }
        .count { (a, b) -> a.takeLast(BITS_TO_COMPARE) == b.takeLast(BITS_TO_COMPARE) }

    companion object {
        private const val ROUNDS = 40_000_000
        private const val BITS_TO_COMPARE = 16

        private const val A_FACTOR = 16807
        private const val B_FACTOR = 48271

        private const val DIVISOR = 2147483647
    }
}
