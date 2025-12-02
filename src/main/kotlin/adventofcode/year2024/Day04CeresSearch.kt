package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.spatial.Grid2d
import adventofcode.common.spatial.Point2d

class Day04CeresSearch(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val grid by lazy { Grid2d(input) }

    override fun partOne() = grid.countXmas()

    override fun partTwo() = grid.countMasCrossed()

    companion object {
        fun Grid2d<Char>.countXmas(): Int {
            val xmas = "XMAS"
            val length = xmas.length

            return (0 until rows)
                .flatMap { row ->
                    (0 until columnsInRow(row)).flatMap { col ->
                        val horizontal = (0 until length).map { y -> Point2d(row, col + y) }.filter { point -> point in this }
                        val vertical = (0 until length).map { x -> Point2d(row + x, col) }.filter { point -> point in this }
                        val rightDiagonal = (0 until length).map { d -> Point2d(row + d, col + d) }.filter { point -> point in this }
                        val leftDiagonal = (0 until length).map { d -> Point2d(row + d, col - d) }.filter { point -> point in this }

                        setOf(horizontal, vertical, rightDiagonal, leftDiagonal)
                            .filter { candidate -> candidate.size == length }
                            .map { points -> points.map { point -> this[point] }.joinToString("") }
                    }
                }.count { candidate -> candidate == xmas || candidate == xmas.reversed() }
        }

        fun Grid2d<Char>.countMasCrossed(): Int {
            val mas = "MAS"
            val length = mas.length

            return (0..rows - length)
                .flatMap { row ->
                    (0..columnsInRow(row) - length).map { col ->
                        val topDiagonal = (0 until length).map { d -> Point2d(row + d, col + d) }
                        val bottomDiagonal = (0 until length).map { d -> Point2d(row + length - 1 - d, col + d) }

                        setOf(topDiagonal, bottomDiagonal).map { points -> points.map { point -> this[point] }.joinToString("") }
                    }
                }.count { diagonals -> diagonals.all { diagonal -> diagonal == mas || diagonal == mas.reversed() } }
        }
    }
}
