package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day22MonkeyMarket(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val secretNumbers by lazy { input.lines().map(String::toLong) }

    override fun partOne() = secretNumbers.sumOf { secretNumber -> secretNumber.evolve(2000).last() }

    override fun partTwo() =
        buildMap {
            secretNumbers
                .map { secretNumber ->
                    secretNumber
                        .evolve(2000)
                        .map { number -> number % 10 }
                        .toList()
                }.forEach { sequence ->
                    sequence
                        .windowed(5, 1)
                        .map { slice -> slice.zipWithNext { a, b -> b - a } to slice.last() }
                        .distinctBy { (changes, _) -> changes }
                        .forEach { (key, value) ->
                            this[key] = (this[key] ?: 0L) + value
                        }
                }
        }.values
            .max()

    companion object {
        private fun Long.evolve(count: Int): List<Long> =
            generateSequence(this) { number ->
                number
                    .let { secretNumber -> (secretNumber mix (secretNumber * 64)).prune() }
                    .let { secretNumber -> (secretNumber mix (secretNumber / 32)).prune() }
                    .let { secretNumber -> (secretNumber mix (secretNumber * 2048)).prune() }
            }.drop(1)
                .take(count)
                .toList()

        private infix fun Long.mix(other: Long) = this xor other

        private fun Long.prune(): Long = this % 16777216
    }
}
