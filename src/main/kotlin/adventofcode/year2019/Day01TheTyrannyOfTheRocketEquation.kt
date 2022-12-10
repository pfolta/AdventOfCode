package adventofcode.year2019

import adventofcode.Puzzle

class Day01TheTyrannyOfTheRocketEquation(customInput: String? = null) : Puzzle(customInput) {
    override val name = "The Tyranny of the Rocket Equation"

    private val modules by lazy { input.lines().map(String::toInt) }

    override fun partOne() = modules.sumOf { getFuelForModule(it) }

    override fun partTwo() = modules
        .sumOf { module ->
            var toAdd = 0
            var fuel = getFuelForModule(module)

            while (fuel > 0) {
                toAdd += fuel
                fuel = getFuelForModule(fuel)
            }

            toAdd
        }

    companion object {
        private fun getFuelForModule(module: Int) = module / 3 - 2
    }
}
