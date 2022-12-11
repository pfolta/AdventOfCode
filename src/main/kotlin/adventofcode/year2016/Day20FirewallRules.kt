package adventofcode.year2016

import adventofcode.Puzzle
import kotlin.math.max

class Day20FirewallRules(customInput: String? = null) : Puzzle(customInput) {
    private val rules by lazy {
        input
            .lines()
            .map { rule -> rule.split("-") }
            .map { (from, to) -> from.toUInt()..to.toUInt() }
            .sortedBy { rule -> rule.first }
            .fold(emptyList<UIntRange>()) { rules, rule ->
                when {
                    rules.isEmpty() -> listOf(rule)
                    rule.first - 1U <= rules.last().last -> rules.dropLast(1) + listOf(rules.last().first..max(rules.last().last, rule.last))
                    else -> rules + listOf(rule)
                }
            }
    }

    override fun partOne() = rules.first().last + 1U

    override fun partTwo() = UInt.MAX_VALUE - rules.sumOf { rule -> rule.last - rule.first + 1U } + 1U
}
