package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.String.containsOnlyDigits

class Day12LeonardosMonorail(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Leonardo's Monorail"

    override fun partOne() = input.lines().runAssembunny()["a"]!!

    override fun partTwo() = input.lines().runAssembunny(mutableMapOf("c" to 1))["a"]!!

    companion object {
        private fun List<String>.runAssembunny(registers: MutableMap<String, Int> = mutableMapOf()): Map<String, Int> {
            var index = 0

            while (index < this.size) {
                index = runAssembunnyInstruction(this, index, registers)
            }

            return registers.toMap()
        }

        fun runAssembunnyInstruction(
            code: List<String>,
            index: Int,
            registers: MutableMap<String, Int>,
        ): Int {
            val instruction = code[index].split(" ")

            return when (instruction.first()) {
                "cpy" -> {
                    registers[instruction.last()] = intOrRegisterValue(instruction[1], registers)
                    index + 1
                }

                "inc" -> {
                    val register = instruction.last()
                    registers[register] = registers.getOrDefault(register, 0) + 1
                    index + 1
                }

                "dec" -> {
                    val register = instruction.last()
                    registers[register] = registers.getOrDefault(register, 0) - 1
                    index + 1
                }

                "jnz" ->
                    when (intOrRegisterValue(instruction[1], registers)) {
                        0 -> index + 1
                        else -> index + instruction.last().toInt()
                    }

                else -> throw IllegalArgumentException("'$this' is not a valid assembunny instruction")
            }
        }

        fun intOrRegisterValue(
            value: String,
            registers: Map<String, Int>,
        ): Int =
            when (value.containsOnlyDigits()) {
                true -> value.toInt()
                false -> registers.getOrDefault(value, 0)
            }
    }
}
