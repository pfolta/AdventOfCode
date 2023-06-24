package adventofcode.year2020

import adventofcode.PuzzleBaseSpec

class Day15RambunctiousRecitationSpec : PuzzleBaseSpec(
    listOf(
        "1,3,2" to 1,
        "2,1,3" to 10,
        "1,2,3" to 27,
        "2,3,1" to 78,
        "3,2,1" to 438,
        "3,1,2" to 1836
    ) // ,
    // TODO: These tests work when run with JUnit but don't seem to terminate when run with Gradle
    // listOf(
    //     "0,3,6" to 175594,
    //     "1,3,2" to 2578,
    //     "2,1,3" to 3544142,
    //     "1,2,3" to 261214,
    //     "2,3,1" to 6895259,
    //     "3,2,1" to 18,
    //     "3,1,2" to 362
    // )
)
