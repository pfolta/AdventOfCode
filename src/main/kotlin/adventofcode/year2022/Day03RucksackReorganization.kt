package adventofcode.year2022

import adventofcode.Puzzle

class Day03RucksackReorganization(customInput: String? = null) : Puzzle(customInput) {
    private val rucksacks by lazy { input.lines() }

    override fun partOne() = rucksacks
        .map { rucksack ->
            listOf(
                rucksack.substring(0 until rucksack.length / 2).toSet(),
                rucksack.substring(rucksack.length / 2).toSet()
            )
        }
        .flatMap { rucksack -> rucksack.reduce(Set<Char>::intersect) }
        .sumOf { item -> item.toPriority() }

    override fun partTwo() = rucksacks
        .chunked(3)
        .map { group -> group.map(String::toSet) }
        .flatMap { group -> group.reduce(Set<Char>::intersect) }
        .sumOf { item -> item.toPriority() }

    companion object {
        fun Char.toPriority() = when (this) {
            in 'a'..'z' -> code - 96
            in 'A'..'Z' -> code - 38
            else -> throw IllegalArgumentException("'$this' is not a valid item type")
        }
    }
}
