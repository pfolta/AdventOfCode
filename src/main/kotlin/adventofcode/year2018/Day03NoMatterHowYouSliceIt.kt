package adventofcode.year2018

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.cartesianProduct

class Day03NoMatterHowYouSliceIt(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val claims by lazy { input.lines().map(Claim::invoke) }

    private val fabric by lazy { claims.flatMap(Claim::area).groupingBy { it }.eachCount() }

    override fun partOne() = fabric.count { it.value > 1 }

    override fun partTwo() = claims.first { claim -> claim.area.all { fabric[it] == 1 } }.id

    companion object {
        private val INPUT_REGEX = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

        private data class Claim(
            val id: Int,
            val left: Int,
            val top: Int,
            val width: Int,
            val height: Int,
        ) {
            val area =
                listOf((left until left + width).toList(), (top until top + height).toList())
                    .cartesianProduct()
                    .map { it.first() to it.last() }

            companion object {
                operator fun invoke(input: String): Claim {
                    val (id, left, top, width, height) =
                        INPUT_REGEX
                            .find(input)!!
                            .destructured
                            .toList()
                            .map(String::toInt)
                    return Claim(id, left, top, width, height)
                }
            }
        }
    }
}
