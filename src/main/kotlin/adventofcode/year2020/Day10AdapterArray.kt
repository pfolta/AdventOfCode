package adventofcode.year2020

import adventofcode.Day

object Day10AdapterArray : Day() {
    private val sortedInput = input.lines().map(String::toInt).sorted()

    override fun partOne() = (sortedInput + listOf(sortedInput.last() + 3))
        .foldIndexed(emptyMap<Int, Int>()) { index, acc, elem ->
            val previous = sortedInput.getOrElse(index - 1) { 0 }
            acc + mapOf(elem - previous to (acc[elem - previous] ?: 0) + 1)
        }
        .values
        .reduce { product, factor -> product * factor }

    override fun partTwo(): Long {
        val k = longArrayOf(1, 0, 0, 0)

        (sortedInput + listOf(sortedInput.last() + 3))
            .fold(0) { a, b ->
                val d = b - a
                k.copyInto(k, d, 0, k.size - d)
                k.fill(0, 0, d)
                k[0] += k.sum()
                b
            }

        return k[0]
    }
}
