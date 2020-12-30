package adventofcode.common

fun <T : Any> Collection<T>.permutations(): List<List<T>> =
    if (size <= 1) listOf(toList())
    else drop(1).permutations().flatMap { permutation ->
        (0..permutation.size).map { index -> permutation.subList(0, index) + first() + permutation.subList(index, permutation.size) }
    }
