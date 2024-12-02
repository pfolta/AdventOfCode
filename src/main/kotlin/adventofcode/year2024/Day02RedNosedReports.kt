package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.abs

class Day02RedNosedReports(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Red-Nosed Reports"

    override fun partOne() =
        input
            .lines()
            .map { report -> report.split(" ").mapNotNull(String::toIntOrNull) }
            .count { report -> report.isSafeReport() }

    companion object {
        private fun List<Int>.isSafeReport(): Boolean {
            val change =
                this
                    .zipWithNext()
                    .map { (a, b) -> b - a }

            if (!change.all { it > 0 } && !change.all { it < 0 }) {
                return false
            }

            if (change.any { abs(it) > 3 }) {
                return false
            }

            return true
        }
    }
}
