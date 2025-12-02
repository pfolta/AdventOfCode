package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d
import adventofcode.common.spatial.Point2d
import adventofcode.common.spatial.Point2d.Companion.EAST
import adventofcode.common.spatial.Point2d.Companion.NORTH
import adventofcode.common.spatial.Point2d.Companion.SOUTH
import adventofcode.common.spatial.Point2d.Companion.WEST

class Day12GardenGroups(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val garden by lazy { Grid2d(input) }

    private val regions by lazy { garden.mapRegions() }

    override fun partOne() = regions.sumOf { region -> region.area * region.perimeter }

    override fun partTwo() = regions.sumOf { region -> region.area * region.sides(garden) }

    companion object {
        private data class Region(
            val type: Char,
            val plots: Set<Point2d>,
        ) {
            val area = plots.size

            val perimeter =
                plots.sumOf { plot ->
                    setOf(NORTH, EAST, SOUTH, WEST)
                        .map { direction -> plot + direction }
                        .filterNot { side -> side in plots }
                        .size
                }

            fun sides(garden: Grid2d<Char>): Int =
                plots.sumOf { plot ->
                    setOf(NORTH to EAST, EAST to SOUTH, SOUTH to WEST, WEST to NORTH)
                        .map { (first, second) ->
                            listOf(plot, plot + first, plot + second, plot + first + second)
                                .map { a -> garden.getOrNull(a) }
                        }.count { (plot, sideA, sideB, corner) ->
                            (plot != sideA && plot != sideB) || (plot == sideA && plot == sideB && plot != corner)
                        }
                }
        }

        private fun Grid2d<Char>.mapRegions(): Set<Region> {
            val regions = mutableSetOf<Region>()
            val queue = ArrayDeque(points)

            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()
                val plots = getConnectedPlots(current)

                queue.removeAll(plots)
                regions.add(Region(this[current], plots))
            }

            return regions
        }

        private fun Grid2d<Char>.getConnectedPlots(plot: Point2d): Set<Point2d> {
            val region = mutableSetOf<Point2d>()
            val queue = ArrayDeque(setOf(plot))

            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()

                val neighbors =
                    neighborsOf(current)
                        .filter { neighbor -> this[plot] == this[neighbor] }
                        .filterNot { neighbor -> neighbor in region }

                region.addAll(setOf(current) + neighbors)
                queue.addAll(neighbors)
            }

            return region
        }
    }
}
