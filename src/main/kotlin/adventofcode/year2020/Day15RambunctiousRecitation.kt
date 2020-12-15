package adventofcode.year2020

import adventofcode.year2020.Day15RambunctiousRecitation.part1
import adventofcode.year2020.Day15RambunctiousRecitation.part2
import adventofcode.utils.readInputAsString

object Day15RambunctiousRecitation {
    private fun playGame(input: List<Int>, turnCount: Int) = (1..turnCount)
        .fold(Pair(mutableMapOf<Int, List<Int>>(), -1)) { (memoryMap, lastNumberSpoken), turn ->
            if (turn <= input.size) {
                memoryMap[input[turn - 1]] = listOfNotNull(memoryMap.getOrDefault(input[turn - 1], emptyList()).lastOrNull(), turn)
                Pair(memoryMap, input[turn - 1])
            } else {
                if (memoryMap[lastNumberSpoken]!!.size == 1) {
                    memoryMap[0] = listOfNotNull(memoryMap.getOrDefault(0, emptyList()).lastOrNull(), turn)
                    Pair(memoryMap, 0)
                } else {
                    val age = memoryMap[lastNumberSpoken]!!.last() - memoryMap[lastNumberSpoken]!!.first()
                    memoryMap[age] = listOfNotNull(memoryMap.getOrDefault(age, emptyList()).lastOrNull(), turn)
                    Pair(memoryMap, age)
                }
            }
        }
        .second

    fun part1(input: List<Int>) = playGame(input, 2020)

    fun part2(input: List<Int>) = playGame(input, 30_000_000)
}

fun main() {
    val input = readInputAsString(2020, 15).split(",").map(String::toInt)

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
