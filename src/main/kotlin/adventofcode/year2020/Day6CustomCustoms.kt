package adventofcode.year2020

import adventofcode.year2020.Day6CustomCustoms.part1
import adventofcode.year2020.Day6CustomCustoms.part2
import adventofcode.utils.readInputAsString

object Day6CustomCustoms {
    fun part1(customsGroups: List<String>) = customsGroups
        .map { it.replace("\n", "") }
        .map { it.toCharArray().distinct().count() }
        .sum()

    fun part2(customsGroups: List<String>) = customsGroups
        .map { it.split("\n").filter { it.isNotBlank() } }
        .map { group -> ('a'..'z').mapNotNull { question -> if (group.all { it.contains(question) }) question else null } }
        .map { it.size }
        .sum()
}

fun main() {
    val customsGroups = readInputAsString(2020, 6).split("\n\n")

    println("Part 1: ${part1(customsGroups)}")
    println("Part 2: ${part2(customsGroups)}")
}
