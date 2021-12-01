package adventofcode.year2021

import adventofcode.Puzzle

class Day01SonarSweep(customInput: String? = null) : Puzzle(customInput) {
    private val depths by lazy { input.lines().map(String::toInt) }

    override fun partOne() = depths.zipWithNext().count { it.second > it.first }

    override fun partTwo() = (0 until depths.size - 2)
        .map { start -> depths[start] + depths[start + 1] + depths[start + 2] }
        .zipWithNext()
        .count { it.second > it.first }
}
