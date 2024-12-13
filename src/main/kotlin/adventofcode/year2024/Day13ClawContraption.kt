package adventofcode.year2024

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Point2d

class Day13ClawContraption(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val clawMachines by lazy {
        input
            .split("\n\n")
            .map { machine ->
                val (buttonAX, buttonAY) = INPUT_REGEX.find(machine.lines().first())!!.destructured
                val (buttonBX, buttonBY) = INPUT_REGEX.find(machine.lines().drop(1).first())!!.destructured
                val (prizeX, prizeY) = INPUT_REGEX.find(machine.lines().last())!!.destructured

                ClawMachine(
                    Point2d(buttonAX.toInt(), buttonAY.toInt()),
                    Point2d(buttonBX.toInt(), buttonBY.toInt()),
                    Point2d(prizeX.toInt(), prizeY.toInt()),
                )
            }
    }

    override fun partOne() = clawMachines.sumOf { clawMachine -> clawMachine.countTokens() }

    override fun partTwo() =
        clawMachines
            .map { (buttonA, buttonB, prize) -> ClawMachine(buttonA, buttonB, prize + PART_TWO_OFFSET) }
            .sumOf { clawMachine -> clawMachine.countTokens() }

    companion object {
        private val INPUT_REGEX = """X[+|=](\d+), Y[+|=](\d+)""".toRegex()

        private const val COST_A = 3
        private const val COST_B = 1

        private const val PART_TWO_OFFSET = 10000000000000

        private data class ClawMachine(
            val buttonA: Point2d,
            val buttonB: Point2d,
            val prize: Point2d,
        ) {
            fun countTokens(): Long {
                val (ax, ay) = buttonA
                val (bx, by) = buttonB
                val (px, py) = prize

                val dividend = ax * by - bx * ay
                val a = px * by - bx * py
                val b = ax * py - px * ay

                return if (setOf(a, b).all { it % dividend == 0L }) {
                    a / dividend * COST_A + b / dividend * COST_B
                } else {
                    0
                }
            }
        }
    }
}
