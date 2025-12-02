package adventofcode.year2015

import adventofcode.PuzzleBaseSpec

class Day06ProbablyAFireHazardSpec :
    PuzzleBaseSpec(
        listOf(
            "turn on 0,0 through 999,999" to 1_000_000,
            """
            turn on 0,0 through 999,999
            toggle 0,0 through 999,0
            """.trimIndent() to 999_000,
            """
            turn on 0,0 through 999,999
            turn off 499,499 through 500,500
            """.trimIndent() to 999_996,
        ),
        listOf(
            "turn on 0,0 through 0,0" to 1,
            "toggle 0,0 through 999,999" to 2_000_000,
        ),
    )
