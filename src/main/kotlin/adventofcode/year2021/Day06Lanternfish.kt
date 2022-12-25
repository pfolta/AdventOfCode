package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day06Lanternfish(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val fish by lazy { input.split(",").map(String::toInt) }

    private val fishByAge by lazy { fish.groupingBy { it }.eachCount().map { (age, count) -> age to count.toLong() }.toMap() }

    override fun partOne() = generateSequence(fish) { previous ->
        previous.flatMap { if (it == 0) listOf(6, 8) else listOf(it - 1) }
    }
        .take(80 + 1)
        .last()
        .size

    override fun partTwo() = generateSequence(fishByAge) { previous ->
        previous
            .flatMap { (age, count) -> if (age == 0) listOf(6 to count, 8 to count) else listOf(age - 1 to count) }
            .groupBy { (age, _) -> age }
            .map { (age, count) -> age to count.sumOf { it.second } }
            .toMap()
    }
        .take(256 + 1)
        .last()
        .values
        .sum()
}
