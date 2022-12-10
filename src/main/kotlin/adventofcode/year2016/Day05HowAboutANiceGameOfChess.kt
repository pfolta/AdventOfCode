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
        .first { (_, password) -> password.length == PASSWORD_LENGTH }
        .second

    override fun partTwo() = generateSequence(0 to emptyMap<Int, Char>()) { (index, password) ->
        val hash = "$input$index".md5()
        val prefix = hash.substring(0, 5)
        val position = hash[5].digitToIntOrNull()
        val character = hash[6]

        when {
            prefix != "00000" -> index + 1 to password
            position !in 0 until PASSWORD_LENGTH -> index + 1 to password
            password.containsKey(position) -> index + 1 to password
            else -> index + 1 to password + mapOf(position!! to character)
        }
    }
        .first { (_, password) -> password.size == PASSWORD_LENGTH }
        .second
        .toSortedMap()
        .values
        .joinToString("")

    companion object {
        private const val PASSWORD_LENGTH = 8
    }
}
