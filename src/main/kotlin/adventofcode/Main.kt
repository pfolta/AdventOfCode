package adventofcode

import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main(vararg args: String) = args
    .map { it.split("/").map(String::toInt) }
    .flatMap { if (it.size == 2) listOf(Puzzles.forDay(it.first(), it.last())) else Puzzles.forYear(it.first()) }
    .ifEmpty { Puzzles.all() }
    .forEach { puzzle ->
        puzzle.run()
        println()
    }
