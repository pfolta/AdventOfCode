package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.pow

class Day17ChronospatialComputer(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val registers by lazy { input.lines().take(3).map { it.substringAfter(": ").toInt() } }
    private val program by lazy { input.lines().last().substringAfter(": ").split(",").map(String::toInt) }

    override fun partOne(): String {
        var (registerA, registerB, registerC) = registers
        var pointer = 0
        val output = mutableListOf<Int>()

        while (pointer < program.size - 1) {
            val opcode = program[pointer]
            val literalOperand = program[pointer + 1]

            val comboOperand =
                when (literalOperand) {
                    in 0..3 -> literalOperand
                    4 -> registerA
                    5 -> registerB
                    6 -> registerC
                    else -> throw IllegalArgumentException("$literalOperand is not a valid combo operand")
                }

            when (opcode) {
                0 -> {
                    registerA = adv(registerA, comboOperand)
                    pointer += 2
                }

                1 -> {
                    registerB = registerB.xor(literalOperand)
                    pointer += 2
                }

                2 -> {
                    registerB = comboOperand.mod(8)
                    pointer += 2
                }

                3 -> {
                    pointer =
                        when (registerA) {
                            0 -> pointer + 2
                            else -> literalOperand
                        }
                }

                4 -> {
                    registerB = registerB.xor(registerC)
                    pointer += 2
                }

                5 -> {
                    output += comboOperand.mod(8)
                    pointer += 2
                }

                6 -> {
                    registerB = adv(registerA, comboOperand)
                    pointer += 2
                }

                7 -> {
                    registerC = adv(registerA, comboOperand)
                    pointer += 2
                }
            }
        }

        return output.joinToString(",")
    }

    companion object {
        private fun adv(
            numerator: Int,
            comboOperand: Int,
        ) = (numerator / 2.0.pow(comboOperand)).toInt()
    }
}
