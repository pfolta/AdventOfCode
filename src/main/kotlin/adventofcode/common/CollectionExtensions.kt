package adventofcode.common

// Returns every nth element in a list shifted by an optional offset
// e.g. listOf(1, 2, 3, 4).everyNth(2, 1) -> listOf(2, 4)
inline fun <reified T : Any> Collection<T>.everyNth(n: Int, offset: Int = 0) = filterIndexed { index, _ -> index % n == offset }
