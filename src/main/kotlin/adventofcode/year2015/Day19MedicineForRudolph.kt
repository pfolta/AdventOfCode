package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day19MedicineForRudolph(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Medicine for Rudolph"

    private val replacements by lazy {
        input
            .split("\n\n")
            .first()
            .lines()
            .map {
                val (source, replacement) = it.split(" => ")
                source to replacement
            }
    }

    private val molecule by lazy { input.split("\n\n").last() }

    override fun partOne() = replacements
        .flatMap { (source, replacement) -> Regex(source).findAll(molecule).map { molecule.replaceRange(it.range, replacement) } }
        .toSet()
        .size
}
