package adventofcode.year2018

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day05AlchemicalReduction(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = input.reducePolymer().length

    override fun partTwo() = ('a'..'z')
        .map { unit -> input.replace("""$unit|${unit.uppercaseChar()}""".toRegex(), "").reducePolymer() }
        .minOf(String::length)

    companion object {
        private fun String.reducePolymer() = ArrayDeque<Char>()
            .also { stack ->
                this.forEach { unit ->
                    when {
                        stack.isEmpty() -> stack.addLast(unit)
                        !stack.last().equals(unit, ignoreCase = true) || stack.last() == unit -> stack.addLast(unit)
                        else -> stack.removeLast()
                    }
                }
            }
            .joinToString("")
    }
}
