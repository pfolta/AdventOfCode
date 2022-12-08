package adventofcode.year2016

import adventofcode.Puzzle

class Day06SignalsAndNoise(customInput: String? = null) : Puzzle(customInput) {
    override val name = "Signals and Noise"

    private val messages by lazy { input.lines() }

    override fun partOne() = messages.charPerColumnByComparator { charCount -> charCount.maxBy { (_, count) -> count } }.joinToString("")

    override fun partTwo() = messages.charPerColumnByComparator { charCount -> charCount.minBy { (_, count) -> count } }.joinToString("")

    companion object {
        private fun List<String>.charPerColumnByComparator(comparatorFun: (charCount: Map<Char, Int>) -> Map.Entry<Char, Int>) =
            List(first().length) { index -> map { message -> message[index] } }
                .map { column -> comparatorFun(column.groupingBy { it }.eachCount()).key }
    }
}
