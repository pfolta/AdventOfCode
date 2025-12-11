package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput

private typealias Device = String

class Day11Reactor(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput() =
        input
            .lines()
            .map { line -> line.split(": ", limit = 2) }
            .associate { (fromDevice, toDevices) -> Pair(fromDevice, toDevices.split(" ").toSet()) }

    override fun partOne() = parseInput().countPaths(YOU)

    override fun partTwo() = parseInput().countPaths(SVR, mustVisit = setOf(DAC, FFT))

    companion object {
        private const val DAC = "dac"
        private const val END = "out"
        private const val FFT = "fft"
        private const val SVR = "svr"
        private const val YOU = "you"

        private fun Map<Device, Set<Device>>.countPaths(
            device: Device,
            pathCounts: HashMap<Pair<Device, Set<Device>>, Long> = hashMapOf(),
            mustVisit: Set<Device> = emptySet(),
        ): Long =
            pathCounts.getOrPut(Pair(device, mustVisit)) {
                when (device) {
                    END if mustVisit.isEmpty() -> 1
                    END -> 0
                    else -> getValue(device).sumOf { next -> countPaths(next, pathCounts, mustVisit - device) }
                }
            }
    }
}
