package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.everyNth

class Day25ClockSignal(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() =
        generateSequence(1, Int::inc)
            .map { a -> a to input.lines().runAssembunny(mutableMapOf("a" to a)) }
            .first { (_, out) -> out.everyNth(2).all { it == 0 } && out.everyNth(2, 1).all { it == 1 } }
            .first

    companion object {
        private fun List<String>.runAssembunny(registers: MutableMap<String, Int> = mutableMapOf()): List<Int> {
            var index = 0
            val executedInstructions = mutableSetOf<Pair<Int, Map<String, Int>>>()
            val transmissions = mutableListOf<Int>()

            while (index < this.size && !executedInstructions.contains(index to registers.toMap())) {
                val result = runAssembunnyInstruction(this, index, registers)

                executedInstructions.add(index to registers.toMap())
                index = result.first

                if (result.second != null) {
                    transmissions.add(result.second!!)
                }
            }

            return transmissions.toList()
        }

        private fun runAssembunnyInstruction(
            code: List<String>,
            index: Int,
            registers: MutableMap<String, Int>,
        ): Pair<Int, Int?> {
            val instruction = code[index].split(" ")

            return when (instruction.first()) {
                "out" -> index + 1 to Day12LeonardosMonorail.intOrRegisterValue(instruction.last(), registers)
                else -> Day12LeonardosMonorail.runAssembunnyInstruction(code, index, registers) to null
            }
        }
    }
}
