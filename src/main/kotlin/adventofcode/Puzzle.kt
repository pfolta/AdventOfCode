package adventofcode

import adventofcode.util.bold
import adventofcode.util.formatBenchmark
import adventofcode.util.formatUri
import java.io.FileNotFoundException
import java.net.URI
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

typealias PuzzleInput = String
typealias PuzzleOutput = Any

abstract class Puzzle(customInput: PuzzleInput?) {
    val year = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component1().toInt()
    val day = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component2().toInt()
    private val link = URI("https://adventofcode.com/$year/day/$day")

    /**
     * Name of the puzzle.
     * Inferred from the puzzle's class name by default but can be overridden (e.g. if puzzle name contains special characters).
     */
    protected open val name = CLASS_NAME_REGEX
        .find(javaClass.name)!!
        .destructured
        .component3()
        .replace("""([A-Z]|\d+)""".toRegex(), " $1")
        .trim()

    /**
     * Puzzle input as String.
     * Uses default input file if custom input is not provided.
     *
     * lazy delegate ensures the input file is only read on first access
     */
    protected val input by lazy {
        customInput
            ?: javaClass.classLoader.getResource("inputs/year$year/day${day.toString().padStart(2, '0')}.txt")?.readText()
            ?: throw FileNotFoundException("Input file for puzzle $year/$day not found")
    }

    /**
     * Solves part one of the puzzle.
     */
    abstract fun partOne(): PuzzleOutput

    /**
     * Solves part two of the puzzle.
     * Don't override this function if there is no part two to solve.
     */
    open fun partTwo(): PuzzleOutput? = null

    /**
     * Prints solutions for part one and part two (if it exists) of the puzzle.
     * Provides timings for both parts.
     */
    @ExperimentalTime
    fun run() {
        """
        🎄 Advent of Code $year, Day $day
           ${name.bold()}
           ${link.formatUri()}
        """.trimIndent().also(::println)

        measureTimedValue { partOne() }.let { (value, duration) ->
            println("⭐️ Part 1: ${value.toString().bold()} (${duration.formatBenchmark()})")
        }

        measureTimedValue { partTwo() }.let { (value, duration) ->
            value?.let { println("⭐️ Part 2: ${value.toString().bold()} (${duration.formatBenchmark()})") }
        }
    }

    override fun toString() = "🎄 Advent of Code $year, Day $day: $name"

    companion object {
        private val CLASS_NAME_REGEX = """^adventofcode.year(\d{4}).Day(\d{2})(.+)$""".toRegex()
    }
}
