package adventofcode.year2019

import adventofcode.year2019.Day21202ProgramAlarm.part1
import adventofcode.year2019.Day21202ProgramAlarm.part2
import adventofcode.utils.readInputAsText

object Day21202ProgramAlarm {
    fun part1(intcode: MutableList<Int>) = (0..intcode.size step 4)
        .fold (intcode.first()) { _, index ->
            when (intcode[index]) {
                1 -> intcode[intcode[index + 3]] = intcode[intcode[index + 1]] + intcode[intcode[index + 2]]
                2 -> intcode[intcode[index + 3]] = intcode[intcode[index + 1]] * intcode[intcode[index + 2]]
                99 -> return intcode.first()
            }
            intcode.first()
        }

    fun part2(intcode: List<Int>, nounRange: IntRange, verbRange: IntRange, output: Int) = nounRange
        .flatMap { noun ->
            verbRange.map { verb ->
                Triple(noun, verb, (listOf(intcode.first()) + listOf(noun, verb) + intcode.subList(3, intcode.size)).toMutableList())
            }
        }
        .filter { part1(it.third) == output }
        .map { 100 * it.first + it.second }
        .first()
}

fun main() {
    val intcode = readInputAsText(2019, 2).split(",").map { it.toInt() }

    val part1 = part1((listOf(intcode.first()) + listOf(12, 2) + intcode.subList(3, intcode.size)).toMutableList())
    val part2 = part2(intcode, 0..99, 0..99, 19690720)

    println("Part 1: $part1")
    println("Part 2: $part2")
}
