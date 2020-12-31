package adventofcode.year2017

import adventofcode.PuzzleBaseSpec

class Day04HighEntropyPassphrasesSpec : PuzzleBaseSpec(
    listOf(
        "aa bb cc dd ee" to 1,
        "aa bb cc dd aa" to 0,
        "aa bb cc dd aaa" to 1
    ),
)
