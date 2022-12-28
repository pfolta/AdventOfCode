package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.PuzzleInput

private typealias InstructionIndex = Int
private typealias Registers = MutableMap<Char, Int>

class Day12LeonardosMonorail(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Leonardo's Monorail"

    override fun partOne() = input.lines().runAssembunny()['a']!!

    companion object {
        private fun List<String>.runAssembunny(): Registers {
            val registers = mutableMapOf('a' to 0, 'b' to 0, 'c' to 0, 'd' to 0)
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
                    val destination = instruction.last().first()

                    when {
                        source.toIntOrNull() != null -> registers[destination] = source.toInt()
                        else -> registers[destination] = registers[source.first()]!!
                    }

                    index + 1
                }

                "inc" -> {
                    val register = instruction.last().first()
                    registers[register] = registers[register]!! + 1
                    index + 1
                }

                "dec" -> {
                    val register = instruction.last().first()
                    registers[register] = registers[register]!! - 1
                    index + 1
                }

                "jnz" -> {
                    val register = instruction[1].first()
                    val offset = instruction.last().toInt()

                    when (registers[register]) {
                        0 -> index + 1
                        else -> index + offset
                    }
                }

                else -> throw IllegalArgumentException("'$this' is not a valid assembunny instruction")
            }
        }
    }
}
