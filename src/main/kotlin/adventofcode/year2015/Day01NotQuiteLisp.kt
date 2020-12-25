package adventofcode.year2015

import adventofcode.Puzzle

object Day01NotQuiteLisp : Puzzle() {
    override fun partOne() = input.fold(0) { floor, step -> if (step == '(') floor + 1 else floor - 1 }

    override fun partTwo() = input
        .mapIndexed { index, step ->
            step to input.substring(0, index).fold(0) { floor, substep -> if (substep == '(') floor + 1 else floor - 1 }
        }
        .indexOfFirst { it.second == -1 }
}
