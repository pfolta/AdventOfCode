package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Point2d
import java.awt.Polygon
import java.awt.geom.Area
import java.awt.geom.Rectangle2D
import kotlin.math.absoluteValue

class Day09MovieTheater(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput(): Pair<Area, Set<Rectangle2D>> {
        val points =
            input.lines().map { line ->
                val (x, y) = line.split(",").map(String::toLong)
                Point2d(x, y)
            }

        val polygon = points.fold(Polygon()) { polygon, (x, y) -> polygon.apply { addPoint(x.toInt(), y.toInt()) } }
        val polygonArea = Area(polygon)

        val rectangles =
            points
                .flatMapIndexed { index, a -> points.subList(index + 1, points.size).map { b -> a to b } }
                .map { (a, b) ->
                    Rectangle2D.Double(
                        minOf(a.x, b.x).toDouble(),
                        minOf(a.y, b.y).toDouble(),
                        (a.x - b.x).absoluteValue.toDouble(),
                        (a.y - b.y).absoluteValue.toDouble(),
                    )
                }.toSet()

        return Pair(polygonArea, rectangles)
    }

    override fun partOne() = parseInput().second.maxOf { rectangle -> rectangle.area() }

    override fun partTwo() =
        parseInput()
            .let { (polygonArea, rectangles) ->
                rectangles
                    .filter { rectangle -> polygonArea.contains(rectangle) }
                    .maxOf { rectangle -> rectangle.area() }
            }

    companion object {
        private fun Rectangle2D.area() = ((width + 1) * (height + 1)).toLong()
    }
}
