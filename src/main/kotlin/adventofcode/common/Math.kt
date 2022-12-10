package adventofcode.common

import kotlin.math.ceil
import kotlin.math.sqrt

object Math {
    fun Int.divisors() = (1..ceil(sqrt(toDouble())).toInt())
        .filter { this % it == 0 }
        .flatMap { setOf(it, this / it) }
        .toSet()
}
