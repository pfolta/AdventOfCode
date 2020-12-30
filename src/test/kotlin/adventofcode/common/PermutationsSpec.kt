package adventofcode.common

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class PermutationsSpec : FreeSpec({
    "Collection<T>.permutations()" - {
        "returns no permutations for an empty list" {
            emptyList<Any>().permutations() shouldBe listOf(listOf())
        }

        "returns the only permutation for a list of a single element" {
            listOf("A").permutations() shouldContainExactlyInAnyOrder listOf(listOf("A"))
        }

        "returns all permutations for a set of two elements" {
            setOf("A", "B").permutations() shouldContainExactlyInAnyOrder listOf(listOf("A", "B"), listOf("B", "A"))
        }

        "returns all permutations for a list of three elements" {
            listOf(1, 2, 3).permutations() shouldContainExactlyInAnyOrder listOf(
                listOf(1, 2, 3), listOf(1, 3, 2), listOf(2, 1, 3), listOf(2, 3, 1), listOf(3, 1, 2), listOf(3, 2, 1)
            )
        }
    }
})
