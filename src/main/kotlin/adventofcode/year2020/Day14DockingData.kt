package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.common.replaceAt
import adventofcode.year2020.Day14DockingData.Companion.InitializationInstruction.MaskInstruction
import adventofcode.year2020.Day14DockingData.Companion.InitializationInstruction.MemoryInstruction

class Day14DockingData(customInput: String? = null) : Puzzle(customInput) {
    private val initializationInstructions = input.lines().map(InitializationInstruction::parseInstruction)

    override fun partOne() = initializationInstructions
        .fold(Pair("X".repeat(36), emptyMap<Long, Long>())) { (mask, memoryMap), instruction ->
            when (instruction) {
                is MaskInstruction -> Pair(instruction.mask, memoryMap)
                is MemoryInstruction -> Pair(
                    mask, memoryMap + mapOf(
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

                    Pair(mask, memoryMap + (addressMask.indices)
                        .fold(listOf(addressMask)) { acc, index ->
                            if (addressMask[index] != 'X') acc
                            else acc.flatMap { listOf(it.replaceAt(index, '0'), it.replaceAt(index, '1')) }
                        }
                        .map { it.toLong(2) to instruction.value })
                }
            }
        }.second.values.sum()

    companion object {
        sealed class InitializationInstruction {
            data class MaskInstruction(val mask: String) : InitializationInstruction()
            data class MemoryInstruction(val address: Long, val value: Long) : InitializationInstruction()

            companion object {
                fun parseInstruction(input: String): InitializationInstruction {
                    val parts = input.split(" = ")

                    return when (parts.first()) {
                        "mask" -> MaskInstruction(parts.last())
                        else -> MemoryInstruction(parts.first().substring(4 until parts.first().length - 1).toLong(), parts.last().toLong())
                    }
                }
            }
        }
    }
}
