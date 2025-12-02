package adventofcode.year2017

import adventofcode.PuzzleBaseSpec

class Day01InverseCaptchaSpec :
    PuzzleBaseSpec(
        listOf(
            "1122" to 3,
            "1111" to 4,
            "1234" to 0,
            "91212129" to 9,
        ),
        listOf(
            "1212" to 6,
            "1221" to 0,
            "123425" to 4,
            "123123" to 12,
            "12131415" to 4,
        ),
    )
