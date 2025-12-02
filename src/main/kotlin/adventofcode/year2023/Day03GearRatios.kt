package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.neighbors
import adventofcode.common.product

class Day03GearRatios(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    override fun partOne() =
        input
            .parsePartNumbers()
            .filter { partNumber -> partNumber.isValidPartNumber(input.parseGrid()) }
            .sumOf(PartNumber::value)

    override fun partTwo() =
        input
            .parsePartNumbers()
            .asSequence()
            .map { partNumber -> partNumber.getAdjacentGears(input.parseGrid()) to partNumber.value }
            .filter { (gears, _) -> gears.isNotEmpty() }
            .groupBy { (gears, _) -> gears }
            .map { (_, adjacentParts) -> adjacentParts.map { (_, adjacentPartNumber) -> adjacentPartNumber } }
            .filter { adjacentPartNumbers -> adjacentPartNumbers.size == 2 }
            .sumOf(List<Int>::product)

    companion object {
        private val PART_NUMBER_REGEX = """(\d+)""".toRegex()

        private data class PartNumber(
            val value: Int,
            val location: Pair<IntRange, Int>,
        ) {
            fun isValidPartNumber(grid: List<List<Char>>) =
                neighbors(grid)
                    .map { (x, y) -> grid[y][x] }
                    .filterNot { value -> value == '.' }
                    .isNotEmpty()

            fun getAdjacentGears(grid: List<List<Char>>) =
                neighbors(grid)
                    .filter { (x, y) -> grid[y][x] == '*' }
                    .toSet()

            private fun neighbors(grid: List<List<Char>>): Set<Pair<Int, Int>> {
                val coordinates =
                    location
                        .first
                        .map { x -> x to location.second }
                        .toSet()

                return coordinates
                    .flatMap { (x, y) -> grid.neighbors(x, y, includeDiagonals = true) }
                    .toSet()
                    .minus(coordinates)
            }
        }

        private fun String.parseGrid(): List<List<Char>> = lines().map(String::toList)

        private fun String.parsePartNumbers(): List<PartNumber> =
            lines()
                .flatMapIndexed { y, line ->
                    PART_NUMBER_REGEX
                        .findAll(line)
                        .map { PartNumber(it.value.toInt(), it.range to y) }
                }
    }
}
