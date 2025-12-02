package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day01SecretEntrance(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): List<Int> = input.lines().map { rotation -> rotation.replace("L", "-").replace("R", "").toInt() }

    override fun partOne() =
        parseInput()
            .runningFold(DIAL_START) { position, rotation -> (position + rotation).mod(DIAL_NUMBERS) }
            .count { position -> position == 0 }

    override fun partTwo() =
        parseInput()
            .fold(DIAL_START to 0) { (position, password), rotation ->
                (position + rotation).mod(DIAL_NUMBERS) to password +
                    when {
                        rotation > 0 -> (position + rotation) / DIAL_NUMBERS
                        position == 0 -> -rotation / DIAL_NUMBERS
                        else -> (-rotation - position + DIAL_NUMBERS) / DIAL_NUMBERS
                    }
            }.second

    companion object {
        private const val DIAL_START = 50
        private const val DIAL_NUMBERS = 100
    }
}
