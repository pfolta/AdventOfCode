package adventofcode.year2017

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day05AMazeOfTwistyTrampolinesAllAlike(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    override val name = "A Maze of Twisty Trampolines, All Alike"

    private val offsets by lazy { input.lines().map(String::toInt) }

    override fun partOne() = offsets.toMutableList().countExitSteps { 1 }

    override fun partTwo() = offsets.toMutableList().countExitSteps { offset -> if (offset >= 3) -1 else 1 }

    companion object {
        private fun MutableList<Int>.countExitSteps(offsetChange: (offset: Int) -> Int): Int {
            var index = 0
            var steps = 0

            while (index < this.size) {
                val offset = this[index]
                this[index] = offset + offsetChange(offset)

                index += offset
                steps++
            }

            return steps
        }
    }
}
