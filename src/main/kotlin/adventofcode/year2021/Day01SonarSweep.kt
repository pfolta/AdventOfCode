package adventofcode.year2021

import adventofcode.Puzzle

class Day01SonarSweep(customInput: String? = null) : Puzzle(customInput) {
    private val depths by lazy { input.lines().map(String::toInt) }

    override fun partOne() = depths.zipWithNext().count { it.second > it.first }
}
