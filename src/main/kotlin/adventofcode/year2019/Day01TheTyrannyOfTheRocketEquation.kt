package adventofcode.year2019

import adventofcode.Puzzle

object Day01TheTyrannyOfTheRocketEquation : Puzzle() {
    override val title = "The Tyranny of the Rocket Equation"

    private val modules = input.lines().map(String::toInt)

    private fun getFuelForModule(module: Int) = module / 3 - 2

    override fun partOne() = modules.map { getFuelForModule(it) }.sum()

    override fun partTwo() = modules
        .map { module ->
            var toAdd = 0
            var fuel = getFuelForModule(module)

            while (fuel > 0) {
                toAdd += fuel
                fuel = getFuelForModule(fuel)
            }

            toAdd
        }
        .sum()
}
