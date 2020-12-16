package adventofcode.year2020

import adventofcode.year2020.Day7HandyHaversacks.part1
import adventofcode.year2020.Day7HandyHaversacks.part2
import adventofcode.utils.readInputAsLines

data class Bag(
    val color: String,
    val contents: Map<String, Int>
) {
    fun contains(bagRules: List<Bag>, searchPattern: String): Boolean {
        if (contents.isEmpty()) return false
        if (contents.containsKey(searchPattern)) return true

        return contents
            .map { bagRules.get(it.key).contains(bagRules, searchPattern) }
            .contains(true)
    }

    fun size(bagRules: List<Bag>): Int {
        if (contents.isEmpty()) return 1

        return contents
            .map { bagRules.get(it.key).size(bagRules) * it.value }
            .sum()
            .plus(1)
    }
}

fun List<Bag>.get(color: String) = this.first { it.color == color }

object Day7HandyHaversacks {
    fun part1(bagRules: List<Bag>, searchPattern: String) = bagRules
        .map { it.contains(bagRules, searchPattern) }
        .count()

    fun part2(bagRules: List<Bag>, searchPattern: String) = bagRules
        .get(searchPattern)
        .size(bagRules)
        .minus(1)
}

private val BAG_RULE_REGEX = """(\w+ \w+) bags contain (.*)""".toRegex()
private val BAG_CONTENTS_REGEX = """(\d+) (\w+ \w+) bags?(, )?""".toRegex()

fun main() {
    val bagRules = readInputAsLines(2020, 7)
        .map { rule ->
            val (color) = BAG_RULE_REGEX.find(rule)!!.destructured

            val contents = BAG_CONTENTS_REGEX
                .findAll(rule)
                .toList()
                .map { it.destructured.component2() to it.destructured.component1().toInt() }
                .toMap()

            Bag(color, contents)
        }

    println("Part 1: ${part1(bagRules, "shiny gold")}")
    println("Part 2: ${part2(bagRules, "shiny gold")}")
}
