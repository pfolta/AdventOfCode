package adventofcode.year2015

import adventofcode.PuzzleBaseSpec

class Day19MedicineForRudolphSpec :
    PuzzleBaseSpec(
        listOf(
            """
            H => HO
            H => OH
            O => HH
            
            HOH
            """.trimIndent() to 4,
            """
            H => HO
            H => OH
            O => HH
            
            HOHOHO
            """.trimIndent() to 7,
        ),
    )
