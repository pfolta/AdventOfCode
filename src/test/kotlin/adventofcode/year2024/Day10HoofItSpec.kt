package adventofcode.year2024

import adventofcode.PuzzleBaseSpec

class Day10HoofItSpec : PuzzleBaseSpec(
    listOf(
        """
        0123
        1234
        8765
        9876
        """.trimIndent() to 1,
        """
        ...0...
        ...1...
        ...2...
        6543456
        7.....7
        8.....8
        9.....9
        """.trimIndent() to 2,
        """
        ..90..9
        ...1.98
        ...2..7
        6543456
        765.987
        876....
        987....
        """.trimIndent() to 4,
        """
        10..9..
        2...8..
        3...7..
        4567654
        ...8..3
        ...9..2
        .....01
        """.trimIndent() to 3,
        """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
        """.trimIndent() to 36,
    ),
)
