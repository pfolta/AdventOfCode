package adventofcode.year2020

import adventofcode.Puzzle

class Day02PasswordPhilosophy(puzzleInput: String? = null) : Puzzle(puzzleInput) {
    override fun partOne() = input
        .lines()
        .filter {
            val matchResults = PASSWORD_REGEX.find(it)!!
            val (min, max, char, password) = matchResults.destructured

            val charCount = password.toCharArray().filter { it.toString() == char }.count()

            charCount >= min.toInt() && charCount <= max.toInt()
        }
        .count()

    override fun partTwo() = input
        .lines()
        .filter {
            val matchResults = PASSWORD_REGEX.find(it)!!
            val (pos1s, pos2s, char, password) = matchResults.destructured

            val pos1 = pos1s.toInt() - 1
            val pos2 = pos2s.toInt() - 1

            ((password[pos1].toString() == char) && (password[pos2].toString() != char)) ||
                ((password[pos1].toString() != char) && (password[pos2].toString() == char))
        }
        .count()

    companion object {
        private val PASSWORD_REGEX = """([0-9]*)-([0-9]*) ([a-z]): (.*)""".toRegex()
    }
}
