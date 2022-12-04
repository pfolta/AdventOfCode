package adventofcode.year2022

import adventofcode.Puzzle

class Day04CampCleanup(customInput: String? = null) : Puzzle(customInput) {
    private val assignmentPairs by lazy {
        input
            .lines()
            .map { ASSIGNMENT_PAIR_REGEX.find(it)?.destructured ?: throw IllegalArgumentException("'$it' is not a valid assignment pair") }
            .map { (a, b, c, d) -> a.toInt()..b.toInt() to c.toInt()..d.toInt() }
    }

    override fun partOne() = assignmentPairs
        .filter { (first, second) -> first.intersect(second).size in setOf(first.count(), second.count()) }
        .size

    override fun partTwo() = assignmentPairs
        .filter { (first, second) -> first.intersect(second).isNotEmpty() }
        .size

    companion object {
        private val ASSIGNMENT_PAIR_REGEX = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()
    }
}
