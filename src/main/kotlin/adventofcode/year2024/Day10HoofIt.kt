package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.neighbors

class Day10HoofIt(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val map by lazy {
        input.lines().mapIndexed { y, line -> line.mapIndexed { x, height -> x to y to (height.digitToIntOrNull() ?: -1) } }
    }

    private val trailheads by lazy { map.flatten().filter { (_, height) -> height == 0 } }

    override fun partOne() = trailheads.sumOf { (trailhead, _) -> map.countTrails(trailhead, true) }

    override fun partTwo() = trailheads.sumOf { (trailhead, _) -> map.countTrails(trailhead, false) }

    companion object {
        private fun List<List<Pair<Pair<Int, Int>, Int>>>.countTrails(
            trailhead: Pair<Int, Int>,
            distinct: Boolean,
        ): Int {
            val visited = mutableListOf<Pair<Int, Int>>()
            val queue = ArrayDeque(setOf(trailhead))

            while (queue.isNotEmpty()) {
                val position = queue.removeFirst()
                val (x, y) = position

                if (!distinct || position !in visited) {
                    visited.add(position)

                    val nextPositions =
                        neighbors(x, y, false)
                            .filter { (a, b) -> this[b][a].second == this[y][x].second + 1 }
                            .filterNot { it in visited }

                    queue.addAll(nextPositions)
                }
            }

            return visited.count { (x, y) -> this[y][x].second == 9 }
        }
    }
}
