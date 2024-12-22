package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day22MonkeyMarket(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val secretNumbers by lazy { input.lines().map(String::toLong) }

    override fun partOne() = secretNumbers.sumOf { secretNumber -> secretNumber.evolve(2000).last() }

    override fun partTwo() =
        buildMap {
            secretNumbers.forEach { secretNumber ->
                val secrets = secretNumber.evolve(2000)
                val changes = secrets.zipWithNext().map { (a, b) -> a % 10 - b % 10 }
                val seen = mutableSetOf<String>()

                (0..secrets.lastIndex - 4).forEach { i ->
                    val seq = changes.slice(i..i + 3).joinToString()

                    if (seq !in seen) {
                        compute(seq) { _, current -> secrets[i + 4] % 10 + (current ?: 0L) }
                        seen += seq
                    }
                }
            }
        }
            .values
            .max()

    companion object {
        private fun Long.evolve(count: Int): List<Long> =
            generateSequence(this) { number ->
                number
                    .let { secretNumber -> (secretNumber mix (secretNumber * 64)).prune() }
                    .let { secretNumber -> (secretNumber mix (secretNumber / 32)).prune() }
                    .let { secretNumber -> (secretNumber mix (secretNumber * 2048)).prune() }
            }
                .drop(1)
                .take(count)
                .toList()

        private infix fun Long.mix(other: Long) = this xor other

        private fun Long.prune(): Long = this % 16777216
    }
}
