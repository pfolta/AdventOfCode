package adventofcode.year2017

import adventofcode.PuzzleBaseSpec

class Day04HighEntropyPassphrasesSpec : PuzzleBaseSpec(
    listOf(
        "aa bb cc dd ee" to 1,
        "aa bb cc dd aa" to 0,
        "aa bb cc dd aaa" to 1,
    ),
    listOf(
        "abcde fghij" to 1,
        "abcde xyz ecdab" to 0,
        "a ab abc abd abf abj" to 1,
        "iiii oiii ooii oooi oooo" to 1,
        "oiii ioii iioi iiio" to 0,
    ),
)
