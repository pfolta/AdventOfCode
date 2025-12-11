package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Permutations.powersets

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
