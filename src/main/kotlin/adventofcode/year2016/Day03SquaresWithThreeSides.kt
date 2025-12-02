package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day03SquaresWithThreeSides(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val triangles by lazy {
        input.lines().map { line -> TRIANGLE_REGEX.find(line)!!.destructured }.map { (a, b, c) -> listOf(a.toInt(), b.toInt(), c.toInt()) }
    }

    override fun partOne() =
        triangles
            .count { sides -> sides.all { side -> sides.minus(side).sum() > side } }

    override fun partTwo() =
        triangles
            .chunked(3)
            .flatMap { row -> List(row.size) { index -> row.map { column -> column[index] } } }
            .count { sides -> sides.all { side -> sides.minus(side).sum() > side } }

    companion object {
        private val TRIANGLE_REGEX = """(\d+)\s*(\d+)\s*(\d+)""".toRegex()
    }
}
