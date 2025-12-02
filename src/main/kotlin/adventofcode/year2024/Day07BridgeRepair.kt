package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day07BridgeRepair(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val equations by lazy {
        input
            .lines()
            .map { equation ->
                val (result, numbers) = equation.split(": ")
                result.toLong() to numbers.split(" ").map(String::toLong)
            }
    }

    override fun partOne() = equations.calibrationResult(setOf(Long::plus, Long::times))

    override fun partTwo() = equations.calibrationResult(setOf(Long::plus, Long::times, { a, b -> "$a$b".toLong() }))

    companion object {
        private fun List<Pair<Long, List<Long>>>.calibrationResult(operators: Set<(Long, Long) -> Long>) =
            filter { (result, numbers) ->
                val results =
                    numbers
                        .drop(1)
                        .fold(setOf(numbers.first())) { candidates, number ->
                            candidates.flatMap { candidate -> operators.map { operator -> operator(candidate, number) } }.toSet()
                        }

                result in results
            }.sumOf { (result, _) -> result }
    }
}
