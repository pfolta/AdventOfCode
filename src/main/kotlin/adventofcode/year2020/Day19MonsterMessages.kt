package adventofcode.year2020

import adventofcode.Day

private val NUMBER_REGEX = """\d+""".toRegex()

object Day19MonsterMessages : Day() {
    override fun partOne(): Int {
        val rules = input
            .split("\n\n")
            .first()
            .lines()
            .map { it.replace("\"", "") }
            .map { it.split(": ") }
            .map { it.first() to it.last() }
            .toMap()

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
            .zipWithNext()
            .takeWhile { regex -> regex.first.split(" ").any { it.contains(NUMBER_REGEX) } }
            .last()
            .second
            .replace(" ", "")
            .toRegex()

        return input.split("\n\n").last().lines().filter { rule0.matches(it) }.count()
    }

    override fun partTwo() {}
}
