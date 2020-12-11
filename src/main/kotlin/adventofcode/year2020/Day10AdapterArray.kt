package adventofcode.year2020

import adventofcode.year2020.Day10AdapterArray.part1
import adventofcode.year2020.Day10AdapterArray.part2
import adventofcode.utils.readInputAsLines

object Day10AdapterArray {
    fun part1(input: List<Int>): Int {
        val sortedInput = input.sorted() + listOf(input.max()!! + 3)

        return sortedInput
            .foldIndexed(emptyMap<Int, Int>()) { index, acc, elem ->
                val previous = sortedInput.getOrElse(index - 1) { 0 }
                acc + mapOf(elem - previous to (acc[elem - previous] ?: 0) + 1)
            }
            .values
            .reduce { product, factor -> product * factor }
    }

    fun part2(input: List<Int>): Long {
        val sortedInput = input.sorted() + listOf(input.max()!! + 3)

        val k = longArrayOf(1, 0, 0, 0)
        sortedInput.fold(0) { a, b ->
            val d = b - a
            k.copyInto(k, d, 0, k.size - d)
            k.fill(0, 0, d)
            k[0] += k.sum()
            b
        }
        return k[0]
    }
}

fun main() {
    val input = readInputAsLines(2020, 10).map { it.toInt() }

    val part1 = part1(input)
    val part2 = part2(input)

    println("Part 1: $part1")
    println("Part 2: $part2")
}
