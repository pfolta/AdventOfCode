package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day03Lobby(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): List<List<Int>> = input.lines().map { bank -> bank.toList().map(Character::getNumericValue) }

    override fun partOne() =
        parseInput()
            .sumOf { bank ->
                val first = bank.dropLast(1).max()
                val second = bank.drop(bank.indexOf(first) + 1).max()

                "$first$second".toInt()
            }
}
