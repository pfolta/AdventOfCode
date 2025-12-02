package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day01CalorieCounting(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val elfCalories by lazy { input.split("\n\n").map { elf -> elf.lines().map(String::toInt) } }

    override fun partOne() = elfCalories.maxOf(List<Int>::sum)

    override fun partTwo() =
        elfCalories
            .map(List<Int>::sum)
            .sortedDescending()
            .take(3)
            .sum()
}
