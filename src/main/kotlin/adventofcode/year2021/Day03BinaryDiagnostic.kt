package adventofcode.year2021

import adventofcode.Puzzle

class Day03BinaryDiagnostic(customInput: String? = null) : Puzzle(customInput) {
    private val numbers by lazy { input.lines() }

    private val ones by lazy { numbers.first().indices.map { index -> numbers.sumOf { it[index].toString().toInt() } } }
    private val zeros by lazy { ones.map { numbers.size - it } }

    override fun partOne(): Int {
        val gammaRate = ones.mapIndexed { index, _ -> if (ones[index] > zeros[index]) 1 else 0 }
        val epsilonRate = gammaRate.map { it.xor(1) }

        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    companion object {
        fun List<Int>.toInt(radix: Int) = joinToString("").toInt(radix)
    }
}
