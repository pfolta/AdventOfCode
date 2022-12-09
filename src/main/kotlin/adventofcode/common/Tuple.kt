package adventofcode.common

object Tuple {
    operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = first + other.first to second + other.second
    operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>) = first - other.first to second - other.second
}
