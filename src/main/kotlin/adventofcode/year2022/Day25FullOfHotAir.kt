package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.pow

class Day25FullOfHotAir(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Full of Hot Air"

    override fun partOne() = input
        .lines()
        .sumOf { it.fromSnafuNumber() }
        .toSnafuNumber()

    companion object {
        private const val SNAFU_NUMBER_BASE = 5

        private fun String.fromSnafuNumber() = reversed()
            .mapIndexed { index, digit ->
                SNAFU_NUMBER_BASE.toDouble().pow(index).toLong() * when (digit) {
                    '=' -> -2
                    '-' -> -1
                    else -> digit.digitToInt()
                }
            }
            .sum()

        private tailrec fun Long.toSnafuNumber(snafuNumber: String = ""): String {
            if (this == 0L) {
                return snafuNumber
            }

            val quotient = this / SNAFU_NUMBER_BASE

            return when (val remainder = this % SNAFU_NUMBER_BASE) {
                4L -> (quotient + 1).toSnafuNumber("-$snafuNumber")
                3L -> (quotient + 1).toSnafuNumber("=$snafuNumber")
                else -> quotient.toSnafuNumber("$remainder$snafuNumber")
            }
        }
    }
}
