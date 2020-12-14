package adventofcode.year2020

import adventofcode.year2020.Day14DockingData.part1
import adventofcode.year2020.Day14DockingData.part2
import adventofcode.utils.readInputAsLines
import adventofcode.year2020.InitializationProgram.MaskInstruction
import adventofcode.year2020.InitializationProgram.MemoryInstruction

sealed class InitializationProgram {
    data class MaskInstruction(val mask: String) : InitializationProgram()
    data class MemoryInstruction(val address: Long, val value: Long) : InitializationProgram()
}

fun String.prepend(prefix: Char, totalLength: Int) = prefix.toString().repeat(totalLength - length) + this

fun String.replace(position: Int, replacement: Char) = substring(0 until position) + replacement + substring(position + 1 until length)

object Day14DockingData {
    fun part1(program: List<InitializationProgram>) = program
        .fold(Pair("X".repeat(36), emptyMap<Long, Long>())) { (mask, memoryMap), instruction ->
            when (instruction) {
                is MaskInstruction -> Pair(instruction.mask, memoryMap)
                is MemoryInstruction -> Pair(
                    mask, memoryMap + mapOf(
                        instruction.address to (mask.indices)
                            .fold(instruction.value.toString(2).prepend('0', mask.length)) { result, index ->
                                if (mask[index] == 'X') result else result.replace(index, mask[index])
                            }.toLong(2)
                    )
                )

            }
        }.second.values.sum()

    fun part2(program: List<InitializationProgram>) = program
        .fold(Pair("0".repeat(36), emptyMap<Long, Long>())) { (mask, memoryMap), instruction ->
            when (instruction) {
                is MaskInstruction -> Pair(instruction.mask, memoryMap)
                is MemoryInstruction -> {
                    val addressMask = (mask.indices)
                        .fold(instruction.address.toString(2).prepend('0', mask.length)) { result, index ->
                            if (mask[index] == '0') result else result.replace(index, mask[index])
                        }

                    Pair(mask, memoryMap + (addressMask.indices)
                        .fold(listOf(addressMask)) { acc, index ->
                            if (addressMask[index] != 'X') acc
                            else acc.flatMap { listOf(it.replace(index, '0'), it.replace(index, '1')) }
                        }
                        .map { it.toLong(2) to instruction.value })
                }
            }
        }.second.values.sum()
}

fun main() {
    val input = readInputAsLines(2020, 14)
        .map {
            val parts = it.split(" = ")

            if (parts.first() == "mask") MaskInstruction(parts.last())
            else MemoryInstruction(parts.first().substring(4 until parts.first().length - 1).toLong(), parts.last().toLong())
        }

    val part1 = part1(input)
    val part2 = part2(input)

    println("Part 1: $part1")
    println("Part 2: $part2")
}
