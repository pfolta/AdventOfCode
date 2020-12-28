package adventofcode.year2015

import adventofcode.Puzzle
import kotlin.math.min

class Day14ReindeerOlympics(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .lines()
        .map(::Reindeer)
        .map {
            val fullCycles = CHECKPOINT / (it.flyTime + it.restTime)
            val remainingTime = CHECKPOINT % (it.flyTime + it.restTime)

            (fullCycles * it.flyTime * it.flySpeed) + min(remainingTime, it.flyTime) * it.flySpeed
        }
        .maxOrNull() ?: 0

    companion object {
        private val INPUT_REGEX = """(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".toRegex()

        private const val CHECKPOINT = 2503

        data class Reindeer(
            val name: String,
            val flySpeed: Int,
            val flyTime: Int,
            val restTime: Int
        ) {
            constructor(input: String) : this(
                INPUT_REGEX.find(input)!!.destructured.component1(),
                INPUT_REGEX.find(input)!!.destructured.component2().toInt(),
                INPUT_REGEX.find(input)!!.destructured.component3().toInt(),
                INPUT_REGEX.find(input)!!.destructured.component4().toInt(),
            )
        }
    }
}
