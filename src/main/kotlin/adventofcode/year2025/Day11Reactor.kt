package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day11Reactor(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput() =
        input
            .lines()
            .map { line -> line.split(": ", limit = 2) }
            .associate { (fromDevice, toDevices) -> Pair(fromDevice, toDevices.split(" ").toSet()) }

    override fun partOne() = parseInput().countPaths(START)

    companion object {
        private const val START = "you"
        private const val END = "out"

        private fun Map<String, Set<String>>.countPaths(device: String): Long =
            when (device) {
                END -> 1
                else -> this.getValue(device).sumOf { next -> countPaths(next) }
            }
    }
}
