package adventofcode.year2020

import adventofcode.year2020.Day8HandheldHalting.part1
import adventofcode.year2020.Day8HandheldHalting.part2
import adventofcode.year2020.Operation.ACC
import adventofcode.year2020.Operation.JMP
import adventofcode.year2020.Operation.NOP
import adventofcode.utils.readInputAsLines

enum class Operation(val type: String) {
    ACC("acc"),
    JMP("jmp"),
    NOP("nop");

    companion object {
        fun fromString(type: String) = values().associateBy(Operation::type)[type]
    }
}

data class Instruction(
    val operation: Operation,
    val argument: Int
)

data class ExecutionResult(
    val acc: Int,
    val terminatedNormally: Boolean
)

object Day8HandheldHalting {
    private fun execute(instructions: List<Instruction>): ExecutionResult {
        var acc = 0
        var index = 0
        val visitedInstructions = mutableListOf<Int>()

        while (index < instructions.size && !visitedInstructions.contains(index)) {
            visitedInstructions.add(index)

            when (instructions[index].operation) {
                ACC -> {
                    acc += instructions[index].argument
                    index++
                }
                JMP -> index += instructions[index].argument
                NOP -> index++
            }
        }

        return ExecutionResult(acc, index == instructions.size)
    }

    fun part1(instructions: List<Instruction>) = execute(instructions).acc

    fun part2(instructions: List<Instruction>) = (instructions.indices)
        .mapNotNull {
            val before = instructions.take(it)
            val after = instructions.takeLast(instructions.size - it - 1)

            when (instructions[it].operation) {
                ACC -> null
                JMP -> before + Instruction(NOP, instructions[it].argument) + after
                NOP -> before + Instruction(JMP, instructions[it].argument) + after
            }
        }
        .map(::execute)
        .first { it.terminatedNormally }
        .acc
}

fun main() {
    val instructions = readInputAsLines(2020, 8)
        .map { Instruction(Operation.fromString(it.split(" ").first())!!, it.split(" ").last().toInt()) }

    println("Part 1: ${part1(instructions)}")
    println("Part 2: ${part2(instructions)}")
}
