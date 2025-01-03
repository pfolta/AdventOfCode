package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.DOWN
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.LEFT
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.RIGHT
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.UP

class Day08TreetopTreeHouse(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val treeMap by lazy { input.lines().map { line -> line.map(Char::digitToInt) } }

    override fun partOne() =
        treeMap
            .flatMapIndexed { y, row ->
                row.mapIndexed { x, tree ->
                    Direction
                        .entries
                        .map { direction -> treeMap.neighbors(x, y, direction).filter { neighbor -> neighbor >= tree } }
                        .any(List<Int>::isEmpty)
                }
            }
            .count { tree -> tree }

    override fun partTwo() =
        treeMap
            .flatMapIndexed { y, row ->
                row.mapIndexed { x, tree ->
                    Direction
                        .entries
                        .map { direction ->
                            val neighbors = treeMap.neighbors(x, y, direction)

                            when (val tallestNeighbor = neighbors.indexOfFirst { neighbor -> neighbor >= tree }) {
                                -1 -> neighbors.size
                                else -> tallestNeighbor + 1
                            }
                        }
                }
            }
            .maxOf(List<Int>::product)

    companion object {
        private enum class Direction { DOWN, LEFT, RIGHT, UP }

        private fun List<List<Int>>.neighbors(
            x: Int,
            y: Int,
            direction: Direction,
        ) = when (direction) {
            DOWN -> map { row -> row[x] }.drop(y + 1)
            LEFT -> this[y].take(x).reversed()
            RIGHT -> this[y].drop(x + 1)
            UP -> map { row -> row[x] }.take(y).reversed()
        }
    }
}
