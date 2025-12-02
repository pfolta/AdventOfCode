package adventofcode.year2024

import adventofcode.PuzzleBaseSpec

class Day22MonkeyMarketSpec :
    PuzzleBaseSpec(
        listOf(
            """
            1
            10
            100
            2024
            """.trimIndent() to 37327623,
        ),
        listOf(
            """
            1
            2
            3
            2024
            """.trimIndent() to 23,
        ),
    )
