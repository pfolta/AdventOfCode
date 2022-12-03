package adventofcode.year2022

import adventofcode.Puzzle

class Day03RucksackReorganization(customInput: String? = null) : Puzzle(customInput) {
    private val rucksacks by lazy { input.lines() }

    override fun partOne() = rucksacks
        .map { rucksack -> rucksack.substring(0 until rucksack.length / 2) to rucksack.substring(rucksack.length / 2) }
        .map { rucksack -> rucksack.first.toSet() to rucksack.second.toSet() }
        .map { rucksack -> rucksack.first.intersect(rucksack.second).first().toChar() }
        .sumOf { item ->
            when (item) {
                in 'a'..'z' -> item.code - 96
                else -> item.code - 38
            }
        }
}
