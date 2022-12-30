package adventofcode.year2017

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day15DuelingGenerators(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val generators by lazy { input.lines().map { it.split(" ").last().toLong() }.zipWithNext().first() }

    override fun partOne() = generateSequence(generators) { (a, b) -> a.next(A_FACTOR) to b.next(B_FACTOR) }
        .take(ROUNDS_PART_1)
        .count { (a, b) -> a.toBinary().takeLast(BITS_TO_COMPARE) == b.toBinary().takeLast(BITS_TO_COMPARE) }

    override fun partTwo() = generateSequence(generators) { (a, b) -> a.next(A_FACTOR, A_MULTIPLE_OF) to b.next(B_FACTOR, B_MULTIPLE_OF) }
        .take(ROUNDS_PART_2)
        .count { (a, b) -> a.toBinary().takeLast(BITS_TO_COMPARE) == b.toBinary().takeLast(BITS_TO_COMPARE) }

    companion object {
        private const val BITS_TO_COMPARE = 16

        private const val ROUNDS_PART_1 = 40_000_000
        private const val ROUNDS_PART_2 = 5_000_000

        private const val A_FACTOR = 16807
        private const val B_FACTOR = 48271

        private const val DIVISOR = 2147483647

        private const val A_MULTIPLE_OF = 4
        private const val B_MULTIPLE_OF = 8

        private fun Long.next(factor: Int) = this * factor % DIVISOR

        private fun Long.next(factor: Int, multipleOf: Int): Long = this
            .next(factor)
            .let { next -> if (next % multipleOf == 0L) next else next.next(factor, multipleOf) }

        private fun Long.toBinary() = this.toString(2).padStart(BITS_TO_COMPARE, '0')
    }
}
