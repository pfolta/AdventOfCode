package adventofcode.year2018

import adventofcode.Puzzle

class Day01ChronalCalibration(customInput: String? = null) : Puzzle(customInput) {
    private val frequencies = input.lines().map(String::toInt)

    override fun partOne() = frequencies.sum()

    override fun partTwo(): Int {
        val seen = hashSetOf<Int>()

        return generateSequence(-1 to 0) { previous ->
            seen.add(previous.second)
            val index = (previous.first + 1) % frequencies.size
            index to previous.second + frequencies[index]
        }
            .map { it.second }
            .first(seen::contains)
    }
}
