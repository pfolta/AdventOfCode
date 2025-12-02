package adventofcode.year2024

import adventofcode.PuzzleBaseSpec

class Day12GardenGroupsSpec :
    PuzzleBaseSpec(
        listOf(
            """
            AAAA
            BBCD
            BBCC
            EEEC
            """.trimIndent() to 140,
            """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
            """.trimIndent() to 772,
            """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """.trimIndent() to 1930,
        ),
        listOf(
            """
            AAAA
            BBCD
            BBCC
            EEEC
            """.trimIndent() to 80,
            """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
            """.trimIndent() to 436,
            """
            EEEEE
            EXXXX
            EEEEE
            EXXXX
            EEEEE
            """.trimIndent() to 236,
            """
            AAAAAA
            AAABBA
            AAABBA
            ABBAAA
            ABBAAA
            AAAAAA
            """.trimIndent() to 368,
            """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """.trimIndent() to 1206,
        ),
    )
