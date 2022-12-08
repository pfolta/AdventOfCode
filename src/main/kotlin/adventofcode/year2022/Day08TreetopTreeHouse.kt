package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.common.product
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.DOWN
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.LEFT
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.RIGHT
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.UP

class Day08TreetopTreeHouse(customInput: String? = null) : Puzzle(customInput) {
    private val treeMap by lazy { input.lines().map { line -> line.map(Char::digitToInt) } }

    override fun partOne() = treeMap
        .mapIndexed { y, row ->
            row.mapIndexed { x, tree ->
                Direction
                    .values()
                    .map { direction -> treeMap.neighbors(x, y, direction).filter { neighbor -> neighbor >= tree } }
                    .any(List<Int>::isEmpty)
            }
        }
        .flatten()
        .count { tree -> tree }

    override fun partTwo() = treeMap
        .mapIndexed { y, row ->
            row.mapIndexed { x, tree ->
                Direction
                    .values()
                    .map { direction ->
                        val neighbors = treeMap.neighbors(x, y, direction)

                        val visibleNeighbors = when (val tallestNeighbor = neighbors.indexOfFirst { neighbor -> neighbor >= tree }) {
                            -1 -> neighbors
                            else -> neighbors.take(tallestNeighbor + 1)
                        }

                        visibleNeighbors.size
                    }
            }
        }
        .flatten()
        .maxOf(List<Int>::product)

    companion object {
        private enum class Direction { UP, RIGHT, DOWN, LEFT }

        private fun List<List<Int>>.neighbors(x: Int, y: Int, direction: Direction) = when (direction) {
            UP -> map { row -> row[x] }.take(y).reversed()
            RIGHT -> get(y).drop(x + 1)
            DOWN -> map { row -> row[x] }.drop(y + 1)
            LEFT -> get(y).take(x).reversed()
        }
    }
}
