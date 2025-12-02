package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Math.isEven

class Day09DiskFragmenter(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val fileMap by lazy {
        input
            .mapIndexed { index, size ->
                when (index.isEven()) {
                    true -> File(index / 2L, size.digitToInt())
                    false -> FreeSpace(size.digitToInt())
                }
            }
    }

    override fun partOne() = fileMap.flatMap((Block::expand)).moveFileBlocks().checksum()

    override fun partTwo() = fileMap.moveFileBlocks().checksum()

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

        private fun List<Block>.moveFileBlocks(): List<Block> {
            var result = this

            for (file in filterIsInstance<File>().reversed()) {
                val freeSpace = result.firstOrNull { block -> block is FreeSpace && block.size >= file.size }
                val freeSpaceIndex = result.indexOf(freeSpace)
                val fileIndex = result.indexOfLast { it == file }

                if (freeSpace != null && freeSpaceIndex < fileIndex) {
                    val freeSpaceRemainder =
                        if (freeSpace.size > file.size) {
                            listOf(FreeSpace(freeSpace.size - file.size))
                        } else {
                            emptyList()
                        }

                    result = result.subList(0, freeSpaceIndex) +
                        listOf(file) +
                        freeSpaceRemainder +
                        result.subList(freeSpaceIndex + 1, fileIndex) +
                        listOf(FreeSpace(file.size)) +
                        result.subList(fileIndex + 1, result.size)
                }
            }

            return result
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
