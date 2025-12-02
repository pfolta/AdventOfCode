package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.min

class Day14ReindeerOlympics(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val reindeer by lazy { input.lines().map(Reindeer::invoke) }

    override fun partOne() = reindeer.maxOfOrNull(Reindeer::distanceFlown) ?: 0

    override fun partTwo() =
        (1..CHECKPOINT)
            .map { time -> reindeer.associateWith { it.distanceFlown(time) }.maxByOrNull { it.value }!!.key }
            .groupingBy { it }
            .eachCount()
            .map { it.value }
            .maxOrNull() ?: 0

    companion object {
        private val INPUT_REGEX = """(\w+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".toRegex()

        private const val CHECKPOINT = 2503

        private data class Reindeer(
            val name: String,
            val flySpeed: Int,
            val flyTime: Int,
            val restTime: Int,
        ) {
            fun distanceFlown(after: Int = CHECKPOINT): Int {
                val fullCycles = after / (flyTime + restTime)
                val remainingTime = after % (flyTime + restTime)

                return (fullCycles * flyTime * flySpeed) + min(remainingTime, flyTime) * flySpeed
            }

            companion object {
                operator fun invoke(input: String): Reindeer {
                    val (name, flySpeed, flyTime, restTime) = INPUT_REGEX.find(input)!!.destructured
                    return Reindeer(name, flySpeed.toInt(), flyTime.toInt(), restTime.toInt())
                }
            }
        }
    }
}
