package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day02RedNosedReports(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    override val name = "Red-Nosed Reports"

    private fun parseInput() = input.lines().map { report -> report.split(" ").mapNotNull(String::toIntOrNull) }

    override fun partOne() = parseInput().count { report -> report.isSafeReport() }

    override fun partTwo() = parseInput().count { report -> report.isSafeReportDampened() }

    companion object {
        private fun List<Int>.isSafeReport(): Boolean {
            val deltas = zipWithNext().map { (a, b) -> b - a }
            return deltas.all { delta -> delta in -3..-1 } || deltas.all { delta -> delta in 1..3 }
        }

        private fun List<Int>.isSafeReportDampened() =
            indices.any { i ->
                filterIndexed { j, _ -> i != j }.isSafeReport()
            }
    }
}
