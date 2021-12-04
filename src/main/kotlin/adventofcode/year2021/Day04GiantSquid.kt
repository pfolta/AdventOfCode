package adventofcode.year2021

import adventofcode.Puzzle

class Day04GiantSquid(customInput: String? = null) : Puzzle(customInput) {
    private val numbers by lazy { input.lines().first().split(",").map(String::toInt) }

    private val boards by lazy {
        input
            .split("\n\n")
            .drop(1)
            .map { board -> board.lines().map { row -> row.split(" ").filter(String::isNotBlank).map { Number(it.toInt()) } } }
            .map(::Board)
    }

    override fun partOne(): Int {
        val winningState =
            generateSequence(0 to boards.map { board -> board.markNumber(numbers.first()) }) { (previousIndex, previousBoards) ->
                previousIndex + 1 to previousBoards.map { board -> board.markNumber(numbers[previousIndex + 1]) }
            }
                .first { (_, boards) -> boards.any { board -> board.hasWon() } }

        val lastDrawnNumber = numbers[winningState.first]
        val winnerBoard = winningState.second.first { board -> board.hasWon() }

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
