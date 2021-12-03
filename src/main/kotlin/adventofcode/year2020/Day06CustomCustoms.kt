package adventofcode.year2020

import adventofcode.Puzzle

class Day06CustomCustoms(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .split("\n\n")
        .map { it.replace("\n", "") }
        .sumOf { it.toCharArray().distinct().count() }

    override fun partTwo() = input
        .split("\n\n")
        .map { it.split("\n").filter(String::isNotBlank) }
        .map { group -> ('a'..'z').mapNotNull { question -> if (group.all { it.contains(question) }) question else null } }
        .sumOf { it.size }
}
