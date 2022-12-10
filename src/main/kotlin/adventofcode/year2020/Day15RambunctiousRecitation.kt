package adventofcode.year2020

import adventofcode.Puzzle

class Day15RambunctiousRecitation(customInput: String? = null) : Puzzle(customInput) {
    private val startingNumbers by lazy { input.split(",").map(String::toInt) }

    override fun partOne() = startingNumbers.playGame(2020)

    override fun partTwo() = startingNumbers.playGame(30_000_000)

    companion object {
        fun List<Int>.playGame(turnCount: Int) = (1..turnCount)
            .fold(Pair(mutableMapOf<Int, List<Int>>(), -1)) { (memoryMap, lastNumberSpoken), turn ->
                if (turn <= size) {
                    memoryMap[this[turn - 1]] = listOfNotNull(memoryMap.getOrDefault(this[turn - 1], emptyList()).lastOrNull(), turn)
                    memoryMap to this[turn - 1]
                } else {
                    if (memoryMap[lastNumberSpoken]!!.size == 1) {
                        memoryMap[0] = listOfNotNull(memoryMap.getOrDefault(0, emptyList()).lastOrNull(), turn)
                        memoryMap to 0
                    } else {
                        val age = memoryMap[lastNumberSpoken]!!.last() - memoryMap[lastNumberSpoken]!!.first()
                        memoryMap[age] = listOfNotNull(memoryMap.getOrDefault(age, emptyList()).lastOrNull(), turn)
                        memoryMap to age
                    }
                }
            }
            .second
    }
}
