package adventofcode.year2019

import adventofcode.Day

object Day01TheTyrannyOfTheRocketEquation : Day() {
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
