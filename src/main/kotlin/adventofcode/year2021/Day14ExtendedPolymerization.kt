package adventofcode.year2021

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day14ExtendedPolymerization(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val polymerTemplate by lazy { input.lines().first() }

    private val pairInsertionRules by lazy {
        input
            .lines()
            .drop(2)
            .map { it.split(" -> ") }
            .associate { it.first() to it.last() }
    }

    override fun partOne(): Int {
        val elementCounts =
            generateSequence(polymerTemplate) { polymer ->
                polymer
                    .windowed(2)
                    .mapIndexed { index, pair ->
                        when (index) {
                            0 -> "${pair.first()}${pairInsertionRules[pair]}${pair.last()}"
                            else -> "${pairInsertionRules[pair]}${pair.last()}"
                        }
                    }.joinToString("")
            }.drop(1)
                .take(10)
                .last()
                .groupingBy { it }
                .eachCount()
                .values
                .sorted()

        return elementCounts.last() - elementCounts.first()
    }

    override fun partTwo(): Long {
        val elementCounts =
            generateSequence(polymerTemplate.windowed(2).groupingBy { it }.eachCount() as Map<String, Long>) { previous ->
                previous
                    .flatMap { (pair, count) ->
                        listOf(
                            "${pair.first()}${pairInsertionRules[pair]}" to count,
                            "${pairInsertionRules[pair]}${pair.last()}" to count,
                        )
                    }.fold(mapOf()) { acc, (pair, count) ->
                        if (acc.contains(pair)) {
                            val existingCount = acc[pair]!!
                            acc.minus(pair) + mapOf(pair to existingCount + count)
                        } else {
                            acc + mapOf(pair to count)
                        }
                    }
            }.drop(1)
                .take(40)
                .last()
                .toList()
                .fold(mapOf<Char, Long>()) { acc, (pair, count) ->
                    val last = pair.last()

                    if (acc.contains(last)) {
                        val existingCount = acc[last]!!
                        acc.minus(last) + mapOf(last to existingCount + count)
                    } else {
                        acc + mapOf(last to count)
                    }
                }.map { (element, count) -> if (element == polymerTemplate.first()) element to count + 1 else element to count }
                .map { (_, count) -> count }
                .sorted()

        return elementCounts.last() - elementCounts.first()
    }
}
