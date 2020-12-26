package adventofcode.year2020

import adventofcode.PuzzleBaseSpec

class Day14DockingDataSpec : PuzzleBaseSpec(
    listOf(
        """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent() to 165
    ),
    listOf(
        """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent() to 208
    )
)
