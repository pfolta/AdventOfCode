package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day11CorporatePolicy(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = input.nextPassword()

    override fun partTwo() = input.nextPassword().nextPassword()

    companion object {
        private fun String.nextPassword() =
            generateSequence(this) { it.increment() }
                .drop(1)
                .filter { password -> ('a'..'x').any { password.contains("$it${it + 1}${it + 2}") } }
                .filter { password -> listOf('i', 'o', 'l').none { password.contains(it) } }
                .filter { password ->
                    val pairs = ('a'..'z').map { "$it$it" }
                    val firstPair = pairs.firstOrNull { password.contains(it) }

                    firstPair != null && pairs.minus(firstPair).any { password.contains(it) }
                }
                .first()

        private fun String.increment(): String {
            val chars = toMutableList()

            for (i in chars.size - 1 downTo 0) {
                if (chars[i] == 'z') {
                    chars[i] = 'a'
                } else {
                    chars[i]++
                    break
                }
            }

            return chars.joinToString("")
        }
    }
}
