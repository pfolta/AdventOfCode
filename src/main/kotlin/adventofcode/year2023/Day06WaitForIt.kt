package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product
import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.sqrt

class Day06WaitForIt(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne(): Long {
        val (times, distances) = input
            .lines()
            .map { line -> line.split(" ").mapNotNull(String::toLongOrNull) }

        return times
            .zip(distances)
            .map { it.countWaysToWin() }
            .product()
    }

    override fun partTwo() = input
        .lines()
        .map { line -> line.filter(Char::isDigit).toLong() }
        .zipWithNext()
        .first()
        .countWaysToWin()

    companion object {
        private fun Pair<Long, Long>.countWaysToWin(): Long {
            val (time, distance) = this

            val lower = (0.5 * (time - sqrt(time.toDouble().pow(2) - 4 * distance)) + 1).toLong()
            val upper = ceil(0.5 * (sqrt(time.toDouble().pow(2) - 4 * distance) + time) - 1).toLong()

            return upper - lower + 1
        }
    }
}
