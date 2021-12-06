package adventofcode.year2021

import adventofcode.Puzzle

class Day06Lanternfish(customInput: String? = null) : Puzzle(customInput) {
    private val fish by lazy { input.split(",").map(String::toInt) }

    override fun partOne() = generateSequence(fish) { previous ->
        previous.flatMap { if (it == 0) listOf(6, 8) else listOf(it - 1) }
    }
        .take(80 + 1)
        .last()
        .size
}
