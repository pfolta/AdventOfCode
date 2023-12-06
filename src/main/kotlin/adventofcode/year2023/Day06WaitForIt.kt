package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

class Day06WaitForIt(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne(): Int {
        val (times, distances) = input
            .lines()
            .map { line -> line.split(" ").mapNotNull(String::toIntOrNull) }

        return times
            .zip(distances)
            .map { (time, distance) ->
                val lower = (0.5 * (time - sqrt(time.toDouble().pow(2) - 4 * distance)) + 1).toInt()
                val upper = ceil(0.5 * (sqrt(time.toDouble().pow(2) - 4 * distance) + time) - 1).toInt()

                upper - lower + 1
            }
            .product()
    }
}
