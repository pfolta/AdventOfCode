package adventofcode

import org.reflections.Reflections
import java.io.FileNotFoundException
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

private val CLASS_NAME_REGEX = """^adventofcode.year(\d+).Day(\d+)(.+)$""".toRegex()

abstract class Day {
    private val year = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component1().toInt()
    private val day = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component2().toInt()
    private val title = CLASS_NAME_REGEX.find(javaClass.name)!!.destructured.component3().replace("([A-Z])".toRegex(), " $1").trim()

    protected val input: String by lazy {
        javaClass.classLoader.getResource("inputs/year$year/day$day.txt")?.readText()?.trim()
            ?: throw FileNotFoundException("Input file for '$this' is missing")
    }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any

    override fun toString() = "Advent of Code $year, Day $day: $title"

    @ExperimentalTime
    fun run() {
        println("Advent of Code $year")
        println("Day $day: $title")
        println("----------------------------------------")

        print("Part 1: ")
        measureTimedValue { partOne() }.let {
            println("${it.value} (${it.duration})")
        }

        print("Part 2: ")
        measureTimedValue { partTwo() }.let {
            println("${it.value} (${it.duration})")
        }
    }
}

object Days {
    private val reflections = Reflections(javaClass.`package`.name)

    private val days = reflections.getSubTypesOf(Day::class.java).sortedBy(Class<out Day>::getName).map { it.kotlin.objectInstance!! }

    fun all() = days

    fun forYear(year: Int) = days
        .filter { it.javaClass.name.startsWith("adventofcode.year$year") }
        .ifEmpty { throw ClassNotFoundException("No solutions exist for year $year") }

    fun forDay(year: Int, day: Int) = days
        .filter { it.javaClass.name.startsWith("adventofcode.year$year.Day${day.toString().padStart(2, '0')}") }
        .ifEmpty { throw ClassNotFoundException("Solution for $year/$day does not exist") }
        .first()
}
