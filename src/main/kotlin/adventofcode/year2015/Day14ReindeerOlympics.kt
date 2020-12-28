package adventofcode.year2015

import adventofcode.Puzzle
import kotlin.math.min

class Day14ReindeerOlympics(customInput: String? = null) : Puzzle(customInput) {
    private val reindeer = input.lines().map(::Reindeer)

    override fun partOne() = reindeer.map(Reindeer::distanceFlown).maxOrNull() ?: 0

    override fun partTwo() = (1..CHECKPOINT)
        .map { time -> reindeer.associateWith { it.distanceFlown(time) }.maxByOrNull { it.value }!!.key }
        .groupingBy { it }
        .eachCount()
        .map { it.value }
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

            fun distanceFlown(after: Int = CHECKPOINT): Int {
                val fullCycles = after / (flyTime + restTime)
                val remainingTime = after % (flyTime + restTime)

                return (fullCycles * flyTime * flySpeed) + min(remainingTime, flyTime) * flySpeed
            }
        }
    }
}
