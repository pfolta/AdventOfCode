package adventofcode.year2022

import adventofcode.Puzzle

class Day06TuningTrouble(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .withIndex()
        .windowed(START_OF_PACKET_MARKER_LENGTH)
        .first { chunk -> chunk.map { (_, value) -> value }.toSet().size == START_OF_PACKET_MARKER_LENGTH }
        .first()
        .index + START_OF_PACKET_MARKER_LENGTH

    companion object {
        private const val START_OF_PACKET_MARKER_LENGTH = 4
    }
}
