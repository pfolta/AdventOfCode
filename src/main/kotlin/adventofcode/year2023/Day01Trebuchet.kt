package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day01Trebuchet(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Trebuchet?!"

    override fun partOne() = input
        .lines()
        .map { line -> line.filter { char -> char.isDigit() } }
        .map { line -> String(charArrayOf(line.first(), line.last())) }
        .sumOf { line -> line.toInt() }
}
