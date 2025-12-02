package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.cartesianProduct

class Day09EncodingError(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val numbers by lazy { input.lines().map(String::toLong) }

    override fun partOne() =
        (PREAMBLE_LENGTH until numbers.size)
            .filter { index ->
                val preamble = numbers.subList(index - PREAMBLE_LENGTH, index)
                listOf(preamble, preamble).cartesianProduct().none { it.sum() == numbers[index] }
            }.map(numbers::get)
            .first()

    override fun partTwo(): Long {
        val invalidNumber = partOne()

        return (2..numbers.size)
            .flatMap { size -> (0..numbers.size - size).map { numbers.subList(it, it + size) } }
            .filter { it.sum() == invalidNumber }
            .map { it.minOrNull()!! + it.maxOrNull()!! }
            .first()
    }

    companion object {
        private const val PREAMBLE_LENGTH = 25
    }
}
