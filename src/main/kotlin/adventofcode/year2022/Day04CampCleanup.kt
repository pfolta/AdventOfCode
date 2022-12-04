package adventofcode.year2022

import adventofcode.Puzzle

class Day04CampCleanup(customInput: String? = null) : Puzzle(customInput) {
    private val assignmentPairs by lazy {
        input
            .lines()
            .map { assignmentPair ->
                ASSIGNMENT_PAIR_REGEX.find(assignmentPair)?.destructured
                    ?: throw IllegalArgumentException("Invalid assignment pair '$assignmentPair'")
            }.map { (a, b, c, d) -> a.toInt()..b.toInt() to c.toInt()..d.toInt() }
    }

    override fun partOne() = assignmentPairs
        .filter { (first, second) -> first.contains(second) || second.contains(first) }
        .size

    companion object {
        private val ASSIGNMENT_PAIR_REGEX = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()

        private fun IntRange.contains(other: IntRange) = contains(other.first) && contains(other.last)
    }
}
