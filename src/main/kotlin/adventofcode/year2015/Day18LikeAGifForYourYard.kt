package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.common.cartesianProduct

class Day18LikeAGifForYourYard(customInput: String? = null) : Puzzle(customInput) {
    override val name = "Like a GIF For Your Yard"

    override fun partOne(): Int {
        val initialConfiguration = input
            .lines()
            .flatMapIndexed { y, row -> row.mapIndexed { x, light -> Pair(x, y) to (light == TURNED_ON) } }
            .toMap()

        return generateSequence(initialConfiguration) { previous ->
            previous + previous.mapNotNull { (light, state) ->
                val turnedOnNeighbors = light.neighbors().count { previous[it]!! }

                if (state && listOf(2, 3).none { turnedOnNeighbors == it }) {
                    light to false
                } else if (!state && turnedOnNeighbors == 3) {
                    light to true
                } else {
                    null
                }
            }
        }
            .drop(1)
            .take(100)
            .last()
            .count { (_, state) -> state }
    }

    companion object {
        private const val TURNED_ON = '#'

        private const val GRID_HEIGHT = 100
        private const val GRID_WIDTH = 100

        fun Pair<Int, Int>.neighbors() = listOf((-1..1).toList(), (-1..1).toList())
            .cartesianProduct()
            .map { this + (it.first() to it.last()) }
            .minus(this)
            .filter { it.first >= 0 && it.second >= 0 && it.first < GRID_WIDTH && it.second < GRID_HEIGHT }

        operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = first + other.first to second + other.second
    }
}

