package adventofcode

import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main(vararg args: String) = args
    .map { it.split("/").map(String::toInt) }
    .flatMap { if (it.size == 2) listOf(Days.forDay(it.first(), it.last())) else Days.forYear(it.first()) }
    .ifEmpty { Days.all() }
    .forEach {
        it.run()
        println()
    }
