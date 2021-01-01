package adventofcode.year2018

import adventofcode.Puzzle
import adventofcode.common.cartesianProduct
import adventofcode.common.product
import adventofcode.common.removeAt

class Day03NoMatterHowYouSliceIt(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .lines()
        .map {
            val (id, left, top, width, height) = INPUT_REGEX.find(it)!!.destructured
            Claim(id.toInt(), left.toInt(), top.toInt(), width.toInt(), height.toInt())
        }
        .flatMap(Claim::area)
        .groupingBy { it }
        .eachCount()
        .count { it.value > 1 }

    companion object {
        private val INPUT_REGEX = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

        data class Claim(
            val id: Int,
            val left: Int,
            val top: Int,
            val width: Int,
            val height: Int
        ) {
            val area = listOf((left until left + width).toList(), (top until top + height).toList())
                .cartesianProduct()
                .map { it.first() to it.last() }
        }
    }
}
