package adventofcode.year2024

import adventofcode.PuzzleBaseSpec

class Day17ChronospatialComputerSpec : PuzzleBaseSpec(
    listOf(
        """
        Register A: 729
        Register B: 0
        Register C: 0

        Program: 0,1,5,4,3,0
        """.trimIndent() to "4,6,3,5,6,3,5,2,1,0",
    ),
    listOf(
        """
        Register A: 2024
        Register B: 0
        Register C: 0

        Program: 0,3,5,4,3,0
        """.trimIndent() to 117440,
    ),
)
