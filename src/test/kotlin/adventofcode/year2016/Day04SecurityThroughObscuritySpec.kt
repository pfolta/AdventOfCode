package adventofcode.year2016

import adventofcode.PuzzleBaseSpec

class Day04SecurityThroughObscuritySpec :
    PuzzleBaseSpec(
        listOf(
            """
            aaaaa-bbb-z-y-x-123[abxyz]
            a-b-c-d-e-f-g-h-987[abcde]
            not-a-real-room-404[oarel]
            totally-real-room-200[decoy] 
            """.trimIndent() to 1514,
        ),
        listOf("rsvxltspi-sfnigx-wxsveki-984[sixve]" to 984),
    )
