package adventofcode.year2018

import adventofcode.Puzzle

class Day01ChronalCalibration(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = input.lines().map(String::toInt).sum()
}
