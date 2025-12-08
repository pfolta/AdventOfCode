package adventofcode.common.spatial

import kotlin.math.pow
import kotlin.math.sqrt

data class Point3d(
    val x: Long,
    val y: Long,
    val z: Long,
) {
    /** Alternative constructor to allow other Number types as coordinates, e.g. `Int`s. */
    constructor(x: Number, y: Number, z: Number) : this(x.toLong(), y.toLong(), z.toLong())

    /** Pretty formatted String representation: (0, 1, 2) */
    override fun toString(): String = "($x, $y, $z)"

    /** Add two points together by adding their x, y and z coordinates. */
    operator fun plus(other: Point3d): Point3d = Point3d(x + other.x, y + other.y, z + other.z)

    /** Add a constant offset to the point's x, y and z coordinates. */
    operator fun plus(offset: Number): Point3d = Point3d(x + offset.toLong(), y + offset.toLong(), z + offset.toLong())

    /** Subtract two points from another by subtracting their x, y and z coordinates. */
    operator fun minus(other: Point3d): Point3d = Point3d(x - other.x, y - other.y, z - other.z)

    /** Returns the Euclidean (straight-line) distance between two points. */
    infix fun euclideanDistanceTo(other: Point3d): Double =
        sqrt((x - other.x).toDouble().pow(2) + (y - other.y).toDouble().pow(2) + (z - other.z).toDouble().pow(2))

    companion object {
        /** The midpoint of the cartesian grid. */
        val ORIGIN = Point3d(0, 0, 0)
    }
}
