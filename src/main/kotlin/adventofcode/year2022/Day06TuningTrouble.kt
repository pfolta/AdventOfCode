package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day06TuningTrouble(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private fun findCharacterProcessedCountForMarker(markerLength: Int) =
        input
            .windowed(markerLength)
            .indexOfFirst { chunk -> chunk.toSet().size == markerLength } + markerLength

    override fun partOne() = findCharacterProcessedCountForMarker(START_OF_PACKET_MARKER_LENGTH)

    override fun partTwo() = findCharacterProcessedCountForMarker(START_OF_MESSAGE_MARKER_LENGTH)

    companion object {
        private const val START_OF_PACKET_MARKER_LENGTH = 4
        private const val START_OF_MESSAGE_MARKER_LENGTH = 14
    }
}
