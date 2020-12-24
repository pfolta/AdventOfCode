package adventofcode.year2019

import adventofcode.Day

object Day1TheTyrannyOfTheRocketEquation : Day() {
    private fun getFuelForModule(module: Int) = module / 3 - 2

    override fun partOne() = input.lines().map(String::toInt).map { getFuelForModule(it) }.sum()

    override fun partTwo() = input
        .lines()
        .map(String::toInt)
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
