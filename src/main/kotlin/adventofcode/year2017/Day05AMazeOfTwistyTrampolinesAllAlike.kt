package adventofcode.year2017

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day05AMazeOfTwistyTrampolinesAllAlike(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "A Maze of Twisty Trampolines, All Alike"

    override fun partOne(): Int {
        val instructions = input.lines().map(String::toInt).toMutableList()
        var index = 0
        var steps = 0

        while (index < instructions.size) {
            index += instructions[index]++
            steps++
        }

        return steps
    }
}
