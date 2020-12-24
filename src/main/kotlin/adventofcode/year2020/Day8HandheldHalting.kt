package adventofcode.year2020

import adventofcode.Day
import adventofcode.year2020.Operation.ACC
import adventofcode.year2020.Operation.JMP
import adventofcode.year2020.Operation.NOP

object Day8HandheldHalting : Day() {
    private val instructions = input.lines().map(::Instruction)

    override fun partOne() = instructions.execute().acc

    override fun partTwo() = (instructions.indices)
        .mapNotNull {
            val before = instructions.take(it)
            val after = instructions.takeLast(instructions.size - it - 1)

            when (instructions[it].operation) {
                ACC -> null
                JMP -> before + Instruction(NOP, instructions[it].argument) + after
                NOP -> before + Instruction(JMP, instructions[it].argument) + after
            }
        }
        .map(List<Instruction>::execute)
        .first { it.terminatedNormally }
        .acc
}

private enum class Operation(val type: String) {
    ACC("acc"),
    JMP("jmp"),
    NOP("nop");

    companion object {
        fun fromString(type: String) = values().associateBy(Operation::type)[type]
    }
}

private data class Instruction(
    val operation: Operation,
    val argument: Int
) {
    constructor(input: String) : this(Operation.fromString(input.split(" ").first())!!, input.split(" ").last().toInt())
}

private fun List<Instruction>.execute(): ExecutionResult {
    var acc = 0
    var index = 0
    val visitedInstructions = mutableListOf<Int>()

    while (index < size && !visitedInstructions.contains(index)) {
        visitedInstructions.add(index)

        when (this[index].operation) {
            ACC -> {
                acc += this[index].argument
                index++
            }
            JMP -> index += this[index].argument
            NOP -> index++
        }
    }

    return ExecutionResult(acc, index == size)
}

private data class ExecutionResult(
    val acc: Int,
    val terminatedNormally: Boolean
)
