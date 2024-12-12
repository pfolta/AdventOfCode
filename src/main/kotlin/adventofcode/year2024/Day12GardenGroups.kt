package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Direction
import adventofcode.common.Tuple.plus
import adventofcode.common.neighbors

class Day12GardenGroups(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val map by lazy {
        input.lines().mapIndexed { y, line -> line.mapIndexed { x, type -> GardenPlot(x, y, type) } }
    }

    override fun partOne() =
        map
            .mapRegions()
            .sumOf { region -> region.area * region.perimeter }

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
                plots
                    .sumOf { plot ->
                        Direction
                            .entries
                            .map { direction -> (plot.x to plot.y) + direction.delta }
                            .filterNot { side -> side in plots.map { (x, y) -> x to y } }
                            .size
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
                    neighbors(current.y, current.x, false)
                        .map { (y, x) -> this[y][x] }
                        .filter { (_, _, type) -> type == plot.type }
                        .filterNot { neighbor -> neighbor in region }

                region.addAll(setOf(current) + neighbors)
                queue.addAll(neighbors)
            }

            return region
        }
    }
}
