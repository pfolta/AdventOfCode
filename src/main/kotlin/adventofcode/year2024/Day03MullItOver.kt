package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day03MullItOver(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() =
        mulRegex
            .findAll(input)
            .map { it.destructured }
            .sumOf { (x, y) -> x.toInt() * y.toInt() }

    companion object {
        private val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
    }
}
