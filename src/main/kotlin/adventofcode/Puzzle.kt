package adventofcode

import adventofcode.utils.readInputAsText
import org.reflections.Reflections
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

abstract class Puzzle {
    private val year = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component1().toInt()
    private val day = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component2().toInt()
    protected open val title = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component3().replace("([A-Z])".toRegex(), " $1").trim()

    protected val input: String by lazy { readInputAsText(year, day) }

    abstract fun partOne(): Any

    open fun partTwo(): Any? = null

    override fun toString() = "Advent of Code $year, Day $day: $title"

    @ExperimentalTime
    fun run() {
        println("Advent of Code $year")
        println("Day $day: $title")
        println("----------------------------------------")

        measureTimedValue { partOne() }.let { (value, duration) ->
            println("Part 1: $value ($duration)")
        }

        measureTimedValue { partTwo() }.let { (value, duration) ->
            value?.let { println("Part 2: $value ($duration)") }
        }
    }

    companion object {
        private val CLASS_NAME_REGEX = """^adventofcode.year(\d+).Day(\d+)(.+)$""".toRegex()
    }
}

object Puzzles {
    private val reflections = Reflections("adventofcode")

    private val puzzles =
        reflections.getSubTypesOf(Puzzle::class.java).sortedBy(Class<out Puzzle>::getName).map { it.kotlin.objectInstance!! }

    fun all() = puzzles

    fun forYear(year: Int) = puzzles
        .filter { it.javaClass.name.startsWith("adventofcode.year$year") }
        .ifEmpty { throw ClassNotFoundException("Puzzles for year $year not found") }

    fun forDay(year: Int, day: Int) = puzzles
        .filter { it.javaClass.name.startsWith("adventofcode.year$year.Day${day.toString().padStart(2, '0')}") }
        .ifEmpty { throw ClassNotFoundException("Solution for puzzle $year/$day not found") }
        .first()
}
