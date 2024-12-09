package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.swap

class Day09DiskFragmenter(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val fileMap by lazy {
        input
            .mapIndexed { index, size ->
                when (index % 2) {
                    0 -> File(index / 2L, size.digitToInt())
                    else -> FreeSpace(size.digitToInt())
                }
            }
    }

    override fun partOne() =
        generateSequence(fileMap.flatMap(Block::expand)) { map ->
            map.swap(map.indexOfFirst { it is FreeSpace }, map.indexOfLast { it is File })
        }
            .first { map -> map.indexOfFirst { it is FreeSpace } > map.indexOfLast { it is File } }
            .checksum()

    override fun partTwo(): Long {
        var result = fileMap

        for (file in fileMap.filterIsInstance<File>().reversed()) {
            val freeSpace = result.firstOrNull { block -> block is FreeSpace && block.size >= file.size }

            if (freeSpace != null && result.indexOf(freeSpace) < result.indexOf(file)) {
                val freeSpaceRemainder =
                    if (freeSpace.size > file.size) {
                        listOf(FreeSpace(freeSpace.size - file.size))
                    } else {
                        emptyList()
                    }

                val remainder =
                    if (result.indexOf(file) + 1 <= result.size) {
                        result.subList(result.indexOf(file) + 1, result.size)
                    } else {
                        emptyList()
                    }

                result = result.subList(0, result.indexOf(freeSpace)) +
                    listOf(file) +
                    freeSpaceRemainder +
                    result.subList(result.indexOf(freeSpace) + 1, result.indexOf(file)) +
                    listOf(FreeSpace(file.size)) +
                    remainder
            }
        }

        return result.checksum()
    }

    companion object {
        private sealed class Block {
            abstract val size: Int

            abstract fun expand(): List<Block>
        }

        private data class File(val id: Long, override val size: Int) : Block() {
            override fun expand() = (0 until size).map { File(id, 1) }
        }

        private data class FreeSpace(override val size: Int) : Block() {
            override fun expand() = (0 until size).map { FreeSpace(1) }
        }

        private fun List<Block>.checksum() =
            flatMap(Block::expand)
                .mapIndexed { index, block -> index to block }
                .sumOf { (index, block) ->
                    when (block) {
                        is File -> index * block.id
                        is FreeSpace -> 0
                    }
                }
    }
}
