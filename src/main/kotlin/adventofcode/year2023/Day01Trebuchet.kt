package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day01Trebuchet(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Trebuchet?!"

    override fun partOne() =
        input
            .lines()
            .sumOfFirstAndLastDigits()

    override fun partTwo() =
        input
            .lines()
            .map { line -> digitMap.entries.fold(line) { acc, (word, digit) -> acc.replace(word, "$word$digit$word") } }
            .sumOfFirstAndLastDigits()

    companion object {
        private val digitMap =
            mapOf(
                "one" to 1,
                "two" to 2,
                "three" to 3,
                "four" to 4,
                "five" to 5,
                "six" to 6,
                "seven" to 7,
                "eight" to 8,
                "nine" to 9,
            )

        private fun List<String>.sumOfFirstAndLastDigits() =
            map { line -> line.filter(Char::isDigit) }
                .map { line -> String(charArrayOf(line.first(), line.last())) }
                .sumOf(String::toInt)
    }
}
