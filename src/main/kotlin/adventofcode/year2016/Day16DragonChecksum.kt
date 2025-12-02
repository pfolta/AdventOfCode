package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Math.isOdd

class Day16DragonChecksum(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = input.generateData(272).checksum()

    override fun partTwo() = input.generateData(35651584).checksum()

    companion object {
        private fun String.generateData(length: Int) =
            generateSequence(this) { data -> data + '0' + data.reversed().map { char -> if (char == '0') '1' else '0' }.joinToString("") }
                .first { data -> data.length >= length }
                .take(length)

        private fun String.checksum() =
            generateSequence(this) { data ->
                data
                    .chunked(2)
                    .map { char -> if (char.first() == char.last()) '1' else '0' }
                    .joinToString("")
            }
                .drop(1)
                .first { checksum -> checksum.length.isOdd() }
    }
}
