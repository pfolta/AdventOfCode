package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.common.Tuple.minus
import adventofcode.common.Tuple.plus
import adventofcode.year2022.Day09RopeBridge.Companion.Direction.D
import adventofcode.year2022.Day09RopeBridge.Companion.Direction.L
import adventofcode.year2022.Day09RopeBridge.Companion.Direction.R
import adventofcode.year2022.Day09RopeBridge.Companion.Direction.U
import kotlin.math.abs

class Day09RopeBridge(customInput: String? = null) : Puzzle(customInput) {
    private val motionMoves by lazy {
        input.lines().map { line -> line.split(" ") }.map { (direction, steps) -> MotionMove(Direction.valueOf(direction), steps.toInt()) }
    }

    override fun partOne() = motionMoves.simulate(2).second.count()

    override fun partTwo() = motionMoves.simulate(10).second.count()

    companion object {
        private enum class Direction { D, L, R, U }
        private data class MotionMove(val direction: Direction, val steps: Int)

        private fun List<MotionMove>.simulate(knotCount: Int) =
            fold(List(knotCount) { (0 to 0) } to setOf(0 to 0)) { (knots, tailVisited), (direction, steps) ->
                (1..steps).fold(knots to tailVisited) { (knots, tailVisited), _ ->
                    val newHead = knots.first().move(direction)

                    val newKnots = knots
                        .drop(1)
                        .fold(listOf(newHead)) { previousKnots, knot -> previousKnots + knot.track(previousKnots.last()) }

                    newKnots to tailVisited + newKnots.last()
                }
            }

        private fun Pair<Int, Int>.move(direction: Direction) = when (direction) {
            D -> this + (0 to -1)
            L -> this + (-1 to 0)
            R -> this + (1 to 0)
            U -> this + (0 to 1)
        }

        private fun Pair<Int, Int>.track(other: Pair<Int, Int>): Pair<Int, Int> {
            val (dx, dy) = other - this

            return when {
                // touching in the corners, no move
                abs(dx) == 1 && abs(dy) == 1 -> this

                // right, left, up, down moves
                dx == 0 && dy == 2 -> this + (0 to 1) // move up
                dx == 0 && dy == -2 -> this + (0 to -1) // move down
                dx == 2 && dy == 0 -> this + (1 to 0) // move right
                dx == -2 && dy == 0 -> this + (-1 to 0) // move left

                // diagonal moves
                dx > 0 && dy > 0 -> this + (1 to 1) // move up right
                dx > 0 && dy < 0 -> this + (1 to -1) // move down right
                dx < 0 && dy > 0 -> this + (-1 to 1) // move up left
                dx < 0 && dy < 0 -> this + (-1 to -1) // move down left

                // no move
                else -> this
            }
        }
    }
}
