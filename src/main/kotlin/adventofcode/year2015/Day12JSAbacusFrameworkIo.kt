package adventofcode.year2015

import adventofcode.Puzzle

class Day12JSAbacusFrameworkIo(customInput: String? = null) : Puzzle(customInput) {
    override val name = "JSAbacusFramework.io"

    override fun partOne() = NUMBER_REGEX.findAll(input).map { it.value.toInt() }.sum()

    companion object {
        private val NUMBER_REGEX = """(-?\d+)""".toRegex()
    }
}
