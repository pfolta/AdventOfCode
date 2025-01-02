package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.cartesianProduct
import adventofcode.common.spatial.Grid2d

class Day25CodeChronicle(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() =
        input
            .split("\n\n")
            .map { Grid2d(it) }
            .partition { schematic -> schematic.rows().first().all { it == FILLED } }
            .toList()
            .map { it.map { schematic -> Grid2d(schematic.values.drop(1).dropLast(1)) } }
            .cartesianProduct()
            .map { (lock, key) -> lock.toHeights().zip(key.toHeights()) { lockHeight, keyHeight -> lockHeight + keyHeight } }
            .count { lockKeyPair -> lockKeyPair.all { height -> height <= 5 } }

    companion object {
        private const val FILLED = '#'

        private fun Grid2d<Char>.toHeights(): List<Int> = columns().map { column -> column.count { it == FILLED } }
    }
}
