package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.common.neighbors

class Day12HillClimbingAlgorithm(customInput: String? = null) : Puzzle(customInput) {
    private val grid by lazy { input.lines().map(String::toList) }

    override fun partOne() = grid.bfs(grid.find('S'), grid.find('E'))

    companion object {
        private fun List<List<Char>>.find(char: Char): Pair<Int, Int> {
            val y = indexOfFirst { row -> row.contains(char) }
            val x = this[y].indexOfFirst { column -> column == char }

            return x to y
        }

        private val Char.level
            get() = when (this) {
                'S' -> 0
                'E' -> 'z' - 'a'
                else -> this - 'a'
            }

        private fun List<List<Char>>.bfs(start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
            val queue: MutableList<Pair<Int, Int>> = mutableListOf(start)
            val visited: MutableMap<Pair<Int, Int>, Int> = mutableMapOf(start to 0)

            while (queue.isNotEmpty() && queue.first() != end) {
                val (x, y) = queue.removeFirst()

                neighbors(x, y, false)
                    .filter { (nx, ny) -> this[ny][nx].level - this[y][x].level <= 1 }
                    .filterNot { neighbor -> visited.contains(neighbor) }
                    .forEach { neighbor ->
                        queue.add(neighbor)
                        visited[neighbor] = visited[x to y]!! + 1
                    }
            }

            return visited[end]!!
        }
    }
}
