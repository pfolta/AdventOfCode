package adventofcode.common

object Tuple {
    operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = first + other.first to second + other.second

    operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>) = first - other.first to second - other.second
}

enum class Direction(val delta: Pair<Int, Int>) {
    NORTH(0 to -1),
    EAST(1 to 0),
    SOUTH(0 to 1),
    WEST(-1 to 0),
}
