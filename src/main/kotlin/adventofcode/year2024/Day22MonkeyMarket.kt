package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day22MonkeyMarket(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val secretNumbers by lazy { input.lines().map(String::toLong) }

    override fun partOne() =
        generateSequence(secretNumbers) { numbers -> numbers.map { secretNumber -> secretNumber.next() } }
            .drop(1)
            .take(2000)
            .last()
            .sum()

    companion object {
        private fun Long.next(): Long {
            val step1 = (this mix (this * 64)).prune()
            val step2 = (step1 mix (step1 / 32)).prune()
            val step3 = (step2 mix (step2 * 2048)).prune()

            return step3
        }

        private infix fun Long.mix(other: Long) = this xor other

        private fun Long.prune(): Long = this % 16777216
    }
}
