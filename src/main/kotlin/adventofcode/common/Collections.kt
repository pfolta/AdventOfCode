package adventofcode.common

// Returns every nth element in a list shifted by an optional offset
// e.g. listOf(1, 2, 3, 4).everyNth(2, 1) -> listOf(2, 4)
inline fun <reified T : Any> Collection<T>.everyNth(n: Int, offset: Int = 0) = filterIndexed { index, _ -> index % n == offset }

// Returns the product of all elements in a Int/Long collection, similar to sum()
fun Collection<Int>.product() = reduce { product, factor -> product * factor }
fun Collection<Long>.product() = reduce { product, factor -> product * factor }

/**
 * Returns the cartesian product of a collection of collections
 */
inline fun <reified T : Any> Collection<Collection<T>>.cartesianProduct() =
    fold(listOf(listOf<T>())) { product, list -> product.flatMap { subList -> list.map { element -> subList + element } } }

/**
 * Returns a set of valid neighbors in any 2D grid
 *
 * Excluding diagonals:           Including diagonals:
 * _ N _                          N N N
 * N P N                          N P N
 * _ N _                          N N N
 * where N is a neighbor of P.
 */
inline fun <reified T : Any> List<List<T>>.neighbors(x: Int, y: Int, includingDiagonals: Boolean): Set<Pair<Int, Int>> {
    val candidates = if (includingDiagonals)
        setOf(
            x - 1 to y - 1, x to y - 1, x + 1 to y - 1,
            x - 1 to y, x + 1 to y,
            x - 1 to y + 1, x to y + 1, x + 1 to y + 1
        )
    else
        setOf(
            x to y - 1,
            x - 1 to y, x + 1 to y,
            x to y + 1
        )

    return candidates
        .filter { it.first >= 0 && it.second >= 0 }
        .filter { it.first < this[y].size && it.second < size }
        .toSet()
}

