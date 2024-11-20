package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.neighbors

class Day12HillClimbingAlgorithm(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val grid by lazy { input.lines().map(String::toList) }

    override fun partOne() = grid.bfs('S', 'E') { from, to -> to.level - from.level <= 1 }

    override fun partTwo() = grid.bfs('E', 'a') { from, to -> to.level - from.level >= -1 }

    companion object {
        private fun List<List<Char>>.find(char: Char): Pair<Int, Int> {
            val y = indexOfFirst { row -> row.contains(char) }
            val x = this[y].indexOfFirst { column -> column == char }

            return x to y
        }

        private val Char.level
            get() =
                when (this) {
                    'S' -> 0
                    'E' -> 'z' - 'a'
                    else -> this - 'a'
                }

        private fun List<List<Char>>.bfs(
            start: Char,
            end: Char,
            validNeighborTest: (from: Char, to: Char) -> Boolean,
        ): Int {
            val startNode = find(start)

            val queue: MutableList<Pair<Int, Int>> = mutableListOf(startNode)
            val visited: MutableMap<Pair<Int, Int>, Int> = mutableMapOf(startNode to 0)

            while (queue.isNotEmpty() && this[queue.first().second][queue.first().first] != end) {
                val (x, y) = queue.removeFirst()

                neighbors(x, y, false)
                    .filter { (nx, ny) -> validNeighborTest(this[y][x], this[ny][nx]) }
                    .filterNot { neighbor -> visited.contains(neighbor) }
                    .forEach { neighbor ->
                        queue += neighbor
                        visited[neighbor] = visited[x to y]!! + 1
                    }
            }

            return visited.keys.filter { (x, y) -> this[y][x] == end }.minOf { key -> visited[key]!! }
        }
    }
}
