package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day03Lobby(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): List<List<Int>> = input.lines().map { bank -> bank.toList().map(Character::getNumericValue) }

    private fun List<List<Int>>.totalOutputJoltage(batteryCount: Int) =
        sumOf { bank ->
            (batteryCount downTo 1)
                .runningFold(Triple<Int, Int, Int?>(0, bank.size - batteryCount, null)) { (lIndex, rIndex), battery ->
                    val slice = bank.subList(lIndex, rIndex + 1)
                    val max = slice.max()
                    Triple((slice.indexOf(max) + lIndex + 1), bank.size - battery + 1, max)
                }.mapNotNull { it.third }
                .joinToString("")
                .toLong()
        }

    override fun partOne() = parseInput().totalOutputJoltage(2)

    override fun partTwo() = parseInput().totalOutputJoltage(12)
}
