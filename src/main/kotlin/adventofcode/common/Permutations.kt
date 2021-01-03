package adventofcode.common

fun <T : Any> Collection<T>.permutations(): List<List<T>> =
    if (size <= 1) listOf(toList())
    else drop(1).permutations().flatMap { permutation ->
        (0..permutation.size).map { index -> permutation.subList(0, index) + first() + permutation.subList(index, permutation.size) }
    }

fun <T : Any> Collection<T>.powersets(): List<List<T>> =
    if (isEmpty()) listOf(emptyList())
    else {
        val powersetOfRest = drop(1).powersets()
        powersetOfRest + powersetOfRest.map { listOf(first()) + it }
    }
