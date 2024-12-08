package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Tuple.minus
import adventofcode.common.Tuple.plus

class Day08ResonantCollinearity(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val gridSize by lazy { input.lines().size }

    override fun partOne() =
        input
            .lines()
            .flatMapIndexed { y, row -> row.mapIndexed { x, char -> (x to y) to char } }
            .filterNot { (_, char) -> char == '.' }
            .groupBy({ it.second }, { it.first })
            .map { (char, antennas) ->
                char to antennas.flatMap { a -> antennas.mapNotNull { b -> if (a != b) setOf(a, b) else null } }.toSet()
            }
            .map { (char, antennaPairs) ->
                char to
                    antennaPairs.map { pair ->
                        val distance = pair.last() - pair.first()
                        setOf(pair.first() - distance, pair.last() + distance).filter { it.isInBounds((gridSize)) }
                    }
            }
            .map { (_, antinodes) -> antinodes.flatten() }
            .reduce { allAntinodes, antinodes -> allAntinodes + antinodes }
            .toSet()
            .size

    companion object {
        fun Pair<Int, Int>.isInBounds(gridSize: Int) = toList().all { it in 0 until gridSize }
    }
}
