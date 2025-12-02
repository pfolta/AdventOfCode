package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day02PasswordPhilosophy(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    override fun partOne() =
        input
            .lines()
            .count {
                val matchResults = PASSWORD_REGEX.find(it)!!
                val (min, max, char, password) = matchResults.destructured

                val charCount = password.toCharArray().count { it.toString() == char }

                charCount >= min.toInt() && charCount <= max.toInt()
            }

    override fun partTwo() =
        input
            .lines()
            .count {
                val matchResults = PASSWORD_REGEX.find(it)!!
                val (pos1s, pos2s, char, password) = matchResults.destructured

                val pos1 = pos1s.toInt() - 1
                val pos2 = pos2s.toInt() - 1

                ((password[pos1].toString() == char) && (password[pos2].toString() != char)) ||
                    ((password[pos1].toString() != char) && (password[pos2].toString() == char))
            }

    companion object {
        private val PASSWORD_REGEX = """([0-9]*)-([0-9]*) ([a-z]): (.*)""".toRegex()
    }
}
