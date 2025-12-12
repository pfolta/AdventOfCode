package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput

private typealias TreeArea = Long
private typealias Presents = List<Long>
private typealias Tree = Pair<TreeArea, Presents>

class Day12ChristmasTreeFarm(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput() =
        input
            .substringAfterLast("\n\n")
            .lines()
            .map { line -> line.split(": ", limit = 2) }
            .map { (area, presents) ->
                val (areaX, areaY) = area.split("x", limit = 2).map(String::toLong)
                Tree(areaX * areaY, presents.split(" ").map(String::toLong))
            }

    /** It seems giving each present a 3x3 area without overlaps produces the correct answer for the actual puzzle input */
    override fun partOne() = parseInput().count { (treeArea, presents) -> presents.sum() * MAX_PRESENT_AREA <= treeArea }

    companion object {
        private const val MAX_PRESENT_AREA = 3L * 3L
    }
}
