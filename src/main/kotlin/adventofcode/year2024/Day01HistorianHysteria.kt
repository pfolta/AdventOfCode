package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.abs

class Day01HistorianHysteria(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val lists by lazy {
        input.lines().map { line -> line.split(" ").mapNotNull(String::toIntOrNull) }
    }

    private val leftList by lazy { lists.map { it.first() } }
    private val rightList by lazy { lists.map { it.last() } }

    override fun partOne() = leftList
        .sorted()
        .zip(rightList.sorted())
        .sumOf { (left, right) -> abs(left - right) }

}
