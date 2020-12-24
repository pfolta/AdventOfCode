package adventofcode.year2015

import adventofcode.Day

object Day02IWasToldThereWouldBeNoMath : Day() {
    private val dimensions = input.lines().map { it.split("x").map(String::toInt) }

    override fun partOne() = dimensions
        .map {
            val wrapping = 2 * (it[0] * it[1] + it[1] * it[2] + it[0] * it[2])
            val slack = it.sorted().subList(0, 2).reduce { product, factor -> product * factor }

            wrapping + slack
        }
        .sum()

    override fun partTwo() = dimensions
        .map {
            val ribbon = it.sorted().subList(0, 2).fold(0) { total, side -> total + 2 * side }
            val bow = it[0] * it[1] * it[2]

            ribbon + bow
        }
        .sum()
}
