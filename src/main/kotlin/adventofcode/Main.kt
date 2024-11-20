package adventofcode

import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main(vararg args: String) =
    args
        .map { arg -> arg.split("/").map(String::toInt) }
        .flatMap { arg ->
            when (arg.size) {
                2 -> listOf(Puzzles.forDay(arg.first(), arg.last()))
                else -> Puzzles.forYear(arg.first())
            }
        }
        .ifEmpty { Puzzles.all() }
        .forEach { puzzle ->
            puzzle.run()
            println()
        }
