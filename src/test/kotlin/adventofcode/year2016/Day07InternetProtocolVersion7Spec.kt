package adventofcode.year2016

import adventofcode.PuzzleBaseSpec

class Day07InternetProtocolVersion7Spec :
    PuzzleBaseSpec(
        listOf(
            """
            abba[mnop]qrst
            abcd[bddb]xyyx
            aaaa[qwer]tyui
            ioxxoj[asdfgh]zxcvbn
            """.trimIndent() to 2,
        ),
        listOf(
            """
            aba[bab]xyz
            xyx[xyx]xyx
            aaa[kek]eke
            zazbz[bzb]cdb
            """.trimIndent() to 3,
        ),
    )
