package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day08SevenSegmentSearch(customInput: PuzzleInput? = null) : Puzzle(customInput) {
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

    private fun List<String>.getPatternsForDigit(digit: Int) = when (digit) {
        0 -> filter { it.length == 6 }
        1 -> filter { it.length == 2 }
        2 -> filter { it.length == 5 }
        3 -> filter { it.length == 5 }
        4 -> filter { it.length == 4 }
        5 -> filter { it.length == 5 }
        6 -> filter { it.length == 6 }
        7 -> filter { it.length == 3 }
        8 -> filter { it.length == 7 }
        9 -> filter { it.length == 6 }
        else -> throw IllegalArgumentException("$digit is not a unique digit")
    }.map(String::toSet)

    override fun partOne() = outputValues.map { entry -> entry.filter { it.length in listOf(2, 3, 4, 7) } }.sumOf(List<String>::size)

    override fun partTwo() = outputValues
        .mapIndexed { index, outputValue ->
            val signalPattern = signalPatterns[index]

            val a = signalPattern
                .getPatternsForDigit(7)
                .first()
                .minus(signalPattern.getPatternsForDigit(1).first())
                .first()

            val g = signalPattern
                .getPatternsForDigit(9)
                .map { it.toSet().minus(signalPattern.getPatternsForDigit(4).first()).minus(signalPattern.getPatternsForDigit(7).first()) }
                .first { it.size == 1 }
                .first()

            val e = signalPattern
                .getPatternsForDigit(8)
                .first()
                .minus(setOf(a, g))
                .minus(signalPattern.getPatternsForDigit(4).first())
                .first()

            val b = signalPattern
                .getPatternsForDigit(6)
                .map { it.toSet().minus(setOf(a, e, g)).minus(signalPattern.getPatternsForDigit(1).first()) }
                .first { it.size == 1 }
                .first()

            val d = signalPattern
                .getPatternsForDigit(8)
                .first()
                .minus(setOf(a, b, e, g))
                .minus(signalPattern.getPatternsForDigit(1).first())
                .first()

            val f = signalPattern
                .getPatternsForDigit(0)
                .map { it.toSet().minus(setOf(a, b, d, e, g)) }
                .first { it.size == 1 }
                .first()

            val c = signalPattern
                .getPatternsForDigit(8)
                .first()
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
