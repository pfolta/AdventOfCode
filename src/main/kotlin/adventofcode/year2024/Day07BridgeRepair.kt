package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day07BridgeRepair(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() =
        input
            .lines()
            .mapNotNull { equation ->
                val result = equation.split(": ").first().toLong()
                val numbers = equation.split(": ").last().split(" ").map(String::toLong)

                val results =
                    numbers
                        .drop(1)
                        .fold(setOf(numbers.first())) { candidates, number ->
                            candidates.flatMap { candidate -> setOf(candidate + number, candidate * number) }.toSet()
                        }

                if (result in results) result else null
            }
            .sum()
}
