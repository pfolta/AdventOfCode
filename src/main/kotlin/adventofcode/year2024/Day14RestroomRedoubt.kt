package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product
import adventofcode.common.spatial.Point2d

class Day14RestroomRedoubt(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val robots by lazy { input.lines().map(Robot::invoke) }

    override fun partOne(): Int {
        val robotCount =
            generateSequence(robots) { previous -> previous.map(Robot::move) }
                .drop(1)
                .take(SECONDS)
                .last()
                .groupingBy(Robot::position)
                .eachCount()

        val leftHalf = 0 until BATHROOM_WIDTH / 2
        val rightHalf = BATHROOM_WIDTH / 2 + 1 until BATHROOM_WIDTH
        val topHalf = 0 until BATHROOM_HEIGHT / 2
        val bottomHalf = BATHROOM_HEIGHT / 2 + 1 until BATHROOM_HEIGHT

        return setOf(
            leftHalf to topHalf,
            rightHalf to topHalf,
            leftHalf to bottomHalf,
            rightHalf to bottomHalf,
        )
            .map { (qx, qy) -> robotCount.filterKeys { point -> point.x in qx && point.y in qy }.values.sum() }
            .product()
    }

    companion object {
        private val ROBOT_REGEX = """p=(\d+),(\d+) v=(-?\d+),(-?\d+)""".toRegex()

        private const val SECONDS = 100
        private const val BATHROOM_WIDTH = 101
        private const val BATHROOM_HEIGHT = 103

        private data class Robot(
            val position: Point2d,
            val velocity: Point2d,
        ) {
            fun move(): Robot {
                val dx = position.x + velocity.x
                val dy = position.y + velocity.y

                val x =
                    when {
                        dx < 0 -> BATHROOM_WIDTH + dx
                        dx >= BATHROOM_WIDTH -> dx - BATHROOM_WIDTH
                        else -> dx
                    }

                val y =
                    when {
                        dy < 0 -> BATHROOM_HEIGHT + dy
                        dy >= BATHROOM_HEIGHT -> dy - BATHROOM_HEIGHT
                        else -> dy
                    }

                return Robot(Point2d(x, y), velocity)
            }

            companion object {
                operator fun invoke(input: String): Robot {
                    val (px, py, vx, vy) = ROBOT_REGEX.find(input)!!.destructured
                    return Robot(Point2d(px.toInt(), py.toInt()), Point2d(vx.toInt(), vy.toInt()))
                }
            }
        }
    }
}
