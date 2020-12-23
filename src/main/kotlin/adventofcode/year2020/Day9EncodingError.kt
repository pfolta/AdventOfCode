package adventofcode.year2020

import adventofcode.year2020.Day9EncodingError.part1
import adventofcode.year2020.Day9EncodingError.part2
import adventofcode.utils.readInputAsLines

object Day9EncodingError {
    fun part1(input: List<Long>, preambleSize: Int) = (preambleSize until input.size)
        .filter { index ->
            val preamble = input.subList(index - preambleSize, index)

            preamble
                .flatMap { fst -> preamble.map { snd -> listOf(fst, snd) } }
                .none { it.sum() == input[index] }
        }
        .map(input::get)
        .first()

    fun part2(input: List<Long>, searchPattern: Long) = (2..input.size)
        .flatMap { size -> (0..input.size - size).map { input.subList(it, it + size) } }
        .filter { it.sum() == searchPattern }
        .map { it.minOrNull()!! + it.maxOrNull()!! }
        .first()
}

fun main() {
    val input = readInputAsLines(2020, 9).map(String::toLong)

    val part1 = part1(input, 25)

    println("Part 1: $part1")
    println("Part 2: ${part2(input, part1)}")
}
