package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day04CampCleanup(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val assignmentPairs by lazy {
        input
            .lines()
            .map {
                ASSIGNMENT_PAIR_REGEX
                    .find(it)!!
                    .destructured
                    .toList()
                    .map(String::toInt)
            }.map { (a, b, c, d) -> a..b to c..d }
    }

    override fun partOne() = assignmentPairs.count { (a, b) -> a in b || b in a }

    override fun partTwo() = assignmentPairs.count { (a, b) -> a overlaps b }

    companion object {
        private val ASSIGNMENT_PAIR_REGEX = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()

        private operator fun IntRange.contains(other: IntRange) = first <= other.first && last >= other.last

        private infix fun IntRange.overlaps(other: IntRange) = first <= other.last && other.first <= last
    }
}
