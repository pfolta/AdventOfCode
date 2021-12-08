package adventofcode.year2021

import adventofcode.Puzzle

class Day08SevenSegmentSearch(customInput: String? = null) : Puzzle(customInput) {
    private val outputValues by lazy { input.lines().map { it.split("|").last().trim().split(" ") } }

    override fun partOne() = outputValues.map { entry -> entry.filter { it.length in listOf(2, 3, 4, 7) } }.sumOf(List<String>::size)
}
