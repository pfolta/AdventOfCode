package adventofcode.year2015

import adventofcode.Puzzle
import java.security.MessageDigest

class Day04TheIdealStockingStuffer(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = generateSequence(0) { it + 1 }.first { (input + it).md5().startsWith("00000") }

    override fun partTwo() = generateSequence(0) { it + 1 }.first { (input + it).md5().startsWith("000000") }

    companion object {
        private val digest = MessageDigest.getInstance("MD5")

        private fun String.md5() = digest.digest(toByteArray()).joinToString("") { "%02x".format(it) }
    }
}
