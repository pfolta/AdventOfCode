package adventofcode.year2015

import adventofcode.Puzzle

object Day05DoesntHeHaveInternElvesForThis : Puzzle() {
    override fun partOne() = input
        .lines()
        .filter { str -> str.groupingBy { it }.eachCount().filter { setOf('a', 'e', 'i', 'o', 'u').contains(it.key) }.values.sum() >= 3 }
        .filter { str -> ('a'..'z').any { str.contains(it.toString().repeat(2)) } }
        .filter { str -> setOf("ab", "cd", "pq", "xy").none { str.contains(it) } }
        .size
}
