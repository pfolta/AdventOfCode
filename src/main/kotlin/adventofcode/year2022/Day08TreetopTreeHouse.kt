package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.DOWN
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.LEFT
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.RIGHT
import adventofcode.year2022.Day08TreetopTreeHouse.Companion.Direction.UP

class Day08TreetopTreeHouse(customInput: String? = null) : Puzzle(customInput) {
    private val treeMap by lazy { input.lines().map { line -> line.map(Char::digitToInt) } }

    override fun partOne() = treeMap
        .mapIndexed { y, row ->
            row.mapIndexed { x, tree ->
                val tallerUpNeighbors = treeMap.neighbors(x, y, UP).filter { neighbor -> neighbor >= tree }
                val tallerRightNeighbors = treeMap.neighbors(x, y, RIGHT).filter { neighbor -> neighbor >= tree }
                val tallerDownNeighbors = treeMap.neighbors(x, y, DOWN).filter { neighbor -> neighbor >= tree }
                val tallerLeftNeighbors = treeMap.neighbors(x, y, LEFT).filter { neighbor -> neighbor >= tree }

                setOf(tallerUpNeighbors, tallerRightNeighbors, tallerDownNeighbors, tallerLeftNeighbors).any(List<Int>::isEmpty)
            }
        }
        .flatten()
        .count { tree -> tree }

    companion object {
        private enum class Direction { UP, RIGHT, DOWN, LEFT }

        private fun List<List<Int>>.neighbors(x: Int, y: Int, direction: Direction) = when (direction) {
            UP -> map { row -> row[x] }.take(y)
            RIGHT -> get(y).drop(x + 1)
            DOWN -> map { row -> row[x] }.drop(y + 1)
            LEFT -> get(y).take(x)
        }
    }
}
