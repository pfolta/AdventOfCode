package adventofcode.year2020

import adventofcode.year2020.Day2PasswordPhilosophy.part1
import adventofcode.year2020.Day2PasswordPhilosophy.part2
import adventofcode.utils.readInputAsLines

object Day2PasswordPhilosophy {
    private val regex = """([0-9]*)-([0-9]*) ([a-z]): (.*)""".toRegex()

    fun part1(passwords: List<String>) = passwords.filter {
        val matchResults = regex.find(it)!!
        val (min, max, char, password) = matchResults.destructured

        val charCount = password.toCharArray().filter { it.toString() == char }.count()

        charCount >= min.toInt() && charCount <= max.toInt()
    }.count()

    fun part2(passwords: List<String>) = passwords.filter {
        val matchResults = regex.find(it)!!
        val (pos1s, pos2s, char, password) = matchResults.destructured

        val pos1 = pos1s.toInt() - 1
        val pos2 = pos2s.toInt() - 1

        ((password[pos1].toString() == char) && (password[pos2].toString() != char)) ||
            ((password[pos1].toString() != char) && (password[pos2].toString() == char))
    }.count()
}


fun main() {
    val passwords = readInputAsLines(2020, 2)

    println("Part 1: ${part1(passwords)}")
    println("Part 2: ${part2(passwords)}")
}
