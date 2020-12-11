package adventofcode.year2020

import adventofcode.year2020.Day3TobogganTrajectory.part1
import adventofcode.year2020.Day3TobogganTrajectory.part2
import adventofcode.utils.readInputAsLines

data class Slope(
    val dx: Long,
    val dy: Long
)

object Day3TobogganTrajectory {
    fun part1(treeMap: List<String>, slope: Slope): Long {
        var counter = 0L
        var x = 0L
        var y = 0L

        while (y < treeMap.size) {
            val currentLine = treeMap[y.toInt()].toCharArray()

            val field = currentLine[x.toInt() % currentLine.size]

            if (field == '#') counter++

            x += slope.dx
            y += slope.dy
        }

        return counter
    }

    fun part2(treeMap: List<String>, slopes: List<Slope>) = slopes
        .map { part1(treeMap, it) }
        .reduce { product, element -> product * element }
}

fun main() {
    val treeMap = readInputAsLines(2020, 3)

    println("Part 1: ${part1(treeMap, Slope(3, 1))}")
    println("Part 2: ${part2(treeMap, listOf(Slope(1, 1), Slope(3, 1), Slope(5, 1), Slope(7, 1), Slope(1, 2)))}")
}
