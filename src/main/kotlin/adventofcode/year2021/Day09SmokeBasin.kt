package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.common.product

class Day09SmokeBasin(customInput: String? = null) : Puzzle(customInput) {
    private val heightMap by lazy { input.lines().map { row -> row.map { col -> col.toString().toInt() } } }

    private fun List<List<Int>>.neighbors(x: Int, y: Int) = setOf(x - 1 to y, x to y - 1, x + 1 to y, x to y + 1)
        .filter { it.first >= 0 && it.second >= 0 }
        .filter { it.first < this[y].size && it.second < size }
        .toSet()

    private fun List<List<Int>>.basin(x: Int, y: Int) = generateSequence(setOf(x to y)) { previous ->
        val new = previous.flatMap { (x, y) -> neighbors(x, y).filter { (x, y) -> this[y][x] < 9 } + setOf(x to y) }.toSet()
        if (new.size > previous.size) new else null
    }
        .last()

    private fun Int.isLowPoint(neighbors: List<Int>) = neighbors.all { neighbor -> this < neighbor }

    private fun Int.riskLevel() = this + 1

    override fun partOne() = heightMap
        .flatMapIndexed { y, row ->
            row.filterIndexed { x, col ->
                col.isLowPoint(heightMap.neighbors(x, y).map { (x, y) -> heightMap[y][x] })
            }
        }
        .sumOf { height -> height.riskLevel() }

    override fun partTwo() = heightMap
        .flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, col ->
                if (col.isLowPoint(heightMap.neighbors(x, y).map { (x, y) -> heightMap[y][x] })) x to y else null
            }
        }
        .map { (x, y) -> heightMap.basin(x, y).size }
        .sorted()
        .takeLast(3)
        .product()
}
