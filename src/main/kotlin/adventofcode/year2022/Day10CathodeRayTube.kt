package adventofcode.year2022

import adventofcode.Puzzle

class Day10CathodeRayTube(customInput: String? = null) : Puzzle(customInput) {
    override val name = "Cathode-Ray Tube"

    override fun partOne() = input
        .lines()
        .fold(listOf(Triple("", 0, 1))) { acc, instruction ->
            val (_, _, xAfterPreviousCycle) = acc.last()

            when {
                instruction.startsWith("addx ") -> acc + listOf(
                    Triple("noop", xAfterPreviousCycle, xAfterPreviousCycle),
                    Triple(instruction, xAfterPreviousCycle, xAfterPreviousCycle + instruction.split(" ").last().toInt())
                )

                else -> acc + listOf(Triple(instruction, xAfterPreviousCycle, xAfterPreviousCycle))
            }
        }
        .mapIndexed { cycle, (instruction, xBeforeCycle, _) -> Triple(cycle, instruction, xBeforeCycle) }
        .filter { (cycle, _, _) -> cycle in setOf(20, 60, 100, 140, 180, 220) }
        .sumOf { (cycle, _, x) -> cycle * x }
}
