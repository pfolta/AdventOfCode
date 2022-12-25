package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.neighbors

class Day11DumboOctopus(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val grid by lazy { input.lines().map { it.map { it.toString().toInt() } } }

    private fun List<List<Int>>.incrementAll() = map { it.map(Int::inc) }

    private fun List<List<Int>>.increment(points: List<Pair<Int, Int>>) =
        mapIndexed { y, row -> row.mapIndexed { x, col -> col + points.filter { it.first == x && it.second == y }.size } }

    private fun List<List<Int>>.flashes() = flatMapIndexed { y, row -> row.mapIndexedNotNull { x, col -> if (col > 9) x to y else null } }

    private fun List<List<Int>>.reset() = map { row -> row.map { col -> if (col > 9) 0 else col } }

    private fun List<List<Int>>.simulate(steps: Int) = generateSequence(0 to this) { (previousFlashes, previousGrid) ->
        val incrementedGrid = previousGrid.incrementAll()

        val (newFlashes, newGrid, _) = generateSequence(
            Triple(0, incrementedGrid, emptySet<Pair<Int, Int>>())
        ) { (currentFlashes, currentGrid, alreadyFlashed) ->
            val flashes = currentGrid.flashes().minus(alreadyFlashed)
            val neighbors = flashes.flatMap { currentGrid.neighbors(it.first, it.second, true) }

            when (flashes.size) {
                0 -> null
                else -> Triple(currentFlashes + flashes.size, currentGrid.increment(neighbors), alreadyFlashed.plus(flashes))
            }
        }
            .last()

        previousFlashes + newFlashes to newGrid.reset()
    }
        .take(steps + 1)
        .last()

    override fun partOne() = grid.simulate(100).first

    override fun partTwo() = generateSequence(0 to grid) { (previousStep, previousGrid) ->
        previousStep + 1 to previousGrid.simulate(1).second
    }
        .first { (_, grid) -> grid.flatten().all { it == 0 } }
        .first
}
