package adventofcode.year2022

import adventofcode.Puzzle

class Day01CalorieCounting(customInput: String? = null) : Puzzle(customInput) {
    private val elfCalories by lazy { input.split("\n\n").map { elf -> elf.lines().map(String::toInt) } }

    override fun partOne() = elfCalories.maxOf(List<Int>::sum)
}
