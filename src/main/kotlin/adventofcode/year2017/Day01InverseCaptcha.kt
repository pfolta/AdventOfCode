package adventofcode.year2017

import adventofcode.Puzzle

class Day01InverseCaptcha(customInput: String? = null) : Puzzle(customInput) {
    private val digits = input.map(Character::getNumericValue)

    override fun partOne() = digits
        .filterIndexed { index, digit -> if (index < digits.size - 1) digit == digits[index + 1] else digit == digits.first() }
        .sum()
}
