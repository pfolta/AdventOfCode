package adventofcode.year2016

import adventofcode.Puzzle

class Day04SecurityThroughObscurity(customInput: String? = null) : Puzzle(customInput) {
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

    companion object {
        private val ROOM_REGEX = """([a-z-]+)-(\d+)\[([a-z]+)]""".toRegex()
    }
}
