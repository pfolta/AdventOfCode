package adventofcode.year2020

import adventofcode.Puzzle

private val BAG_RULE_REGEX = """(\w+ \w+) bags contain (.*)""".toRegex()
private val BAG_CONTENTS_REGEX = """(\d+) (\w+ \w+) bags?(, )?""".toRegex()

object Day07HandyHaversacks : Puzzle() {
    private val bagRules = input.lines().map { rule ->
        val (color) = BAG_RULE_REGEX.find(rule)!!.destructured

        val contents = BAG_CONTENTS_REGEX
            .findAll(rule)
            .toList()
            .map { it.destructured.component2() to it.destructured.component1().toInt() }
            .toMap()

        Bag(color, contents)
    }

    override fun partOne() = bagRules
        .filter { it.contains(bagRules, "shiny gold") }
        .count()

    override fun partTwo() = bagRules
        .get("shiny gold")
        .size(bagRules)
        .minus(1)
}

private data class Bag(
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

private fun List<Bag>.get(color: String) = this.first { it.color == color }
