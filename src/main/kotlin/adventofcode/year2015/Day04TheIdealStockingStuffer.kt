package adventofcode.year2015

import adventofcode.Puzzle
import java.security.MessageDigest

object Day04TheIdealStockingStuffer : Puzzle() {
    private val digest = MessageDigest.getInstance("MD5")

    private fun String.md5() = digest.digest(toByteArray()).joinToString("") { "%02x".format(it) }

    override fun partOne() = generateSequence(0) { it + 1 }.first { (input + it).md5().startsWith("00000") }

    override fun partTwo() = generateSequence(0) { it + 1 }.first { (input + it).md5().startsWith("000000") }
}
