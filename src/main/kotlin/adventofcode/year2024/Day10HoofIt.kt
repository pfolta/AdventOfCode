package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d
import adventofcode.common.spatial.Point2d

class Day10HoofIt(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val topographicMap by lazy {
        Grid2d(input.lines().map { line -> line.map { height -> height.digitToIntOrNull() ?: -1 } })
    }

    private val trailheads by lazy { topographicMap.points.filter { position -> topographicMap[position] == TRAIL_START_HEIGHT } }

    override fun partOne() = trailheads.sumOf { trailhead -> topographicMap.countTrails(trailhead, true) }

    override fun partTwo() = trailheads.sumOf { trailhead -> topographicMap.countTrails(trailhead, false) }

    companion object {
        private const val TRAIL_START_HEIGHT = 0
        private const val TRAIL_END_HEIGHT = 9

        private fun Grid2d<Int>.countTrails(
            trailhead: Point2d,
            distinct: Boolean,
        ): Int {
            val visited = mutableListOf<Point2d>()
            val queue = ArrayDeque(setOf(trailhead))

            while (queue.isNotEmpty()) {
                val position = queue.removeFirst()

                if (!distinct || position !in visited) {
                    visited.add(position)

                    val nextPositions =
                        neighborsOf(position)
                            .filter { neighbor -> this[neighbor] == this[position] + 1 }
                            .filterNot { it in visited }

                    queue.addAll(nextPositions)
                }
            }

            return visited.count { position -> this[position] == TRAIL_END_HEIGHT }
        }
    }
}
