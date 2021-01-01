package adventofcode.year2018

import adventofcode.Puzzle
import adventofcode.common.cartesianProduct
import adventofcode.common.product
import adventofcode.common.removeAt

class Day03NoMatterHowYouSliceIt(customInput: String? = null) : Puzzle(customInput) {
    private val claims by lazy {
        input
            .lines()
            .map {
                val (id, left, top, width, height) = INPUT_REGEX.find(it)!!.destructured
                Claim(id.toInt(), left.toInt(), top.toInt(), width.toInt(), height.toInt())
            }
    }

    private val fabric by lazy { claims.flatMap(Claim::area).groupingBy { it }.eachCount() }

    override fun partOne() = fabric.count { it.value > 1 }

    override fun partTwo() = claims.first { claim -> claim.area.all { fabric[it] == 1 } }.id

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
