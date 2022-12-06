package adventofcode.year2022

import adventofcode.Puzzle

class Day06TuningTrouble(customInput: String? = null) : Puzzle(customInput) {
    private fun findCharacterProcessedCountForMarker(markerLength: Int) = input
        .windowed(markerLength)
        .indexOfFirst { chunk -> chunk.toSet().size == markerLength } + markerLength

    override fun partOne() = findCharacterProcessedCountForMarker(START_OF_PACKET_MARKER_LENGTH)

    override fun partTwo() = findCharacterProcessedCountForMarker(START_OF_MESSAGE_MARKER_LENGTH)

    companion object {
        private val START_OF_PACKET_MARKER_LENGTH = 4
        private val START_OF_MESSAGE_MARKER_LENGTH = 14
    }
}
