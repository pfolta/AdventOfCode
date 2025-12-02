package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day10CathodeRayTube(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    override val name = "Cathode-Ray Tube"

    private val instructionCycles by lazy { input.toInstructionCycles() }

    override fun partOne() =
        instructionCycles
            .filter { (cycle, _, _) -> cycle in setOf(20, 60, 100, 140, 180, 220) }
            .sumOf { (cycle, _, x) -> cycle * x }

    override fun partTwo() =
        instructionCycles
            .map { (cycle, _, x) ->
                val pixel = (cycle - 1) % SCREEN_WIDTH
                val spritePosition = listOf(x - 1, x, x + 1)

                spritePosition.contains(pixel)
            }.chunked(SCREEN_WIDTH)
            .joinToString(
                separator = "\n",
                prefix = "\n",
                postfix = "\n",
            ) { row -> row.joinToString("") { cell -> if (cell) "█" else " " } }

    companion object {
        private const val SCREEN_WIDTH = 40

        private fun String.toInstructionCycles() =
            lines()
                .fold(listOf(Triple("", 0, 1))) { acc, instruction ->
                    val (_, _, xAfterPreviousCycle) = acc.last()

                    acc +
                        when {
                            instruction.startsWith("addx ") ->
                                listOf(
                                    Triple("noop", xAfterPreviousCycle, xAfterPreviousCycle),
                                    Triple(instruction, xAfterPreviousCycle, xAfterPreviousCycle + instruction.split(" ").last().toInt()),
                                )

                            else -> listOf(Triple(instruction, xAfterPreviousCycle, xAfterPreviousCycle))
                        }
                }.mapIndexed { cycle, (instruction, xBeforeCycle, _) -> Triple(cycle, instruction, xBeforeCycle) }
                .drop(1)
    }
}
