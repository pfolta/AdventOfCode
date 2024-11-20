package adventofcode.year2015

import adventofcode.PuzzleBaseSpec

class Day01NotQuiteLispSpec : PuzzleBaseSpec(
    listOf(
        "(())" to 0,
        "()()" to 0,
        "(((" to 3,
        "(()(()(" to 3,
        "))(((((" to 3,
        "())" to -1,
        "))(" to -1,
        ")))" to -3,
        ")())())" to -3,
    ),
    listOf(
        ")" to 1,
        "()())" to 5,
    ),
)
