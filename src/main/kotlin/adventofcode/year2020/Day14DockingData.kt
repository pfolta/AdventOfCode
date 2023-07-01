package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.replaceAt

class Day14DockingData(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val initializationInstructions by lazy { input.lines().map(InitializationInstruction::invoke) }

    override fun partOne() = initializationInstructions
        .fold(Pair("X".repeat(36), emptyMap<Long, Long>())) { (mask, memoryMap), instruction ->
            when (instruction) {
                is MaskInstruction -> Pair(instruction.mask, memoryMap)
                is MemoryInstruction -> Pair(
                    mask,
                    memoryMap + mapOf(
                        instruction.address to (mask.indices)
                            .fold(instruction.value.toString(2).padStart(mask.length, '0')) { result, index ->
                                if (mask[index] == 'X') result else result.replaceAt(index, mask[index])
                            }.toLong(2)
                    )
                )
            }
        }.second.values.sum()

    override fun partTwo() = initializationInstructions
        .fold(Pair("0".repeat(36), emptyMap<Long, Long>())) { (mask, memoryMap), instruction ->
            when (instruction) {
                is MaskInstruction -> Pair(instruction.mask, memoryMap)
                is MemoryInstruction -> {
                    val addressMask = (mask.indices)
                        .fold(instruction.address.toString(2).padStart(mask.length, '0')) { result, index ->
                            if (mask[index] == '0') result else result.replaceAt(index, mask[index])
                        }

                    Pair(
                        mask,
                        memoryMap + (addressMask.indices)
                            .fold(listOf(addressMask)) { acc, index ->
                                when {
                                    addressMask[index] != 'X' -> acc
                                    else -> acc.flatMap { listOf(it.replaceAt(index, '0'), it.replaceAt(index, '1')) }
                                }
                            }
                            .map { it.toLong(2) to instruction.value }
                    )
                }
            }
        }.second.values.sum()

    companion object {
        private sealed class InitializationInstruction {
            companion object {
                operator fun invoke(input: String): InitializationInstruction {
                    val parts = input.split(" = ")

                    return when (parts.first()) {
                        "mask" -> MaskInstruction(parts.last())
                        else -> MemoryInstruction(parts.first().substring(4 until parts.first().length - 1).toLong(), parts.last().toLong())
                    }
                }
            }
        }

        private data class MaskInstruction(val mask: String) : InitializationInstruction()
        private data class MemoryInstruction(val address: Long, val value: Long) : InitializationInstruction()
    }
}
