package adventofcode

import org.reflections.Reflections

object Puzzles {
    private val puzzles = Reflections(Puzzle::class.java)
        .getSubTypesOf(Puzzle::class.java)
        .sortedBy(Class<out Puzzle>::getName)
        .map { it.getDeclaredConstructor().newInstance()!! }

    fun all() = puzzles

    fun forYear(year: Int) = puzzles
        .filter { puzzle -> puzzle.year == year }
        .ifEmpty { throw ClassNotFoundException("Puzzles for year $year not found") }

    fun forDay(year: Int, day: Int) = puzzles
        .filter { puzzle -> puzzle.year == year && puzzle.day == day }
        .ifEmpty { throw ClassNotFoundException("Puzzle $year/$day not found") }
        .first()
}
