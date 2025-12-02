package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day10ElvesLookElvesSay(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    override val name = "Elves Look, Elves Say"

    override fun partOne() =
        generateSequence(input) { it.elfLookElfSay() }
            .drop(1)
            .take(40)
            .last()
            .length

    override fun partTwo() =
        generateSequence(input) { it.elfLookElfSay() }
            .drop(1)
            .take(50)
            .last()
            .length

    companion object {
        private fun String.elfLookElfSay(): String {
            val digitGroups = mutableListOf<Pair<Int, Int>>()

            map { it.toString().toInt() }.forEach { digit ->
                if (digitGroups.isNotEmpty() && digitGroups.last().second == digit) {
                    digitGroups[digitGroups.size - 1] = digitGroups.last().first + 1 to digit
                } else {
                    digitGroups.add(1 to digit)
                }
            }

            return digitGroups.flatMap { it.toList() }.joinToString("")
        }
    }
}
