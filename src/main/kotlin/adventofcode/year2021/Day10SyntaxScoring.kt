package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day10SyntaxScoring(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val lines by lazy { input.lines() }

    private val bracketMap = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>'
    )

    private val scoreMapPartOne = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )

    private val scoreMapPartTwo = mapOf(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4
    )

    private fun List<Any>.middle() = this[size / 2]

    override fun partOne() = lines
        .mapNotNull { line ->
            val stack = ArrayDeque<Char>()

            line.forEach { char ->
                when (char) {
                    in setOf('(', '[', '{', '<') -> stack.add(char)
                    else -> if (char != bracketMap[stack.removeLast()]) return@mapNotNull char
                }
            }

            null
        }
        .sumOf { char -> scoreMapPartOne[char]!! }

    override fun partTwo() = lines
        .mapNotNull { line ->
            val stack = ArrayDeque<Char>()

            line.forEach { char ->
                when (char) {
                    in setOf('(', '[', '{', '<') -> stack.add(char)
                    else -> if (char != bracketMap[stack.removeLast()]) return@mapNotNull null
                }
            }

            stack.reversed().map { openingBracket -> bracketMap[openingBracket]!! }
        }
        .map { completion -> completion.fold(0L) { acc, char -> acc * 5 + scoreMapPartTwo[char]!! } }
        .sorted()
        .middle()
}
