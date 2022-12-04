package adventofcode.year2022

import adventofcode.Puzzle

class Day04CampCleanup(customInput: String? = null) : Puzzle(customInput) {
    private val assignmentPairs by lazy {
        input
            .lines()
            .map { ASSIGNMENT_PAIR_REGEX.find(it)?.destructured ?: throw IllegalArgumentException("Invalid assignment pair '$it'") }
            .map { (a, b, c, d) -> a.toInt()..b.toInt() to c.toInt()..d.toInt() }
    }

    override fun partOne() = assignmentPairs
        .filter { (first, second) -> first.fullyContains(second) || second.fullyContains(first) }
        .size

    override fun partTwo() = assignmentPairs
        .filter { (first, second) -> first.overlapsWith(second) }
        .size

    companion object {
        private val ASSIGNMENT_PAIR_REGEX = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()

        private fun IntRange.fullyContains(other: IntRange) = contains(other.first) && contains(other.last)

        private fun IntRange.overlapsWith(other: IntRange) =
            contains(other.first) || contains(other.last) || other.contains(first) || other.contains(last)
    }
}
