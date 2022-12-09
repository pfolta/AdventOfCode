package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.common.cartesianProduct
import adventofcode.common.Tuple.plus

class Day18LikeAGifForYourYard(customInput: String? = null) : Puzzle(customInput) {
    override val name = "Like a GIF For Your Yard"

    private val initialConfiguration by lazy {
        input
            .lines()
            .flatMapIndexed { y, row -> row.mapIndexed { x, state -> Pair(x, y) to (state == TURNED_ON) } }
            .toMap()
    }

    override fun partOne() = initialConfiguration.animate()
        .drop(1)
        .take(STEP_COUNT)
        .last()
        .count { (_, turnedOn) -> turnedOn }

    override fun partTwo() = initialConfiguration.animate(setOf(Pair(0, 0), Pair(99, 0), Pair(0, 99), Pair(99, 99)))
        .drop(1)
        .take(STEP_COUNT)
        .last()
        .count { (_, turnedOn) -> turnedOn }

    companion object {
        private const val TURNED_ON = '#'

        private const val GRID_HEIGHT = 100
        private const val GRID_WIDTH = 100

        private const val STEP_COUNT = 100

        fun Map<Pair<Int, Int>, Boolean>.animate(stuckLights: Set<Pair<Int, Int>> = emptySet()) = generateSequence(this) { previous ->
            previous + previous.minus(stuckLights).mapNotNull { (light, turnedOn) ->
                val turnedOnNeighbors = light.neighbors().count { previous[it]!! }

                if (turnedOn && listOf(2, 3).none { turnedOnNeighbors == it }) {
                    light to false
                } else if (!turnedOn && turnedOnNeighbors == 3) {
                    light to true
                } else {
                    null
                }
            }
        }

        private fun Pair<Int, Int>.neighbors() = listOf((-1..1).toList(), (-1..1).toList())
            .cartesianProduct()
            .map { this + (it.first() to it.last()) }
            .minus(this)
            .filter { it.first >= 0 && it.second >= 0 && it.first < GRID_WIDTH && it.second < GRID_HEIGHT }
    }
}

