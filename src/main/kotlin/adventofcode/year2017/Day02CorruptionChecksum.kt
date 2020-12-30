package adventofcode.year2017

import adventofcode.Puzzle

class Day02CorruptionChecksum(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .lines()
        .map { it.split("\t").map(String::toInt) }
        .map { (it.maxOrNull() ?: 0) - (it.minOrNull() ?: 0) }
        .sum()
}
