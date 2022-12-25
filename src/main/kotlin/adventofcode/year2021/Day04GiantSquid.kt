package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day04GiantSquid(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val numbers by lazy { input.lines().first().split(",").map(String::toInt) }

    private val boards by lazy {
        input
            .split("\n\n")
            .drop(1)
            .map { board -> board.lines().map { row -> row.split(" ").filter(String::isNotBlank).map { Number(it.toInt()) } } }
            .map(::Board)
    }

    private fun playBingo() =
        generateSequence(0 to boards.map { board -> board.markNumber(numbers.first()) }) { (previousIndex, previousBoards) ->
            previousIndex + 1 to previousBoards.map { board -> board.markNumber(numbers[previousIndex + 1]) }
        }
            .take(numbers.size)

    override fun partOne(): Int {
        val firstWinner = playBingo().first { (_, boards) -> boards.any { board -> board.hasWon() } }

        val lastDrawnNumber = numbers[firstWinner.first]
        val winnerBoard = firstWinner.second.first { board -> board.hasWon() }

        return lastDrawnNumber * winnerBoard.sumOfAllUnmarkedNumbers()
    }

    override fun partTwo(): Int {
        val beforeLastWinner = playBingo().last { (_, boards) -> boards.any { board -> !board.hasWon() } }

        val lastDrawnNumber = numbers[beforeLastWinner.first + 1]
        val winnerBoard = beforeLastWinner.second.first { board -> !board.hasWon() }.markNumber(lastDrawnNumber)

        return lastDrawnNumber * winnerBoard.sumOfAllUnmarkedNumbers()
    }

    companion object {
        data class Number(
            val value: Int,
            val marked: Boolean = false
        )

        data class Board(
            val numbers: List<List<Number>>
        ) {
            fun markNumber(number: Int) =
                copy(numbers = numbers.map { row -> row.map { if (it.value == number) Number(it.value, true) else it } })

            fun hasWon() = when {
                (numbers.any { row -> row.all { it.marked } }) -> true
                (numbers.first().indices.any { col -> numbers.all { row -> row[col].marked } }) -> true
                else -> false
            }

            fun sumOfAllUnmarkedNumbers() = numbers.flatten().filterNot { it.marked }.sumOf { it.value }
        }
    }
}
