package adventofcode.year2022

import adventofcode.PuzzleBaseSpec

class Day09RopeBridgeSpec :
    PuzzleBaseSpec(
        listOf(
            """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
            """.trimIndent() to 13,
        ),
        listOf(
            """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
            """.trimIndent() to 1,
            """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
            """.trimIndent() to 36,
        ),
    )
