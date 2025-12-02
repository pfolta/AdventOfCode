package adventofcode.common

import kotlin.math.ceil
import kotlin.math.sqrt

object Math {
    /** Returns a set of all divisors (factors) of this `Int`. */
    fun Int.divisors(): Set<Int> =
        (1..ceil(sqrt(toDouble())).toInt())
            .filter { this % it == 0 }
            .flatMap { setOf(it, this / it) }
            .toSet()

    fun Number.isEven(): Boolean = toLong().mod(2) == 0

    fun Number.isOdd(): Boolean = !isEven()
}
