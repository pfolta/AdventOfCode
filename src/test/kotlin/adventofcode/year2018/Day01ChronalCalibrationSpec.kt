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
        """.trimIndent() to -6,
    ),
    listOf(
        """
        +1
        -2
        +3
        +1
        """.trimIndent() to 2,
        """
        +1
        -1
        """.trimIndent() to 0,
        """
        +3
        +3
        +4
        -2
        -4
        """.trimIndent() to 10,
        """
        -6
        +3
        +8
        +5
        -6
        """.trimIndent() to 5,
        """
        +7
        +7
        -2
        -7
        -4
        """.trimIndent() to 14,
    ),
)
