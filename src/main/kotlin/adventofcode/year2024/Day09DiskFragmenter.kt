package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.swap

class Day09DiskFragmenter(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne(): Long {
        val fileMap =
            input
                .flatMapIndexed { index, size ->
                    val id = if (index % 2 == 0) (index / 2).toString() else "."
                    (0 until size.digitToInt()).map { Block(id) }
                }

        val compactedFileMap =
            generateSequence(fileMap) { map ->
                map.swap(map.indexOfFirst { it is FreeSpace }, map.indexOfLast { it is File })
            }
                .first { map -> map.indexOfFirst { it is FreeSpace } > map.indexOfLast { it is File } }

        return compactedFileMap
            .mapIndexed { index, block -> index to block }
            .sumOf { (index, block) ->
                when (block) {
                    is File -> index * block.id
                    is FreeSpace -> 0
                }
            }
    }

    companion object {
        sealed class Block {
            companion object {
                operator fun invoke(id: String) = if (id == ".") FreeSpace else File(id.toLong())
            }
        }

        data class File(val id: Long) : Block() {
            override fun toString() = id.toString()
        }

        data object FreeSpace : Block() {
            override fun toString() = "."
        }
    }
}
