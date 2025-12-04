package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day03Lobby(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): List<List<IndexedValue<Int>>> = input.lines().map { bank -> bank.map(Char::digitToInt).withIndex().toList() }

    private fun List<List<IndexedValue<Int>>>.totalOutputJoltage(batteryCount: Int) =
        sumOf { bank ->
            (batteryCount downTo 1)
                .fold(Triple(0, bank.size - batteryCount, 0L)) { (lIndex, rIndex, joltage), battery ->
                    bank
                        .subList(lIndex, rIndex + 1)
                        .maxBy { battery -> battery.value }
                        .let { max -> Triple(max.index + 1, bank.size - battery + 1, joltage * 10 + max.value) }
                }.third
        }

    override fun partOne() = parseInput().totalOutputJoltage(2)

    override fun partTwo() = parseInput().totalOutputJoltage(12)
}
