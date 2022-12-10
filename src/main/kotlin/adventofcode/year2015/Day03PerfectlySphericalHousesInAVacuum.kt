package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.common.everyNth

class Day03PerfectlySphericalHousesInAVacuum(customInput: String? = null) : Puzzle(customInput) {
    override val name = "Perfectly Spherical Houses in a Vacuum"

    private val instructions by lazy { input.map(Char::toString) }

    override fun partOne() = instructions
        .housesVisited()
        .distinct()
        .count()

    override fun partTwo() = (instructions.everyNth(2).housesVisited() + instructions.everyNth(2, 1).housesVisited())
        .distinct()
        .count()

    companion object {
        private val directions = mapOf(
            "^" to Pair(0, 1),
            "v" to Pair(0, -1),
            ">" to Pair(1, 0),
            "<" to Pair(-1, 0)
        )

        private fun List<String>.housesVisited() = fold(listOf(Pair(0, 0))) { houses, move -> houses + (houses.last() + directions[move]!!) }

        private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = this.first + other.first to this.second + other.second
    }
}
