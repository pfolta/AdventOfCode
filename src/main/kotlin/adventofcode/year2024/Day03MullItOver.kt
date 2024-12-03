package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day03MullItOver(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = input.sumOfMultiplications()

    override fun partTwo() =
        input
            .split(doRegex)
            .map { doPart -> doPart.split(dontRegex).first() }
            .sumOf { it.sumOfMultiplications() }

    companion object {
        private val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        private val doRegex = """do\(\)""".toRegex()
        private val dontRegex = """don't\(\)""".toRegex()

        private fun String.sumOfMultiplications() =
            mulRegex
                .findAll(this)
                .map { it.destructured }
                .sumOf { (x, y) -> x.toInt() * y.toInt() }
    }
}
