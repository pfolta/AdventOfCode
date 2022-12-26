package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.md5

class Day14OneTimePad(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "One-Time Pad"

    override fun partOne() = generateSequence(0, Int::inc)
        .filter { index -> isKey(input, index) }
        .take(64)
        .last()

    companion object {
        private fun String.hash(index: Int) = "$this$index".md5()

        private fun String.findFirstTriplet() = this
            .windowed(3)
            .find { triplet -> triplet.toSet().count() == 1 }
            ?.first()

        private fun isKey(salt: String, index: Int): Boolean {
            val hash = salt.hash(index)
            val triplet = hash.findFirstTriplet() ?: return false

            return ((index + 1 until index + 1000).any { salt.hash(it).contains(triplet.toString().repeat(5)) })
        }
    }
}
