package adventofcode.year2021

import adventofcode.Puzzle

class Day09SmokeBasin(customInput: String? = null) : Puzzle(customInput) {
    private val heightMap by lazy { input.lines().map { it.map { it.toString().toInt() } } }

    private fun List<List<Int>>.neighbors(x: Int, y: Int) = listOf(x - 1 to y, x to y - 1, x + 1 to y, x to y + 1)
        .filter { it.first >= 0 && it.second >= 0 }
        .filter { it.first < this[y].size && it.second < heightMap.size }
        .map { (x, y) -> this[y][x] }

    private fun Int.isLowPoint(neighbors: List<Int>) = neighbors.all { neighbor -> this < neighbor }

    override fun partOne() = heightMap
        .flatMapIndexed { y, row -> row.filterIndexed { x, col -> col.isLowPoint(heightMap.neighbors(x, y)) } }
        .sumOf { height -> 1 + height }
}
