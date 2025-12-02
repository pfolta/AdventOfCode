package adventofcode.year2017

import adventofcode.PuzzleBaseSpec

class Day02CorruptionChecksumSpec :
    PuzzleBaseSpec(
        listOf(
            """
            5	1	9	5
            7	5	3
            2	4	6	8
            """.trimIndent() to 18,
        ),
        listOf(
            """
            5	9	2	8
            9	4	7	3
            3	8	6	5
            """.trimIndent() to 9,
        ),
    )
