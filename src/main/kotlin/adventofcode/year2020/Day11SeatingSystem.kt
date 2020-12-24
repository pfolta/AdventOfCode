package adventofcode.year2020

import adventofcode.Day

// Eight directions going up, down, left, right, or diagonal
private val directions = (-1..1).flatMap { dx -> (-1..1).map { dy -> Pair(dx, dy) } }.filter { it != Pair(0, 0) }

object Day11SeatingSystem : Day() {
    private val seatMap = input.lines().map { it.split("") }

    override fun partOne() = seatMap.iterate(4, List<List<String>>::getImmediateNeighbors)

    override fun partTwo() = seatMap.iterate(5, List<List<String>>::getNearestSeatNeighbors)
}

private fun List<List<String>>.getNearestSeatNeighbor(self: Pair<Int, Int>, direction: Pair<Int, Int>): String? {
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

private fun List<List<String>>.getImmediateNeighbors(self: Pair<Int, Int>) =
    directions.mapNotNull { dir -> getOrNull(self.second + dir.second)?.getOrNull(self.first + dir.first) }

private fun List<List<String>>.next(tolerance: Int, neighborFunction: List<List<String>>.(Pair<Int, Int>) -> List<String>) =
    mapIndexed { y, row ->
        row.mapIndexed { x, _ ->
            val occupiedNeighbors = neighborFunction(this, Pair(x, y)).count { it == "#" }

            when {
                (this[y][x] == "L" && occupiedNeighbors == 0) -> "#"
                (this[y][x] == "#" && occupiedNeighbors >= tolerance) -> "L"
                else -> this[y][x]
            }
        }
    }

private fun List<List<String>>.iterate(tolerance: Int, neighborFunction: List<List<String>>.(Pair<Int, Int>) -> List<String>) =
    generateSequence(this) { it.next(tolerance, neighborFunction) }
        .zipWithNext()
        .first { it.first == it.second }
        .first
        .sumBy { it.count { it == "#" } }

private fun List<List<String>>.getNearestSeatNeighbors(self: Pair<Int, Int>) = directions
    .mapNotNull { dir -> getNearestSeatNeighbor(self, dir) }
