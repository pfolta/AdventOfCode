package adventofcode

import adventofcode.utils.readInputAsText
import org.reflections.Reflections
import java.net.URL
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

abstract class Puzzle(customInput: String?) {
    private val year = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component1().toInt()
    private val day = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component2().toInt()
    private val link = URL("https://adventofcode.com/$year/day/$day")

    /**
     * Name of the puzzle.
     * Inferred from the puzzle's class name by default but can be overridden (e.g. if puzzle name contains special characters).
     */
    protected open val name = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component3().replace("([A-Z])".toRegex(), " $1").trim()

    /**
     * Puzzle input as String.
     * Uses default input file if custom input is not provided.
     *
     * lazy delegate ensures the input file is only read when first accessed
     */
    protected val input by lazy { customInput ?: readInputAsText(year, day) }

    /**
     * Solves part one of the puzzle.
     */
    abstract fun partOne(): Any

    /**
     * Solves part two of the puzzle.
     * Don't override this function if there is no part two to solve.
     */
    open fun partTwo(): Any? = null

    /**
     * Prints solutions for part one and part two (if it exists) of the puzzle.
     * Provides timings for both parts.
     */
    @ExperimentalTime
    fun run() {
        println("Advent of Code $year")
        println("Day $day: $name")
        println(link)
        println("----------------------------------------")

        measureTimedValue { partOne() }.let { (value, duration) ->
            println("Part 1: $value ($duration)")
        }

        measureTimedValue { partTwo() }.let { (value, duration) ->
            value?.let { println("Part 2: $value ($duration)") }
        }
    }

    override fun toString() = "Advent of Code $year, Day $day: $name"

    companion object {
        private val CLASS_NAME_REGEX = """^adventofcode.year(\d+).Day(\d+)(.+)$""".toRegex()
    }
}

object Puzzles {
    private val reflections = Reflections("adventofcode")

    private val puzzles = reflections
        .getSubTypesOf(Puzzle::class.java)
        .sortedBy(Class<out Puzzle>::getName)
        .map { it.getDeclaredConstructor().newInstance()!! }

    fun all() = puzzles

    fun forYear(year: Int) = puzzles
        .filter { it.javaClass.name.startsWith("adventofcode.year$year") }
        .ifEmpty { throw ClassNotFoundException("Puzzles for year $year not found") }

    fun forDay(year: Int, day: Int) = puzzles
        .filter { it.javaClass.name.startsWith("adventofcode.year$year.Day${day.toString().padStart(2, '0')}") }
        .ifEmpty { throw ClassNotFoundException("Solution for puzzle $year/$day not found") }
        .first()
}
