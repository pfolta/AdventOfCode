package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.abs

class Day02RedNosedReports(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Red-Nosed Reports"

    private val reports by lazy {
        input.lines().map { report -> report.split(" ").mapNotNull(String::toIntOrNull) }
    }

    override fun partOne() = reports.count { report -> report.isSafeReport() }

    override fun partTwo() = reports.count { report -> report.isAlmostSafeReport() }

    companion object {
        private fun List<Int>.isSafeReport() = (isStrictlyIncreasing() || isStrictlyDecreasing()) && differsByAtLeast1AndAtMost3()

        private fun List<Int>.isAlmostSafeReport(): Boolean {
            for (i in -1 until size) {
                if (filterIndexed { j, _ -> i != j }.isSafeReport()) {
                    return true
                }
            }

            return false
        }

        private fun List<Int>.isStrictlyIncreasing() = zipWithNext().all { (a, b) -> a < b }

        private fun List<Int>.isStrictlyDecreasing() = zipWithNext().all { (a, b) -> a > b }

        private fun List<Int>.differsByAtLeast1AndAtMost3() = zipWithNext().all { (a, b) -> abs(a - b) in 1..3 }
    }
}
