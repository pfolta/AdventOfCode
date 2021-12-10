package adventofcode.year2021

import adventofcode.Puzzle

class Day10SyntaxScoring(customInput: String? = null) : Puzzle(customInput) {
    private val lines by lazy { input.lines() }

    private val bracketMap = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>'
    )

    private val scoreMap = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )

    override fun partOne() = lines
        .mapNotNull { line ->
            val stack = ArrayDeque<Char>()

            line.forEach { char ->
                when (char) {
                    in setOf('(', '[', '{', '<') -> stack.add(char)
                    in setOf(')', ']', '}', '>') -> if (char != bracketMap[stack.removeLast()]) return@mapNotNull char
                }
            }

            null
        }
        .sumOf { char -> scoreMap[char]!! }
}
