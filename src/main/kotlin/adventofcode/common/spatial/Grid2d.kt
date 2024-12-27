package adventofcode.common.spatial

data class Grid2d<T>(val values: List<List<T>>) {
    /** Set of points contained in this grid. */
    val points = values.flatMapIndexed { y, row -> List(row.size) { x -> Point2d(x, y) } }.toSet()

    /** `true` for a square grid, `false` otherwise. */
    val isSquare: Boolean = values.all { row -> row.size == values.size }

    /** Number of rows in the grid. */
    val rows = values.size

    /**
     * Pretty formatted String representation.
     *
     * [A, B, C]
     * [D, E, F]
     * [G, H, I]
     */
    override fun toString(): String = values.joinToString("\n") { row -> row.toString() }

    /** Returns the number of columns for a given row in the grid. */
    fun columnsInRow(row: Int) = values[row].size

    /** Returns the row of elements at the given row index. */
    fun rowAt(index: Int): List<T> = values[index]

    /** Returns the column of elements at the given column index. */
    fun columnAt(index: Int): List<T> = values.map { row -> row[index] }

    /** Syntactic sugar for nested `values` list. */
    fun rows(): List<List<T>> = values

    /** Returns all the columns of the grid. */
    fun columns(): List<List<T>> = rotate().values.map(List<T>::reversed)

    /** Returns `true` if the grid contains `value`. */
    operator fun contains(value: T): Boolean = values.flatten().contains(value)

    /** Returns `true` if the point is within the grid. */
    operator fun contains(point: Point2d): Boolean = point.y in values.indices && point.x in values[point.y.toInt()].indices

    /** Returns the points of all instances of `value` if the grid contains it. */
    fun findAll(value: T): Set<Point2d> = points.filter { (x, y) -> values[y.toInt()][x.toInt()] == value }.toSet()

    /** Returns the first point with the value `value` if the grid contains it, throws otherwise. */
    operator fun get(value: T): Point2d = findAll(value).first()

    /** Returns the value at the given point if the point is within the grid, throws otherwise. */
    operator fun get(point: Point2d): T =
        if (point in this) {
            values[point.y.toInt()][point.x.toInt()]
        } else {
            throw IndexOutOfBoundsException("Point $point is outside of the grid")
        }

    /** Returns the value at the given point if the point is within the grid, or `null` otherwise. */
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
        /** Create a new `Grid2d` from a `String` where each point is a character of that string. */
        operator fun invoke(values: String) = Grid2d(values.lines().map(String::toList))
    }
}
