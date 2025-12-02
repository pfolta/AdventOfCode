package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day03BinaryDiagnostic(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val numbers by lazy { input.lines() }

    override fun partOne(): Int {
        val gammaRate =
            numbers
                .first()
                .indices
                .map { index -> mostCommonOrOne(numbers.countBy(index)) }
                .toInt(2)
        val epsilonRate =
            numbers
                .first()
                .indices
                .map { index -> leastCommonOrZero(numbers.countBy(index)) }
                .toInt(2)

        return gammaRate * epsilonRate
    }

    override fun partTwo(): Int {
        val oxygenGeneratorRating = findRating(::mostCommonOrOne)
        val co2ScrubberRating = findRating(::leastCommonOrZero)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    private fun findRating(bitCriteria: (Map<Char, Int>) -> Int): Int {
        val mutList = numbers.toMutableList()
        var index = 0

        while (mutList.size > 1) {
            val common = bitCriteria(mutList.countBy(index))
            mutList.removeAll { it[index].toString().toInt() != common }
            index++
        }

        return mutList.first().toInt(2)
    }

    companion object {
        private fun List<String>.countBy(index: Int) = groupingBy { it[index] }.eachCount()

        private fun mostCommonOrOne(eachCount: Map<Char, Int>) = if ((eachCount['1'] ?: 0) >= (eachCount['0'] ?: 0)) 1 else 0

        private fun leastCommonOrZero(eachCount: Map<Char, Int>) = if ((eachCount['0'] ?: 0) <= (eachCount['1'] ?: 0)) 0 else 1

        private fun List<Int>.toInt(radix: Int) = joinToString("").toInt(radix)
    }
}
