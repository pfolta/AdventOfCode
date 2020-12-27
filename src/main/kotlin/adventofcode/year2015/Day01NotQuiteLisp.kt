package adventofcode.year2015

import adventofcode.Puzzle

class Day01NotQuiteLisp(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = input.fold(0) { floor, step -> if (step == '(') floor + 1 else floor - 1 }

    override fun partTwo() = input
        .mapIndexed { index, step ->
            step to input.substring(0, index + 1).fold(0) { floor, substep -> if (substep == '(') floor + 1 else floor - 1 }
        }
        .indexOfFirst { it.second == -1 }
        .plus(1)
}
