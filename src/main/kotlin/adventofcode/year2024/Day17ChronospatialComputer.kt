package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day17ChronospatialComputer(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val registers by lazy { input.lines().take(3).map { it.substringAfter(": ").toLong() } }
    private val program by lazy {
        input
            .lines()
            .last()
            .substringAfter(": ")
            .split(",")
            .map(String::toInt)
    }

    override fun partOne() = runProgram(program, registers).joinToString(",")

    override fun partTwo() =
        program
            .reversed()
            .map(Int::toLong)
            .fold(listOf(0L)) { candidates, instruction ->
                candidates.flatMap { candidate ->
                    val shifted = candidate shl 3
                    (shifted..shifted + 8).mapNotNull { registerA ->
                        registerA.takeIf { runProgram(program, listOf(registerA) + registers.drop(1)).first() == instruction }
                    }
                }
            }.first()

    companion object {
        private fun runProgram(
            program: List<Int>,
            registers: List<Long>,
        ): List<Long> {
            var (registerA, registerB, registerC) = registers
            var pointer = 0
            val output = mutableListOf<Long>()

            while (pointer < program.size - 1) {
                val opcode = program[pointer]
                val literalOperand = program[pointer + 1]

                val comboOperand =
                    when (literalOperand) {
                        in 0..3 -> literalOperand.toLong()
                        4 -> registerA
                        5 -> registerB
                        6 -> registerC
                        else -> throw IllegalArgumentException("$literalOperand is not a valid combo operand")
                    }

                when (opcode) {
                    0 -> {
                        registerA = registerA shr comboOperand.toInt()
                        pointer += 2
                    }

                    1 -> {
                        registerB = registerB xor literalOperand.toLong()
                        pointer += 2
                    }

                    2 -> {
                        registerB = comboOperand.mod(8L)
                        pointer += 2
                    }

                    3 -> {
                        pointer =
                            when (registerA) {
                                0L -> pointer + 2
                                else -> literalOperand
                            }
                    }

                    4 -> {
                        registerB = registerB xor registerC
                        pointer += 2
                    }

                    5 -> {
                        output += comboOperand.mod(8).toLong()
                        pointer += 2
                    }

                    6 -> {
                        registerB = registerA shr comboOperand.toInt()
                        pointer += 2
                    }

                    7 -> {
                        registerC = registerA shr comboOperand.toInt()
                        pointer += 2
                    }
                }
            }

            return output
        }
    }
}
