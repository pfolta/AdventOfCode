package adventofcode.year2020

import adventofcode.Day

object Day06CustomCustoms : Day() {
    override fun partOne() = input
        .split("\n\n")
        .map { it.replace("\n", "") }
        .map { it.toCharArray().distinct().count() }
        .sum()

    override fun partTwo() = input
        .split("\n\n")
        .map { it.split("\n").filter(String::isNotBlank) }
        .map { group -> ('a'..'z').mapNotNull { question -> if (group.all { it.contains(question) }) question else null } }
        .map { it.size }
        .sum()
}
