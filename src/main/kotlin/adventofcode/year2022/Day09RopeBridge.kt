package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Tuple.minus
import adventofcode.common.Tuple.plus
import adventofcode.year2022.Day09RopeBridge.Companion.Direction.D
import adventofcode.year2022.Day09RopeBridge.Companion.Direction.L
import adventofcode.year2022.Day09RopeBridge.Companion.Direction.R
import adventofcode.year2022.Day09RopeBridge.Companion.Direction.U
import kotlin.math.abs
import kotlin.math.sign

class Day09RopeBridge(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val motionMoves by lazy {
        input.lines().map { line -> line.split(" ") }.map { (direction, steps) -> MotionMove(Direction.valueOf(direction), steps.toInt()) }
    }

    override fun partOne() = motionMoves.simulate(2).second.count()

    override fun partTwo() = motionMoves.simulate(10).second.count()

    companion object {
        private enum class Direction { D, L, R, U }

        private data class MotionMove(
            val direction: Direction,
            val steps: Int,
        )

        private fun List<MotionMove>.simulate(knotCount: Int) =
            fold(List(knotCount) { (0 to 0) } to setOf(0 to 0)) { (knots, tailVisited), (direction, steps) ->
                (1..steps).fold(knots to tailVisited) { (knots, tailVisited), _ ->
                    val newKnots =
                        knots
                            .drop(1)
                            .fold(listOf(knots.first().move(direction))) { knotList, knot -> knotList + knot.track(knotList.last()) }

                    newKnots to tailVisited + newKnots.last()
                }
            }

        private fun Pair<Int, Int>.move(direction: Direction) =
            when (direction) {
                D -> this + (0 to -1)
                L -> this + (-1 to 0)
                R -> this + (1 to 0)
                U -> this + (0 to 1)
            }

        private fun Pair<Int, Int>.track(other: Pair<Int, Int>): Pair<Int, Int> {
            val (dx, dy) = other - this

            return when {
                abs(dx) < 2 && abs(dy) < 2 -> this
                else -> this + (dx.sign to dy.sign)
            }
        }
    }
}
