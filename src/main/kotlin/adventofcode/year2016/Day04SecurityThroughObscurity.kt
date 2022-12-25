package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day04SecurityThroughObscurity(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val rooms by lazy {
        input
            .lines()
            .map { room -> ROOM_REGEX.find(room)!!.destructured }
            .map { (name, sectorId, checksum) -> Triple(name, sectorId.toInt(), checksum) }
    }

    override fun partOne() = rooms
        .map { (name, sectorId, checksum) -> Triple(name.filterNot { it == '-' }.groupingBy { it }.eachCount(), sectorId, checksum) }
        .filter { (letterCount, _, checksum) ->
            val computedChecksum = letterCount
                .toList()
                .sortedBy { (letter, _) -> letter }
                .sortedByDescending { (_, count) -> count }
                .take(5)
                .joinToString("") { (letter, _) -> letter.toString() }

            computedChecksum == checksum
        }
        .sumOf { (_, sectorId, _) -> sectorId }

    override fun partTwo() = rooms
        .map { (name, sectorId, _) ->
            name.map { char ->
                when (char) {
                    '-' -> ' '
                    else -> ((char.code - 'a'.code + sectorId) % 26 + 'a'.code).toChar()
                }
            }.joinToString("") to sectorId
        }
        .first { (name, _) -> name == NORTHPOLE_OBJECT_STORAGE_ROOM }
        .second

    companion object {
        private val ROOM_REGEX = """([a-z-]+)-(\d+)\[([a-z]{5})]""".toRegex()
        private const val NORTHPOLE_OBJECT_STORAGE_ROOM = "northpole object storage"
    }
}
