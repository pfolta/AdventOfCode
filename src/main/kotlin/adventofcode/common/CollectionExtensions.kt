package adventofcode.common

// Returns every nth element in a list shifted by an optional offset
// e.g. listOf(1, 2, 3, 4).everyNth(2, 1) -> listOf(2, 4)
inline fun <reified T : Any> Collection<T>.everyNth(n: Int, offset: Int = 0) = filterIndexed { index, _ -> index % n == offset }

// Returns the product of all elements in a Int/Long collection, similar to sum()
fun Collection<Int>.product() = reduce { product, factor -> product * factor }
fun Collection<Long>.product() = reduce { product, factor -> product * factor }

fun <T : Any> List<T>.permutations(): List<List<T>> =
    if (size <= 1) listOf(this)
    else drop(1).permutations().flatMap { permutation ->
        (0..permutation.size).map { index -> permutation.subList(0, index) + first() + permutation.subList(index, permutation.size) }
    }
