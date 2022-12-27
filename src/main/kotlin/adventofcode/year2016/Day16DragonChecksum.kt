package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day16DragonChecksum(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = input.generateData().checksum()

    companion object {
        private const val DISK_LENGTH = 272

        private fun String.generateData() =
            generateSequence(this) { data -> data + '0' + data.reversed().map { char -> if (char == '0') '1' else '0' }.joinToString("") }
                .first { data -> data.length >= DISK_LENGTH }
                .take(DISK_LENGTH)

        private fun String.checksum() =
            generateSequence(this) { data ->
                data
                    .chunked(2)
                    .map { char -> if (char.first() == char.last()) '1' else '0' }
                    .joinToString("")
            }
                .drop(1)
                .first { checksum -> checksum.length % 2 == 1 }
    }
}
