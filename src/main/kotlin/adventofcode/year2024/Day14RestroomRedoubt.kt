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

    override fun partTwo() =
        generateSequence(robots to 0) { (previous, seconds) -> previous.map(Robot::move) to seconds + 1 }
            .first { (robots) -> robots.groupingBy(Robot::position).eachCount().all { (_, count) -> count == 1 } }
            .second

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
                val x = (position.x + velocity.x).mod(BATHROOM_WIDTH)
                val y = (position.y + velocity.y).mod(BATHROOM_HEIGHT)

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
