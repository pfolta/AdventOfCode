package adventofcode.common

object Permutations {
    fun <T : Any> Collection<T>.permutations(): List<List<T>> =
        when {
            size <= 1 -> listOf(toList())
            else ->
                drop(1).permutations().flatMap { permutation ->
                    (0..permutation.size).map { index ->
                        permutation.subList(0, index) + first() + permutation.subList(index, permutation.size)
                    }
                }
        }

    fun <T : Any> Collection<T>.powersets(): List<List<T>> =
        when {
            isEmpty() -> listOf(emptyList())
            else -> drop(1).powersets().let { powerSetOfRest -> powerSetOfRest + powerSetOfRest.map { listOf(first()) + it } }
        }
}
