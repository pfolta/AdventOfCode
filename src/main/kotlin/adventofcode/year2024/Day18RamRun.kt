package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Point2d
import adventofcode.common.spatial.Point2d.Companion.ORIGIN

class Day18RamRun(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "RAM Run"

    private val corruptedBytes by lazy {
        input.lines().map { line ->
            val (x, y) = line.split(",").map(String::toLong)
            Point2d(x, y)
        }
    }

    override fun partOne() = shortestPath(ORIGIN, corruptedBytes.take(KILOBYTE_IN_BYTES))!!

    override fun partTwo(): String {
        val (x, y) =
            generateSequence(KILOBYTE_IN_BYTES + 1, Int::inc)
                .first { bytes -> shortestPath(ORIGIN, corruptedBytes.take(bytes)) == null }
                .let { byte -> corruptedBytes[byte - 1] }

        return "$x,$y"
    }

    companion object {
        private const val GRID_SIZE = 70
        private const val KILOBYTE_IN_BYTES = 1024
        private val end = Point2d(GRID_SIZE, GRID_SIZE)

        private fun shortestPath(
            start: Point2d,
            corruptedBytes: List<Point2d>,
        ): Int? {
            val visited = mutableSetOf<Point2d>()
            val queue = ArrayDeque(setOf(start to 0))

            while (queue.isNotEmpty()) {
                val (position, cost) = queue.removeFirst()

                if (position == end) {
                    return cost
                }

                val neighbors =
                    position
                        .neighbors()
                        .filter { (x, y) -> x in 0..end.x && y in 0..end.y }
                        .filterNot { neighbor -> neighbor in visited }
                        .filterNot { neighbor -> neighbor in corruptedBytes }

                visited.addAll(neighbors)
                queue.addAll(neighbors.map { neighbor -> neighbor to cost + 1 })
            }

            return null
        }
    }
}
