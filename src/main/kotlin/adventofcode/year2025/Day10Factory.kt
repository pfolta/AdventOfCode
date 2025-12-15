package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Permutations.powersets
import com.microsoft.z3.Context
import com.microsoft.z3.Status.SATISFIABLE

class Day10Factory(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput() = input.lines().map(Machine::invoke)

    override fun partOne() =
        parseInput()
            .sumOf { machine ->
                machine
                    .buttons
                    .powersets()
                    .sortedBy { combination -> combination.size }
                    .first { combination ->
                        combination.fold(List(machine.lights.size) { false }) { lights, buttons ->
                            lights.mapIndexed { index, light ->
                                when (index in buttons) {
                                    true -> !light
                                    else -> light
                                }
                            }
                        } == machine.lights
                    }.size
            }

    override fun partTwo() =
        parseInput()
            .sumOf { machine ->
                Context().use { ctx ->
                    val solver = ctx.mkOptimize()

                    // Create variables for button presses and ensure they are positive.
                    val buttons =
                        machine.buttons.indices
                            .map { index -> ctx.mkIntConst("button#$index") }
                            .onEach { button -> solver.Add(ctx.mkGe(button, ctx.mkInt(0))) }

                    // Create equations: For each joltage counter, ensure the sum of all button presses is equal to the specified joltage
                    machine.joltages.forEachIndexed { counter, joltage ->
                        val pressedButtons =
                            machine.buttons
                                .withIndex()
                                .filter { (_, counters) -> counter in counters }
                                .map { buttons[it.index] }

                        solver.Add(ctx.mkEq(ctx.mkAdd(*pressedButtons.toTypedArray()), ctx.mkInt(joltage)))
                    }

                    // Solve for the minimum number of button presses
                    val buttonPresses = ctx.mkIntConst("buttonPresses")
                    solver.Add(ctx.mkEq(buttonPresses, ctx.mkAdd(*buttons.toTypedArray())))
                    solver.MkMinimize(buttonPresses)

                    when (solver.Check()) {
                        SATISFIABLE ->
                            solver.model
                                .evaluate(buttonPresses, false)
                                .toString()
                                .toInt()

                        else -> 0
                    }
                }
            }

    companion object {
        private data class Machine(
            val lights: List<Boolean>,
            val buttons: List<List<Int>>,
            val joltages: List<Int>,
        ) {
            companion object {
                operator fun invoke(input: String): Machine {
                    val lights =
                        input
                            .substringAfter("[")
                            .substringBefore("]")
                            .split("")
                            .filter(String::isNotEmpty)
                            .map { light ->
                                when (light.single()) {
                                    '#' -> true
                                    else -> false
                                }
                            }

                    val buttons =
                        input
                            .substringAfter(" ")
                            .substringBefore(" {")
                            .split(" ")
                            .map { buttons ->
                                buttons
                                    .replace("(", "")
                                    .replace(")", "")
                                    .split(",")
                                    .map(String::toInt)
                            }

                    val joltages =
                        input
                            .substringAfter("{")
                            .substringBefore("}")
                            .split(",")
                            .map(String::toInt)

                    return Machine(lights, buttons, joltages)
                }
            }
        }
    }
}
