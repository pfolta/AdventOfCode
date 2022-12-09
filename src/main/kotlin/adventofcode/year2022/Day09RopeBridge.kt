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

    override fun partOne() = motionMoves
        .fold((0 to 0) to listOf(0 to 0)) { (head, tailList), (direction, steps) ->
            (1..steps).fold(head to tailList) { (head, tailList), _ ->
                val tail = tailList.last()

                val newHead = when (direction) {
                    D -> head + (0 to -1)
                    L -> head + (-1 to 0)
                    R -> head + (1 to 0)
                    U -> head + (0 to 1)
                }

                val (dx, dy) = newHead - tail

                val newTail = when {
                    // touching in the corners, no move
                    abs(dx) == 1 && abs(dy) == 1 -> tail

                    // right, left, up, down moves
                    dx == 0 && dy == 2 -> tail + (0 to 1) // move up
                    dx == 0 && dy == -2 -> tail + (0 to -1) // move down
                    dx == 2 && dy == 0 -> tail + (1 to 0) // move right
                    dx == -2 && dy == 0 -> tail + (-1 to 0) // move left

                    // diagonal moves
                    dx > 0 && dy > 0 -> tail + (1 to 1) // move up right
                    dx > 0 && dy < 0 -> tail + (1 to -1) // move down right
                    dx < 0 && dy > 0 -> tail + (-1 to 1) // move up left
                    dx < 0 && dy < 0 -> tail + (-1 to -1) // move down left

                    // no move
                    else -> tail
                }

                newHead to tailList.dropLast(1) + setOf(tail, newTail)
            }
        }
        .second
        .toSet()
        .count()

    companion object {
        private enum class Direction { D, L, R, U }
        private data class MotionMove(val direction: Direction, val steps: Int)
    }
}
