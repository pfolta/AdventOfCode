package adventofcode.common.spatial

data class Grid2d<T>(val values: List<List<T>>) {
    val points = values.flatMapIndexed { y, row -> List(row.size) { x -> Point2d(x, y) } }

    /**
     * `true` for a square grid, `false` otherwise.
     */
    val isSquare: Boolean = values.all { row -> row.size == values.size }

    /**
     * Number of rows in the grid.
     */
    val rows = values.size

    /**
     * Pretty formatted String representation.
     *
     * [A, B, C]
     * [D, E, F]
     * [G, H, I]
     */
    override fun toString(): String = values.joinToString("\n") { row -> row.toString() }

    /**
     * Returns the number of columns for a given row in the grid.
     */
    fun colsInRow(row: Int) = values[row].size

    /**
     * Returns `true` if the grid contains `value`.
     */
    operator fun contains(value: T): Boolean = values.flatten().contains(value)

    /**
     * Returns `true` if the point is within the grid.
     */
    operator fun contains(point: Point2d): Boolean = point.y in values.indices && point.x in values[point.y.toInt()].indices

    /**
     * Returns the points of all instances of `value` if the grid contains it.
     */
    fun find(value: T): Set<Point2d> = points.filter { (x, y) -> values[y.toInt()][x.toInt()] == value }.toSet()

    /**
     * Returns the value at the given point if the point is within the grid, throws otherwise.
     */
    operator fun get(point: Point2d): T =
        if (point in this) {
            values[point.y.toInt()][point.x.toInt()]
        } else {
            throw IndexOutOfBoundsException("Point $point is outside of the grid")
        }

    /**
     * Returns the value at the given point if the point is within the grid, or `null` otherwise.
     */
    fun getOrNull(point: Point2d): T? = if (point in this) values[point.y.toInt()][point.x.toInt()] else null

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
    ): Set<Point2d> = point.neighbors(includeDiagonals).filter { it in this }.toSet()

    /**
     * Returns a new grid by rotating this grid 90deg clockwise.
     *
     * Original grid:     After rotation:
     * [A, B, C]          [G, D, A]
     * [D, E, F]          [H, E, B]
     * [G, H, I]          [I, F, C]
     */
    fun rotate(): Grid2d<T> {
        val result = values.first().map { mutableListOf<T>() }
        values.forEach { list -> result.zip(list).forEach { it.first.add(it.second) } }
        return Grid2d(result.map(List<T>::reversed))
    }

    companion object {
        operator fun invoke(values: String) = Grid2d(values.lines().map(String::toList))
    }
}
