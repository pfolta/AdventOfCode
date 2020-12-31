package adventofcode.year2018

import adventofcode.PuzzleBaseSpec

class Day01ChronalCalibrationSpec : PuzzleBaseSpec(
    listOf(
        """
            +1
            -2
            +3
            +1
        """.trimIndent() to 3,
        """
            +1
            +1
            +1
        """.trimIndent() to 3,
        """
            +1
            +1
            -2
        """.trimIndent() to 0,
        """
            -1
            -2
            -3
        """.trimIndent() to -6
    )
)
