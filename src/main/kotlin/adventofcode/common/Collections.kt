package adventofcode.common

/**
 *
 * Returns every nth element in a list shifted by an optional offset.
 *
 * e.g. listOf(1, 2, 3, 4).everyNth(2, 1) -> listOf(2, 4)
 */
inline fun <reified T : Any?> Collection<T>.everyNth(
    n: Int,
    offset: Int = 0,
) = filterIndexed { index, _ -> index % n == offset }

/** Returns the product of all elements in a Int/Long collection, similar to sum(). */
fun Collection<Int>.product() = reduce(Int::times)

fun Collection<Long>.product() = reduce(Long::times)

/** Returns the cartesian product of a collection of collections. */
inline fun <reified T : Any?> Collection<Collection<T>>.cartesianProduct() =
    fold(listOf(listOf<T>())) { product, list -> product.flatMap { subList -> list.map { element -> subList + element } } }

/**
 * Returns a set of valid neighbors in any 2D grid
 *
 * Excluding diagonals:     Including diagonals:
 * _ N _                    N N N
 * N P N                    N P N
 * _ N _                    N N N
 * where N is a neighbor of P.
 */
inline fun <reified T : Any?> List<List<T>>.neighbors(
    x: Int,
    y: Int,
    includeDiagonals: Boolean,
): Set<Pair<Int, Int>> =
    when {
        includeDiagonals ->
            setOf(
                x - 1 to y - 1,
                x to y - 1,
                x + 1 to y - 1,
                x - 1 to y,
                x + 1 to y,
                x - 1 to y + 1,
                x to y + 1,
                x + 1 to y + 1,
            )

        else ->
            setOf(
                x to y - 1,
                x - 1 to y,
                x + 1 to y,
                x to y + 1,
            )
    }.filter { it.first >= 0 && it.second >= 0 }
        .filter { it.first < this[y].size && it.second < size }
        .toSet()

/**
 * Transposes a 2D collection by rotating it 90deg clockwise:
 *
 * Original collection:     After transposing:
 * A B C                    G D A
 * D E F                    H E B
 * G H I                    I F C
 */
fun <T> Collection<Collection<T>>.transpose(): List<List<T>> {
    val result = first().map { mutableListOf<T>() }
    forEach { list -> result.zip(list).forEach { it.first.add(it.second) } }
    return result.map(List<T>::reversed)
}

/**
 * Swaps two elements in a list, identified by index
 * e.g. listOf(1, 2, 3, 4, 5).swap(1, 3) -> listOf(1, 4, 3, 2, 5)
 */
inline fun <reified T : Any?> List<T>.swap(
    aIndex: Int,
    bIndex: Int,
) = subList(0, aIndex) + this[bIndex] + subList(aIndex + 1, bIndex) + this[aIndex] + subList(bIndex + 1, size)
