package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.powersets

class Day17NoSuchThingAsTooMuch(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "No Such Thing as Too Much"

    private val combinations by lazy { input.lines().map(String::toInt).powersets().filter { it.sum() == 150 } }

    override fun partOne() = combinations.size

    override fun partTwo() = combinations.filter { it.size == combinations.minBy { it.size }.size }.size
}
