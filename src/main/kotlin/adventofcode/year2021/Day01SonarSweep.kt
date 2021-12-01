package adventofcode.year2021

import adventofcode.Puzzle

class Day01SonarSweep(customInput: String? = null) : Puzzle(customInput) {
    private val depths by lazy { input.lines().map(String::toInt) }

    override fun partOne() = depths.windowed(2).count { it.last() > it.first() }

    override fun partTwo() = depths.windowed(3).map(List<Int>::sum).windowed(2).count { it.last() > it.first() }
}
