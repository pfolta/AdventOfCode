package adventofcode.year2017

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day01InverseCaptcha(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val digits by lazy { input.map(Character::getNumericValue) }

    override fun partOne() = digits
        .filterIndexed { index, digit -> digit == digits[(index + 1) % digits.size] }
        .sum()

    override fun partTwo() = digits
        .filterIndexed { index, digit -> digit == digits[(index + digits.size / 2) % digits.size] }
        .sum()
}
