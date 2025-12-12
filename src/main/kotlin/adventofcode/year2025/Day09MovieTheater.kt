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
                val (x, y) = line.split(",", limit = 2).map(String::toLong)
                Point2d(x, y)
            }

        val polygon = points.fold(Polygon()) { polygon, (x, y) -> polygon.apply { addPoint(x.toInt(), y.toInt()) } }
        val polygonArea = Area(polygon)

        val rectangles =
            points
                .flatMapIndexed { index, a ->
                    points.drop(index + 1).map { b ->
                        Rectangle2D.Double(
                            minOf(a.x, b.x).toDouble(),
                            minOf(a.y, b.y).toDouble(),
                            (a.x - b.x).absoluteValue.toDouble(),
                            (a.y - b.y).absoluteValue.toDouble(),
                        )
                    }
                }.sortedByDescending { rectangle -> rectangle.area() }
                .toSet()

        return Pair(polygonArea, rectangles)
    }

    override fun partOne() = parseInput().second.first().area()

    override fun partTwo() =
        parseInput()
            .let { (polygonArea, rectangles) ->
                rectangles
                    .first { rectangle -> polygonArea.contains(rectangle) }
                    .area()
            }

    companion object {
        private fun Rectangle2D.area() = ((width + 1) * (height + 1)).toLong()
    }
}
