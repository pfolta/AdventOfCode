package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.PuzzleInput

private typealias InstructionIndex = Int
private typealias Registers = MutableMap<String, Int>

class Day12LeonardosMonorail(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Leonardo's Monorail"

    override fun partOne() = input.lines().runAssembunny()["a"]!!

    override fun partTwo() = input.lines().runAssembunny(mutableMapOf("c" to 1))["a"]!!

    companion object {
        private fun List<String>.runAssembunny(registers: Registers = mutableMapOf()): Registers {
            var index = 0

            while (index < this.size) {
                index = this[index].runAssembunnyInstruction(index, registers)
            }

            return registers
        }

        private fun String.runAssembunnyInstruction(index: Int, registers: Registers): InstructionIndex {
            val instruction = this.split(" ")

            return when (instruction.first()) {
                "cpy" -> {
                    val source = instruction[1]
                    val destination = instruction.last()

                    when {
                        source.toIntOrNull() != null -> registers[destination] = source.toInt()
                        else -> registers[destination] = registers.getOrDefault(source, 0)
                    }

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

                "jnz" -> {
                    val test = instruction[1]
                    val offset = instruction.last().toInt()

                    when {
                        test.toIntOrNull() == 0 -> index + 1
                        test.toIntOrNull() == null && registers.getOrDefault(test, 0) == 0 -> index + 1
                        else -> index + offset
                    }
                }

                else -> throw IllegalArgumentException("'$this' is not a valid assembunny instruction")
            }
        }
    }
}
