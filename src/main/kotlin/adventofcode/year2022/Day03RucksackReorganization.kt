package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day03RucksackReorganization(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val rucksacks by lazy { input.lines() }

    override fun partOne() =
        rucksacks
            .map { rucksack ->
                listOf(
                    rucksack.substring(0 until rucksack.length / 2).toSet(),
                    rucksack.substring(rucksack.length / 2).toSet(),
                )
            }.flatMap { rucksack -> rucksack.reduce(Set<Char>::intersect) }
            .sumOf { item -> item.toPriority() }

    override fun partTwo() =
        rucksacks
            .chunked(3)
            .map { group -> group.map(String::toSet) }
            .flatMap { group -> group.reduce(Set<Char>::intersect) }
            .sumOf { item -> item.toPriority() }

    companion object {
        private fun Char.toPriority() = (('a'..'z') + ('A'..'Z')).indexOf(this) + 1
    }
}
