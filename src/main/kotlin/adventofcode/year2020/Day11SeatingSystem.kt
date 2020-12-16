package adventofcode.year2020

import adventofcode.year2020.Day11SeatingSystem.part1
import adventofcode.year2020.Day11SeatingSystem.part2
import adventofcode.utils.readInputAsLines

typealias SeatMap = List<List<String>>

fun SeatMap.getNearestSeatNeighbor(self: Pair<Int, Int>, direction: Pair<Int, Int>): String? {
    var x = self.first
    var y = self.second

    while ((x + direction.first) in first().indices && (y + direction.second) in indices) {
        val cx = x + direction.first
        val cy = y + direction.second

        if (this[cy][cx] != ".") return this[cy][cx]

        x += direction.first
        y += direction.second
    }

    return null
}

// Eight directions going up, down, left, right, or diagonal
private val directions = (-1..1).flatMap { dx -> (-1..1).map { dy -> Pair(dx, dy) } }.filter { it != Pair(0, 0) }

private fun SeatMap.getImmediateNeighbors(self: Pair<Int, Int>) =
    directions.mapNotNull { dir -> getOrNull(self.second + dir.second)?.getOrNull(self.first + dir.first) }

private fun SeatMap.getNearestSeatNeighbors(self: Pair<Int, Int>) = directions
    .mapNotNull { dir -> getNearestSeatNeighbor(self, dir) }

private fun SeatMap.next(tolerance: Int, neighborFunction: SeatMap.(Pair<Int, Int>) -> List<String>) = mapIndexed { y, row ->
    row.mapIndexed { x, _ ->
        val occupiedNeighbors = neighborFunction(this, Pair(x, y)).count { it == "#" }

        when {
            (this[y][x] == "L" && occupiedNeighbors == 0) -> "#"
            (this[y][x] == "#" && occupiedNeighbors >= tolerance) -> "L"
            else -> this[y][x]
        }
    }
}

private fun SeatMap.iterate(tolerance: Int, neighborFunction: SeatMap.(Pair<Int, Int>) -> List<String>) =
    generateSequence(this) { it.next(tolerance, neighborFunction) }
        .zipWithNext()
        .first { it.first == it.second }
        .first
        .sumBy { it.count { it == "#" } }

object Day11SeatingSystem {
    fun part1(input: SeatMap) = input.iterate(4, SeatMap::getImmediateNeighbors)

    fun part2(input: SeatMap) = input.iterate(5, SeatMap::getNearestSeatNeighbors)
}

fun main() {
    val input = readInputAsLines(2020, 11).map { it.split("") }

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
