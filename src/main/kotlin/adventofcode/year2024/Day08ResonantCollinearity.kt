package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Tuple.minus
import adventofcode.common.Tuple.plus

class Day08ResonantCollinearity(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val gridSize by lazy { input.lines().size }

    override fun partOne() =
        input
            .distinctAntinodesCount { (a, b) ->
                val distance = b - a
                setOf(a - distance, b + distance).filter { it.isInBounds((gridSize)) }.toSet()
            }

    override fun partTwo() =
        input
            .distinctAntinodesCount { (a, b) ->
                generateSequence(a) { antinode -> antinode - (b - a) }
                    .takeWhile { antinode -> antinode.isInBounds(gridSize) }
                    .toSet()
            }

    companion object {
        private fun Pair<Int, Int>.isInBounds(gridSize: Int) = toList().all { it in 0 until gridSize }

        private fun String.distinctAntinodesCount(antinodeFunction: (Pair<Pair<Int, Int>, Pair<Int, Int>>) -> Set<Pair<Int, Int>>) =
            lines()
                .flatMapIndexed { y, row -> row.mapIndexed { x, char -> (x to y) to char } }
                .filterNot { (_, char) -> char == '.' }
                .groupBy({ it.second }, { it.first })
                .map { (_, antennas) -> antennas.flatMap { a -> antennas.mapNotNull { b -> if (a != b) a to b else null } }.toSet() }
                .map { antennaPairs -> antennaPairs.flatMap { pair -> antinodeFunction(pair) } }
                .reduce { allAntinodes, antinodes -> allAntinodes + antinodes }
                .toSet()
                .size
    }
}
