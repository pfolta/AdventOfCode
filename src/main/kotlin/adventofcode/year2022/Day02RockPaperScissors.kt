package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day02RockPaperScissors(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val strategies by lazy { input.lines().map { it.split(" ") }.map { it.first() to it.last() } }

    override fun partOne() =
        strategies
            .map { (opponent, me) ->
                when {
                    opponent == "A" && me == "X" -> 1 + 3
                    opponent == "A" && me == "Y" -> 2 + 6
                    opponent == "A" && me == "Z" -> 3 + 0
                    opponent == "B" && me == "X" -> 1 + 0
                    opponent == "B" && me == "Y" -> 2 + 3
                    opponent == "B" && me == "Z" -> 3 + 6
                    opponent == "C" && me == "X" -> 1 + 6
                    opponent == "C" && me == "Y" -> 2 + 0
                    opponent == "C" && me == "Z" -> 3 + 3
                    else -> throw IllegalArgumentException("'$opponent $me' is not a valid strategy")
                }
            }.sum()

    override fun partTwo() =
        strategies
            .map { (opponent, outcome) ->
                when {
                    opponent == "A" && outcome == "X" -> 3 + 0
                    opponent == "A" && outcome == "Y" -> 1 + 3
                    opponent == "A" && outcome == "Z" -> 2 + 6
                    opponent == "B" && outcome == "X" -> 1 + 0
                    opponent == "B" && outcome == "Y" -> 2 + 3
                    opponent == "B" && outcome == "Z" -> 3 + 6
                    opponent == "C" && outcome == "X" -> 2 + 0
                    opponent == "C" && outcome == "Y" -> 3 + 3
                    opponent == "C" && outcome == "Z" -> 1 + 6
                    else -> throw IllegalArgumentException("'$opponent $outcome' is not a valid strategy")
                }
            }.sum()
}
