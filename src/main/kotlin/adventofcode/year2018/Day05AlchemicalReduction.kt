package adventofcode.year2018

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day05AlchemicalReduction(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = generateSequence(input) { polymer ->
        polymer
            .windowed(2)
            .find { pair -> pair.first().equals(pair.last(), ignoreCase = true) && pair.first() != pair.last() }
            ?.let { pair -> polymer.substringBefore(pair) + polymer.substringAfter(pair) }
    }
        .last()
        .length
}
