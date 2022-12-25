package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product

class Day02IWasToldThereWouldBeNoMath(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val dimensions by lazy { input.lines().map { it.split("x").map(String::toInt) } }

    override fun partOne() = dimensions
        .sumOf {
            val wrapping = 2 * (it[0] * it[1] + it[1] * it[2] + it[0] * it[2])
            val slack = it.sorted().subList(0, 2).product()

            wrapping + slack
        }

    override fun partTwo() = dimensions
        .sumOf {
            val ribbon = it.sorted().subList(0, 2).fold(0) { total, side -> total + 2 * side }
            val bow = it[0] * it[1] * it[2]

            ribbon + bow
        }
}
