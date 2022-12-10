package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.common.product

class Day03TobogganTrajectory(customInput: String? = null) : Puzzle(customInput) {
    private val treeMap by lazy { input.lines() }

    override fun partOne() = treeMap.countTrees(Slope(3, 1))

    override fun partTwo() = listOf(Slope(1, 1), Slope(3, 1), Slope(5, 1), Slope(7, 1), Slope(1, 2))
        .map { treeMap.countTrees(it) }
        .product()

    companion object {
        private data class Slope(val dx: Long, val dy: Long)

        private fun List<String>.countTrees(slope: Slope): Long {
            var counter = 0L
            var x = 0L
            var y = 0L

            while (y < size) {
                val currentLine = this[y.toInt()].toCharArray()

                val field = currentLine[x.toInt() % currentLine.size]

                if (field == '#') counter++

                x += slope.dx
                y += slope.dy
            }

            return counter
        }
    }
}
