package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.common.powersets

class Day17NoSuchThingAsTooMuch(customInput: String? = null) : Puzzle(customInput) {
    override val name = "No Such Thing as Too Much"

    override fun partOne() = input.lines().map(String::toInt).powersets().filter { it.sum() == 150 }.size
}
