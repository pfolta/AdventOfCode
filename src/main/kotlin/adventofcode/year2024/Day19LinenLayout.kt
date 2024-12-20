package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day19LinenLayout(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val patterns by lazy { input.lines().first().split(", ") }

    private val designs by lazy { input.lines().drop(2) }

    override fun partOne() = designs.count { design -> design.patternCombinations(patterns) > 0 }

    companion object {
        private fun String.patternCombinations(
            patterns: List<String>,
            cache: MutableMap<String, Long> = mutableMapOf(),
        ): Long =
            when {
                isEmpty() -> 1
                else ->
                    cache.getOrPut(this) {
                        patterns.filter { pattern -> startsWith(pattern) }.sumOf { pattern ->
                            removePrefix(pattern).patternCombinations(patterns, cache)
                        }
                    }
            }
    }
}
