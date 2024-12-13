package adventofcode.common

import kotlin.String

data class Point2d(
    val x: Int,
    val y: Int,
) {
    /**
     * Pretty formatted String representation.
     *
     * (0, 1)
     */
    override fun toString(): String = "($x, $y)"

    /**
     * Add two points together by adding their x and y coordinates
     */
    operator fun plus(other: Point2d): Point2d = Point2d(x + other.x, y + other.y)
}

val NORTH = Point2d(0, -1)
val NORTHEAST = Point2d(1, -1)
val EAST = Point2d(1, 0)
val SOUTHEAST = Point2d(1, 1)
val SOUTH = Point2d(0, 1)
val SOUTHWEST = Point2d(-1, 1)
val WEST = Point2d(-1, 0)
val NORTHWEST = Point2d(-1, -1)

data class Grid2d<T>(val values: List<List<T>>) {
    val points = values.flatMapIndexed { y, row -> List(row.size) { x -> Point2d(x, y) } }
    val isSquare: Boolean = values.all { row -> row.size == values.size }

    /**
     * Pretty formatted String representation.
     *
     * [A, B, C]
     * [D, E, F]
     * [G, H, I]
     */
    override fun toString(): String = values.joinToString("\n") { row -> row.toString() }

    /**
     * Returns `true` if the grid contains `value`
     */
    operator fun contains(value: T): Boolean = values.flatten().contains(value)

    /**
     * Returns `true` if the point is within the grid.
     */
    operator fun contains(point: Point2d): Boolean = point in points

    /**
     * Returns the points of all instances of `value` if the grid contains it.
     */
    fun find(value: T): List<Point2d> =
        points
            .filter { (x, y) -> values[y][x] == value }
            .map { (x, y) -> Point2d(x, y) }

    /**
     * Returns the value at the given point if the point is within the grid, throws otherwise.
     */
    operator fun get(point: Point2d): T =
        if (point in this) values[point.y][point.x] else throw IndexOutOfBoundsException("Point $point is not part of this grid")

    /**
     * Returns the value at the given point if the point is within the grid, or `null` otherwise.
     */
    fun getOrNull(point: Point2d): T? = if (point in this) values[point.y][point.x] else null

    /**
     * Returns a set of valid neighbors for a given point P in the grid.
     *
     * Excluding diagonals:     Including diagonals:
     * _ N _                    N N N
     * N P N                    N P N
     * _ N _                    N N N
     * where N is a neighbor of P.
     */
    fun neighborsOf(
        point: Point2d,
        includeDiagonals: Boolean = false,
    ): Set<Point2d> =
        when {
            includeDiagonals -> setOf(NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST)
            else -> setOf(NORTH, EAST, SOUTH, WEST)
        }
            .map { direction -> point + direction }
            .filter { it in this }
            .toSet()
}
