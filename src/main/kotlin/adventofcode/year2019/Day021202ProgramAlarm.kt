package adventofcode.year2019

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day021202ProgramAlarm(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val intcode by lazy { input.split(",").map(String::toInt) }

    override fun partOne() =
        (listOf(intcode.first()) + listOf(12, 2) + intcode.subList(3, intcode.size))
            .toMutableList()
            .runProgram()

    override fun partTwo() =
        NOUN_RANGE
            .flatMap { noun ->
                VERB_RANGE.map { verb ->
                    Triple(noun, verb, (listOf(intcode.first()) + listOf(noun, verb) + intcode.subList(3, intcode.size)).toMutableList())
                }
            }
            .filter { it.third.runProgram() == 19690720 }
            .map { 100 * it.first + it.second }
            .first()

    companion object {
        private val NOUN_RANGE = 0..99
        private val VERB_RANGE = 0..99

        private fun MutableList<Int>.runProgram() =
            (indices step 4)
                .fold(first()) { _, index ->
                    when (this[index]) {
                        1 -> this[this[index + 3]] = this[this[index + 1]] + this[this[index + 2]]
                        2 -> this[this[index + 3]] = this[this[index + 1]] * this[this[index + 2]]
                        99 -> return first()
                    }
                    first()
                }
    }
}
