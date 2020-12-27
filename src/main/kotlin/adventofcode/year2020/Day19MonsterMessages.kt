package adventofcode.year2020

import adventofcode.Puzzle

class Day19MonsterMessages(customInput: String? = null) : Puzzle(customInput) {
    private val rules = input
        .split("\n\n")
        .first()
        .lines()
        .map { it.replace("\"", "") }
        .map { it.split(": ") }
        .map { it.first() to it.last() }
        .toMap()

    private val messages = input.split("\n\n").last().lines()

    override fun partOne(): Int {
        val rule0 = generateSequence(rules["0"]) { previous ->
            previous
                .split(" ")
                .joinToString(" ") {
                    when (val ref = NUMBER_REGEX.find(it)) {
                        null -> it
                        else -> it.replace(ref.value, "(${rules[ref.value]})")
                    }
                }
        }
            .first { regex -> regex.split(" ").none { it.contains(NUMBER_REGEX) } }
            .replace(" ", "")
            .toRegex()

        return messages.filter { rule0.matches(it) }.count()
    }

    companion object {
        private val NUMBER_REGEX = """\d+""".toRegex()
    }
}
