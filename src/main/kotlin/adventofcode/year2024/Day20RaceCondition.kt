package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d
import adventofcode.common.spatial.Point2d

class Day20RaceCondition(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val racetrack by lazy { input.racketrack() }

    override fun partOne() = racetrack.findCheats(2)

    override fun partTwo() = racetrack.findCheats(20)

    companion object {
        private const val MINIMUM_SAVINGS = 100

        private fun String.racketrack(): List<Point2d> {
            val grid = Grid2d(this)
            val path = mutableListOf(grid['S'])

            while (path.last() != grid['E']) {
                val next =
                    grid
                        .neighborsOf(path.last())
                        .filterNot { neighbor -> neighbor == path.getOrNull(path.lastIndex - 1) }
                        .filterNot { neighbor -> grid[neighbor] == '#' }
                        .first()

                path.add(next)
            }

            return path.toList()
        }

        private fun List<Point2d>.findCheats(time: Int) =
            indices.sumOf { start ->
                (start + MINIMUM_SAVINGS..lastIndex).count { end ->
                    val distance = this[start] distanceTo this[end]
                    (distance <= time) && (distance <= end - start - MINIMUM_SAVINGS)
                }
            }
    }
}
