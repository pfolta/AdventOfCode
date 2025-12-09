package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Point2d
import kotlin.math.absoluteValue

class Day09MovieTheater(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput() =
        input.lines().map { line ->
            val (x, y) = line.split(",").map(String::toLong)
            Point2d(x, y)
        }

    override fun partOne() =
        parseInput()
            .let { points -> points.flatMapIndexed { index, a -> points.subList(index + 1, points.size).map { b -> a to b } } }
            .maxOf { (a, b) -> ((b.x - a.x).absoluteValue + 1) * ((b.y - a.y).absoluteValue + 1) }
}
