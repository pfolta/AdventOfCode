package adventofcode.year2018

import adventofcode.PuzzleBaseSpec

class Day02InventoryManagementSystemSpec : PuzzleBaseSpec(
    listOf(
        """
        abcdef
        bababc
        abbcde
        abcccd
        aabcdd
        abcdee
        ababab
        """.trimIndent() to 12,
    ),
    listOf(
        """
        abcde
        fghij
        klmno
        pqrst
        fguij
        axcye
        wvxyz
        """.trimIndent() to "fgij",
    ),
)
