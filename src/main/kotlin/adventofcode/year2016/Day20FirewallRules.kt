package adventofcode.year2016

import adventofcode.Puzzle

class Day20FirewallRules(customInput: String? = null) : Puzzle(customInput) {
    private val rules by lazy {
        input
            .lines()
            .map { RULE_REGEX.find(it)?.destructured ?: throw IllegalArgumentException("'$it' is not a valid firewall rule") }
            .map { (from, to) -> from.toUInt()..to.toUInt() }
    }

    override fun partOne() = generateSequence(0.toUInt(), UInt::inc).first { ip -> rules.none { rule -> rule.contains(ip) } }

    companion object {
        private val RULE_REGEX = """(\d+)-(\d+)""".toRegex()
    }
}
