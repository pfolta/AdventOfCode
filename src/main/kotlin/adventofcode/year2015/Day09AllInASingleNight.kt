package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.common.permutations

class Day09AllInASingleNight(customInput: String? = null) : Puzzle(customInput) {
    override val name = "All in a Single Night"

    private val distances = input
        .lines()
        .map {
            val (cityA, cityB, distance) = INPUT_REGEX.find(it)!!.destructured
            setOf(cityA, cityB) to distance.toInt()
        }
        .toMap()

    private val cities = distances.keys.flatten().distinct()

    private val routes = cities
        .permutations()
        .map { route ->
            route.dropLast(1).foldIndexed(0) { index, distance, _ ->
                distance + distances[setOf(route[index], route[index + 1])]!!
            }
        }

    override fun partOne() = routes.minOrNull() ?: 0

    override fun partTwo() = routes.maxOrNull() ?: 0

    companion object {
        private val INPUT_REGEX = """(\w+) to (\w+) = (\d+)""".toRegex()
    }
}
