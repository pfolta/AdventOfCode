package adventofcode.year2016

import adventofcode.Puzzle

class Day07InternetProtocolVersion7(customInput: String? = null) : Puzzle(customInput) {
    private val ipv7Addresses by lazy {
        input.lines().map { line -> line to HYPERNET_REGEX.findAll(line).map(MatchResult::value).toList() }
    }

    override fun partOne() = ipv7Addresses
        .count { (ipAddress, hypernets) -> ipAddress.containsAbba() && hypernets.none { hypernet -> hypernet.containsAbba() } }

    companion object {
        private val HYPERNET_REGEX = """\[(\w*)]""".toRegex()

        private fun String.containsAbba() = windowed(4).map(String::toCharArray).any { (a, b, c, d) -> (a != b && "$a$b" == "$d$c") }
    }
}
