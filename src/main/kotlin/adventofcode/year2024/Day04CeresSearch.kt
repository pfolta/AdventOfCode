package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day04CeresSearch(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = WordSearch(input).countXmas()

    override fun partTwo() = WordSearch(input).countMasCrossed()

    companion object {
        private data class WordSearch(val grid: List<List<Char>>) {
            private val rows = grid.size
            private val cols = grid.first().size

            fun countXmas(): Int {
                val xmas = "XMAS"
                val length = xmas.length

                return (0 until rows).flatMap { row ->
                    (0 until cols).flatMap { col ->
                        val horizontal = (0 until length).map { y -> row to col + y }.filter { (_, y) -> y < cols }
                        val vertical = (0 until length).map { x -> row + x to col }.filter { (x, _) -> x < rows }
                        val rightDiagonal = (0 until length).map { d -> row + d to col + d }.filter { (x, y) -> x < rows && y < cols }
                        val leftDiagonal = (0 until length).map { d -> row + d to col - d }.filter { (x, y) -> x < rows && y >= 0 }

                        setOf(horizontal, vertical, rightDiagonal, leftDiagonal)
                            .filter { candidate -> candidate.size == length }
                            .map { coordinates -> coordinates.map { (x, y) -> grid[x][y] }.joinToString("") }
                    }
                }
                    .count { candidate -> candidate == xmas || candidate == xmas.reversed() }
            }

            fun countMasCrossed(): Int {
                val mas = "MAS"
                val length = mas.length

                return (0..rows - length).flatMap { row ->
                    (0..cols - length).map { col ->
                        val topDiagonal = (0 until length).map { d -> row + d to col + d }
                        val bottomDiagonal = (0 until length).map { d -> row + length - 1 - d to col + d }

                        setOf(topDiagonal, bottomDiagonal).map { coordinates -> coordinates.map { (x, y) -> grid[x][y] }.joinToString("") }
                    }
                }
                    .count { diagonals -> diagonals.all { diagonal -> diagonal == mas || diagonal == mas.reversed() } }
            }

            companion object {
                operator fun invoke(input: String) = WordSearch(input.lines().map { row -> row.toList() })
            }
        }
    }
}
