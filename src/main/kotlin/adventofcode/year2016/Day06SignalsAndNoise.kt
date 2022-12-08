package adventofcode.year2016

import adventofcode.Puzzle

class Day06SignalsAndNoise(customInput: String? = null) : Puzzle(customInput) {
    override val name = "Signals and Noise"

    private val messages by lazy { input.lines() }

    override fun partOne() = List(messages.first().length) { index -> messages.map { message -> message[index] } }
        .map { column -> column.groupingBy { it }.eachCount().maxBy { (_, count) -> count }.key }
        .joinToString("")
}
