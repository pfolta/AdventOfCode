package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.common.powersets

class Day17NoSuchThingAsTooMuch(customInput: String? = null) : Puzzle(customInput) {
    override val name = "No Such Thing as Too Much"

    private val combinations by lazy { input.lines().map(String::toInt).powersets().filter { it.sum() == 150 } }

    override fun partOne() = combinations.size

    override fun partTwo() = combinations.filter { it.size == combinations.minByOrNull { it.size }!!.size }.size
}
