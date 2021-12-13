package adventofcode.year2021

import adventofcode.Puzzle

class Day13TransparentOrigami(customInput: String? = null) : Puzzle(customInput) {
    private val paper by lazy {
        val dots = input.split("\n\n").first().lines().map { it.split(",").first().toInt() to it.split(",").last().toInt() }.toSet()
        val maxX = dots.maxOf { it.first }
        val maxY = dots.maxOf { it.second }

        (0..maxY).map { y -> (0..maxX).map { x -> dots.contains(x to y) } }
    }

    private val instructions by lazy {
        input.split("\n\n").last().lines().map { it.split("=").first().last() to it.split("=").last().toInt() }
    }

    private fun List<List<Boolean>>.fold(instruction: Pair<Char, Int>) = when (instruction.first) {
        'x' -> foldLeft(instruction.second)
        'y' -> foldUp(instruction.second)
        else -> throw IllegalArgumentException("${instruction.first} is not a valid fold direction")
    }

    private fun List<List<Boolean>>.foldLeft(along: Int) = map { it.take(along) }
        .zip(map { it.takeLast(along).reversed() })
        .map { it.first.zip(it.second) }
        .map { it.map { it.first || it.second } }

    private fun List<List<Boolean>>.foldUp(along: Int) = take(along)
        .zip(takeLast(along).reversed())
        .map { it.first.zip(it.second) }
        .map { it.map { it.first || it.second } }

    override fun partOne() = paper
        .fold(instructions.first())
        .flatten()
        .count { it }
}
