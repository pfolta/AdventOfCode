package adventofcode.year2021

import adventofcode.Puzzle

class Day08SevenSegmentSearch(customInput: String? = null) : Puzzle(customInput) {
    private val signalPatterns by lazy { input.lines().map { it.split("|").first().trim().split(" ") } }
    private val outputValues by lazy { input.lines().map { it.split("|").last().trim().split(" ") } }

    private val digitMap = mapOf(
        setOf('a', 'b', 'c', 'e', 'f', 'g') to 0,
        setOf('c', 'f') to 1,
        setOf('a', 'c', 'd', 'e', 'g') to 2,
        setOf('a', 'c', 'd', 'f', 'g') to 3,
        setOf('b', 'c', 'd', 'f') to 4,
        setOf('a', 'b', 'd', 'f', 'g') to 5,
        setOf('a', 'b', 'd', 'e', 'f', 'g') to 6,
        setOf('a', 'c', 'f') to 7,
        setOf('a', 'b', 'c', 'd', 'e', 'f', 'g') to 8,
        setOf('a', 'b', 'c', 'd', 'f', 'g') to 9
    )

    private fun List<String>.getUniqueDigit(digit: Int) = when (digit) {
        1 -> first { it.length == 2 }
        4 -> first { it.length == 4 }
        7 -> first { it.length == 3 }
        8 -> first { it.length == 7 }
        else -> throw IllegalArgumentException("$digit is not a unique digit")
    }.toSet()

    override fun partOne() = outputValues.map { entry -> entry.filter { it.length in listOf(2, 3, 4, 7) } }.sumOf(List<String>::size)

    override fun partTwo() = outputValues
        .mapIndexed { index, outputValue ->
            val signalPattern = signalPatterns[index]

            val a = signalPattern
                .getUniqueDigit(7)
                .minus(signalPattern.getUniqueDigit(1))
                .first()

            val g = signalPattern
                .filter { it.length == 6 }
                .map { it.toSet().minus(signalPattern.getUniqueDigit(4)).minus(signalPattern.getUniqueDigit(7)) }
                .first { it.size == 1 }
                .first()

            val e = signalPattern
                .getUniqueDigit(8)
                .minus(setOf(a, g))
                .minus(signalPattern.getUniqueDigit(4))
                .first()

            val b = signalPattern
                .filter { it.length == 6 }
                .map { it.toSet().minus(setOf(a, e, g)).minus(signalPattern.getUniqueDigit(1)) }
                .first { it.size == 1 }
                .first()

            val d = signalPattern
                .getUniqueDigit(8)
                .minus(setOf(a, b, e, g))
                .minus(signalPattern.getUniqueDigit(1))
                .first()

            val f = signalPattern
                .filter { it.length == 6 }
                .map { it.toSet().minus(setOf(a, b, d, e, g)) }
                .first { it.size == 1 }
                .first()

            val c = signalPattern
                .getUniqueDigit(8)
                .minus(setOf(a, b, d, e, f, g))
                .first()

            val wireSegmentMap = mapOf(
                a to 'a',
                b to 'b',
                c to 'c',
                d to 'd',
                e to 'e',
                f to 'f',
                g to 'g'
            )

            outputValue
                .map { digit -> digit.map { wireSegmentMap[it] }.toSet() }
                .map { digit -> digitMap[digit] }
                .joinToString("")
        }
        .sumOf(String::toInt)
}
