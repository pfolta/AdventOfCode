package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Point2d
import adventofcode.common.spatial.Point2d.Companion.Origin

class Day18RamRun(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val corruptedBytes by lazy {
        input.lines().map { line ->
            val (x, y) = line.split(",").map(String::toLong)
            Point2d(x, y)
        }
    }

    override val name = "RAM Run"

    override fun partOne() = shortestPath(Origin, Point2d(GRID_SIZE, GRID_SIZE), corruptedBytes.take(1024).toSet())

    companion object {
        private const val GRID_SIZE = 70

        private fun shortestPath(
            start: Point2d,
            end: Point2d,
            corruptedBytes: Set<Point2d>,
        ): Int {
            val visited = mutableSetOf<Pair<Point2d, Int>>()
            val queue = ArrayDeque(setOf(start to 0))

            while (queue.isNotEmpty()) {
                val (position, cost) = queue.removeFirst()

                val neighbors =
                    position
                        .neighbors()
                        .filter { (x, y) -> x in 0..end.x && y in 0..end.y }
                        .filterNot { neighbor -> neighbor in visited.map { it.first } }
                        .filterNot { neighbor -> neighbor in corruptedBytes }
                        .map { neighbor -> neighbor to cost + 1 }

                visited.addAll(neighbors)
                queue.addAll(neighbors)
            }

            return visited.first { (position) -> position == end }.second
        }
    }
}
