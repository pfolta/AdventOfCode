package adventofcode.year2019

import adventofcode.utils.readInputAsLines

private fun getFuelForModule(module: Int) = module / 3 - 2

fun part1(modules: List<Int>) = modules.map { getFuelForModule(it) }.sum()

fun part2(modules: List<Int>) =
    modules.map { module ->
        var toAdd = 0
        var fuel = getFuelForModule(module)

        while (fuel > 0) {
            toAdd += fuel
            fuel = getFuelForModule(fuel)
        }

        toAdd
    }
        .sum()

fun main() {
    val modules = readInputAsLines(2019, 1).map { it.toInt() }

    println("Part 1: ${part1(modules)}")
    println("Part 2: ${part2(modules)}")
}
