package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.cartesianProduct
import adventofcode.common.spatial.Grid2d

class Day25CodeChronicle(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val schematics by lazy { input.split("\n\n").map { Grid2d(it) } }
    private val locks by lazy {
        schematics
            .filter { schematic -> schematic.rowAt(0).all { it == FILLED } && schematic.rowAt(schematic.rows - 1).all { it == EMPTY } }
            .map { schematic -> Grid2d(schematic.values.drop(1).dropLast(1)) }
            .toSet()
    }
    private val keys by lazy {
        schematics
            .map { schematic -> Grid2d(schematic.values.drop(1).dropLast(1)) }
            .minus(locks)
            .toSet()
    }

    override fun partOne() =
        listOf(locks, keys)
            .cartesianProduct()
            .map { (lock, key) -> lock.toHeights().zip(key.toHeights()) { lockHeight, keyHeight -> lockHeight + keyHeight } }
            .count { lockKeyPair -> lockKeyPair.all { height -> height <= 5 } }

    companion object {
        private const val FILLED = '#'
        private const val EMPTY = '.'

        private fun Grid2d<Char>.toHeights(): List<Int> = columns().map { column -> column.count { it == FILLED } }
    }
}
