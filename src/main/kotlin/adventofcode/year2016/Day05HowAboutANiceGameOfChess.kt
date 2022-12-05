package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.common.md5

class Day05HowAboutANiceGameOfChess(customInput: String? = null) : Puzzle(customInput) {
    override val name = "How About a Nice Game of Chess?"

    override fun partOne() = generateSequence(0 to "") { (index, password) ->
        val hash = "$input$index".md5()

        when (hash.substring(0, 5)) {
            "00000" -> index + 1 to "$password${hash[5]}"
            else -> index + 1 to password
        }
    }
        .first { (_, password) -> password.length == 8 }
        .second
}
