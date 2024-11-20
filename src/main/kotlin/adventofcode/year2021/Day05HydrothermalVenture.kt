package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.max
import kotlin.math.min

class Day05HydrothermalVenture(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val lines by lazy { input.lines().map { it.split(" -> ").map(Point::invoke) }.map(::Line) }

    override fun partOne() =
        lines
            .filter { it.isHorizontal() || it.isVertical() }
            .flatMap(Line::getCoveredPoints)
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }

    override fun partTwo() =
        lines
            .flatMap(Line::getCoveredPoints)
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }

    companion object {
        private data class Point(
            val x: Int,
            val y: Int,
        ) {
            companion object {
                operator fun invoke(coordinates: String): Point {
                    val (x, y) = coordinates.split(",").map(String::toInt)
                    return Point(x, y)
                }
            }
        }

        private data class Line(
            val start: Point,
            val end: Point,
        ) {
            constructor(points: List<Point>) : this(points.first(), points.last())

            fun isHorizontal() = start.y == end.y

            fun isVertical() = start.x == end.x

            fun getCoveredPoints() =
                when {
                    isHorizontal() -> IntRange(min(start.x, end.x), max(start.x, end.x)).map { Point(it, start.y) }
                    isVertical() -> IntRange(min(start.y, end.y), max(start.y, end.y)).map { Point(start.x, it) }
                    else -> {
                        val left = if (start.x < end.x) start else end
                        val right = listOf(start, end).minus(left).first()

                        val gradient = if (left.y < right.y) 1 else -1

                        (left.x..right.x).map { Point(it, gradient * (it - left.x) + left.y) }
                    }
                }
        }
    }
}
