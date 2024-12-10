package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.neighbors

class Day10HoofIt(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val map by lazy {
        input.lines().mapIndexed { y, line -> line.mapIndexed { x, height -> x to y to (height.digitToIntOrNull() ?: -1) } }
    }

    override fun partOne() =
        map
            .flatten()
            .filter { (_, height) -> height == 0 }
            .sumOf { (trailhead, _) -> map.countTrails(trailhead, 9, true, 1) }

    override fun partTwo() =
        map
            .flatten()
            .filter { (_, height) -> height == 9 }
            .sumOf { (trailhead, _) -> map.countTrails(trailhead, 0, false, -1) }

    companion object {
        private fun List<List<Pair<Pair<Int, Int>, Int>>>.countTrails(
            start: Pair<Int, Int>,
            end: Int,
            distinct: Boolean,
            delta: Int,
        ): Int {
            val visited = mutableListOf<Pair<Int, Int>>()
            val queue = ArrayDeque(setOf(start))

            while (queue.isNotEmpty()) {
                val position = queue.removeFirst()
                val (x, y) = position

                if (!distinct || position !in visited) {
                    visited.add(position)

                    val nextPositions =
                        neighbors(x, y, false)
                            .filter { (a, b) -> this[b][a].second - this[y][x].second == delta }
                            .filterNot { it in visited }

                    queue.addAll(nextPositions)
                }
            }

            return visited.count { (x, y) -> this[y][x].second == end }
        }
    }
}
