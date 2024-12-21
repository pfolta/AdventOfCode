package adventofcode.common.spatial

import kotlin.math.absoluteValue

data class Point2d(
    val x: Long,
    val y: Long,
) {
    constructor(x: Number, y: Number) : this(x.toLong(), y.toLong())

    /**
     * Pretty formatted String representation.
     *
     * (0, 1)
     */
    override fun toString(): String = "($x, $y)"

    /** Add two points together by adding their x and y coordinates. */
    operator fun plus(other: Point2d): Point2d = Point2d(x + other.x, y + other.y)

    /** Add a constant offset to the point's x and y coordinates. */
    operator fun plus(offset: Number): Point2d = Point2d(x + offset.toLong(), y + offset.toLong())

    /** Subtract two points from another by subtracting their x and y coordinates. */
    operator fun minus(other: Point2d): Point2d = Point2d(x - other.x, y - other.y)

    /**
     * Returns a set of neighboring points. Can include negative coordinates.
     *
     * Excluding diagonals:     Including diagonals:
     * _ N _                    N N N
     * N P N                    N P N
     * _ N _                    N N N
     * where N is a neighbor of P.
     */
    fun neighbors(includeDiagonals: Boolean = false): Set<Point2d> =
        when {
            includeDiagonals -> setOf(NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST)
            else -> setOf(NORTH, EAST, SOUTH, WEST)
        }
            .map { direction -> this + direction }
            .toSet()

    /** Returns the manhattan distance between two points. */
    infix fun distanceTo(other: Point2d): Long = (x - other.x).absoluteValue + (y - other.y).absoluteValue

    companion object {
        /** The midpoint of the cartesian grid. */
        val ORIGIN = Point2d(0, 0)

        val NORTH = Point2d(0, -1)
        val NORTHEAST = Point2d(1, -1)
        val EAST = Point2d(1, 0)
        val SOUTHEAST = Point2d(1, 1)
        val SOUTH = Point2d(0, 1)
        val SOUTHWEST = Point2d(-1, 1)
        val WEST = Point2d(-1, 0)
        val NORTHWEST = Point2d(-1, -1)
    }
}
