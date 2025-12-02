package adventofcode.year2015

import adventofcode.PuzzleBaseSpec

class Day03PerfectlySphericalHousesInAVacuumSpec :
    PuzzleBaseSpec(
        listOf(
            ">" to 2,
            "^>v<" to 4,
            "^v^v^v^v^v" to 2,
        ),
        listOf(
            "^v" to 3,
            "^>v<" to 3,
            "^v^v^v^v^v" to 11,
        ),
    )
