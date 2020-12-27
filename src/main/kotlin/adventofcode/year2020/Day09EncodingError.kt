package adventofcode.year2020

import adventofcode.Puzzle

class Day09EncodingError(customInput: String? = null) : Puzzle(customInput) {
    private val numbers = input.lines().map(String::toLong)

    override fun partOne() = (PREAMBLE_LENGTH until numbers.size)
        .filter { index ->
            val preamble = numbers.subList(index - PREAMBLE_LENGTH, index)

            preamble
                .flatMap { fst -> preamble.map { snd -> listOf(fst, snd) } }
                .none { it.sum() == numbers[index] }
        }
        .map(numbers::get)
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
        const val PREAMBLE_LENGTH = 25
    }
}
