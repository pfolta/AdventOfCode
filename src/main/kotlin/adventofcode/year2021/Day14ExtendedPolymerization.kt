package adventofcode.year2021

import adventofcode.Puzzle

class Day14ExtendedPolymerization(customInput: String? = null) : Puzzle(customInput) {
    private val polymerTemplate by lazy { input.lines().first() }

    private val pairInsertionRules by lazy { input.lines().drop(2).map { it.split(" -> ") }.associate { it.first() to it.last() } }

    override fun partOne(): Int {
        val elementCounts = generateSequence(polymerTemplate) { polymer ->
            polymer
                .windowed(2)
                .mapIndexed { index, pair ->
                    when (index) {
                        0 -> "${pair.first()}${pairInsertionRules[pair]}${pair.last()}"
                        else -> "${pairInsertionRules[pair]}${pair.last()}"
                    }
                }
                .joinToString("")
        }
            .drop(1)
            .take(10)
            .last()
            .groupingBy { it }
            .eachCount()
            .values
            .sorted()

        return elementCounts.last() - elementCounts.first()
    }
}
