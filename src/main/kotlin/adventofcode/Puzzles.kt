package adventofcode

import org.reflections.Reflections

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
