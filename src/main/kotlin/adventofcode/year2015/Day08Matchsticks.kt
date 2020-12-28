package adventofcode.year2015

import adventofcode.Puzzle

class Day08Matchsticks(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .lines()
        .map { string ->
            val decodedString = string
                .substring(1 until string.length - 1)
                .replace("""\\x([0-9a-f]{2})""".toRegex()) { it.destructured.component1().toLong(16).toChar().toString() }
                .replace("""\\""", """\""")
                .replace("\\\"", "\"")

            string.length - decodedString.length
        }
        .sum()
}
