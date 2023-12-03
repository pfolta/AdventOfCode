package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.neighbors

class Day03GearRatios(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .parsePartNumbers()
        .filter { partNumber -> partNumber.isValidPartNumber(input.parseGrid()) }
        .sumOf(PartNumber::value)

    companion object {
        private val PART_NUMBER_REGEX = """(\d+)""".toRegex()

        private data class PartNumber(
            val value: Int,
            val location: Pair<IntRange, Int>
        ) {
            fun isValidPartNumber(grid: List<List<Char>>): Boolean {
                val coordinates = location
                    .first
                    .map { x -> x to location.second }
                    .toSet()

                return coordinates
                    .flatMap { (x, y) -> grid.neighbors(x, y, includeDiagonals = true) }
                    .toSet()
                    .minus(coordinates)
                    .map { (x, y) -> grid[y][x] }
                    .filterNot { value -> value == '.' }
                    .isNotEmpty()
            }
        }

        private fun String.parseGrid(): List<List<Char>> = lines().map(String::toList)

        private fun String.parsePartNumbers(): List<PartNumber> = lines()
            .flatMapIndexed { y, line ->
                PART_NUMBER_REGEX
                    .findAll(line)
                    .map { PartNumber(it.value.toInt(), it.range to y) }
            }
    }
}
