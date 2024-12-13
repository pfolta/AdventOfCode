package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d
import adventofcode.common.spatial.Point2d

class Day08ResonantCollinearity(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val grid by lazy { Grid2d(input) }

    override fun partOne() =
        grid
            .distinctAntinodesCount { (a, b) ->
                val distance = b - a
                setOf(a - distance, b + distance).filter { point -> point in grid }.toSet()
            }

    override fun partTwo() =
        grid
            .distinctAntinodesCount { (a, b) ->
                generateSequence(a) { antinode -> antinode - (b - a) }
                    .takeWhile { antinode -> antinode in grid }
                    .toSet()
            }

    companion object {
        private fun Grid2d<Char>.distinctAntinodesCount(antinodeFunction: (Pair<Point2d, Point2d>) -> Set<Point2d>) =
            points
                .filterNot { point -> this[point] == '.' }
                .map { point -> point to this[point] }
                .groupBy({ it.second }, { it.first })
                .map { (_, antennas) -> antennas.flatMap { a -> antennas.mapNotNull { b -> if (a != b) a to b else null } }.toSet() }
                .map { antennaPairs -> antennaPairs.flatMap { pair -> antinodeFunction(pair) } }
                .reduce { allAntinodes, antinodes -> allAntinodes + antinodes }
                .toSet()
                .size
    }
}
