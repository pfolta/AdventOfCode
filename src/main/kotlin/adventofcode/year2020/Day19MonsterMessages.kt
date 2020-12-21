package adventofcode.year2020

import adventofcode.utils.readInputAsString
import adventofcode.year2020.Day19MonsterMessages.part1
import adventofcode.year2020.Day19MonsterMessages.part2

object Day19MonsterMessages {
    private val NUMBER_REG_EX = """\d+""".toRegex()

    fun part1(input: String): Int {
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
                    when (val ref = NUMBER_REG_EX.find(it)) {
                        null -> it
                        else -> it.replace(ref.value, "(${rules[ref.value]})")
                    }
                }
        }
            .zipWithNext()
            .takeWhile { regex -> regex.first.split(" ").any { it.contains(NUMBER_REG_EX) } }
            .last()
            .second
            .replace(" ", "")
            .toRegex()

        return input.split("\n\n").last().lines().filter { rule0.matches(it) }.count()
    }

    fun part2(input: String) {

    }
}

fun main() {
    val input = readInputAsString(2020, 19)

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
