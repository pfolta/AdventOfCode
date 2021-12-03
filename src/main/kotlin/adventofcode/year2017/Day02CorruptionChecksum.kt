package adventofcode.year2017

import adventofcode.Puzzle

class Day02CorruptionChecksum(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .lines()
        .map { it.split("\t").map(String::toInt) }
        .sumOf { (it.maxOrNull() ?: 0) - (it.minOrNull() ?: 0) }

    override fun partTwo() = input
        .lines()
        .map { it.split("\t").map(String::toInt) }
        .map { it.filter { a -> it.minus(a).any { b -> a % b == 0 || b % a == 0 } } }
        .sumOf { it.maxOrNull()!! / it.minOrNull()!! }
}
