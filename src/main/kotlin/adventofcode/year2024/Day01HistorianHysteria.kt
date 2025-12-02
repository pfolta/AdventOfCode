package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.abs

class Day01HistorianHysteria(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): Pair<List<Int>, List<Int>> {
        val lists = input.lines().map { line -> line.split(" ").mapNotNull(String::toIntOrNull) }
        val leftList = lists.map { it.first() }
        val rightList = lists.map { it.last() }

        return leftList to rightList
    }

    override fun partOne() =
        parseInput()
            .let { (leftList, rightList) ->
                leftList
                    .sorted()
                    .zip(rightList.sorted())
                    .sumOf { (left, right) -> abs(left - right) }
            }

    override fun partTwo() =
        parseInput()
            .let { (leftList, rightList) ->
                leftList.sumOf { left -> left * rightList.count { right -> left == right } }
            }
}
