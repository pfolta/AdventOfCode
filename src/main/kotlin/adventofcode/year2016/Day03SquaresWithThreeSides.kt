package adventofcode.year2016

import adventofcode.Puzzle

class Day03SquaresWithThreeSides(customInput: String? = null) : Puzzle(customInput) {
    private val triangles by lazy {
        input.lines().map { line -> TRIANGLE_REGEX.find(line)!!.destructured }.map { (a, b, c) -> Triple(a.toInt(), b.toInt(), c.toInt()) }
    }

    override fun partOne() = triangles
        .count { (a, b, c) -> listOf(a, b, c).all { listOf(a, b, c).minus(it).sum() > it } }

    companion object {
        private val TRIANGLE_REGEX = """(\d+)\s*(\d+)\s*(\d+)""".toRegex()
    }
}
