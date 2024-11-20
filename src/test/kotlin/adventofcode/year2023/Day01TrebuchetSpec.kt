package adventofcode.year2023

import adventofcode.PuzzleBaseSpec

class Day01TrebuchetSpec : PuzzleBaseSpec(
    listOf(
        """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
        """.trimIndent() to 142,
    ),
    listOf(
        """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
        """.trimIndent() to 281,
    ),
)
