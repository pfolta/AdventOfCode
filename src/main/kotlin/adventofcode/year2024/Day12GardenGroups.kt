package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Direction
import adventofcode.common.Direction.EAST
import adventofcode.common.Direction.NORTH
import adventofcode.common.Direction.SOUTH
import adventofcode.common.Direction.WEST
import adventofcode.common.Tuple.plus
import adventofcode.common.contains
import adventofcode.common.neighbors

class Day12GardenGroups(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val garden by lazy {
        input.lines().mapIndexed { y, line -> line.mapIndexed { x, type -> GardenPlot(x, y, type) } }
    }

    private val regions by lazy { garden.mapRegions() }

    override fun partOne() = regions.sumOf { region -> region.area * region.perimeter }

    override fun partTwo() = regions.sumOf { region -> region.area * region.sides(garden) }

    companion object {
        private data class GardenPlot(
            val x: Int,
            val y: Int,
            val type: Char,
        )

        private data class Region(
            val type: Char,
            val plots: Set<GardenPlot>,
        ) {
            val area = plots.size

            val perimeter =
                plots.sumOf { plot ->
                    Direction
                        .entries
                        .map { direction -> (plot.x to plot.y) + direction.delta }
                        .filterNot { side -> side in plots.map { (x, y) -> x to y } }
                        .size
                }

            fun sides(garden: List<List<GardenPlot>>): Int =
                plots.sumOf { (x, y) ->
                    setOf(NORTH to EAST, EAST to SOUTH, SOUTH to WEST, WEST to NORTH)
                        .map { (first, second) ->
                            listOf(x to y, (x to y) + first.delta, (x to y) + second.delta, (x to y) + first.delta + second.delta)
                                .map { (a, b) -> if (garden.contains(a, b)) garden[b][a].type else null }
                        }
                        .count { (plot, sideA, sideB, corner) ->
                            (plot != sideA && plot != sideB) || (plot == sideA && plot == sideB && plot != corner)
                        }
                }
        }

        private fun List<List<GardenPlot>>.mapRegions(): Set<Region> {
            val regions = mutableSetOf<Region>()
            val queue = ArrayDeque(flatten())

            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()
                val plots = getConnectedPlots(current)

                queue.removeAll(plots)
                regions.add(Region(current.type, plots))
            }

            return regions
        }

        private fun List<List<GardenPlot>>.getConnectedPlots(plot: GardenPlot): Set<GardenPlot> {
            val region = mutableSetOf<GardenPlot>()
            val queue = ArrayDeque(setOf(plot))

            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()

                val neighbors =
                    neighbors(current.x, current.y, false)
                        .map { (x, y) -> this[y][x] }
                        .filter { (_, _, type) -> type == plot.type }
                        .filterNot { neighbor -> neighbor in region }

                region.addAll(setOf(current) + neighbors)
                queue.addAll(neighbors)
            }

            return region
        }
    }
}
