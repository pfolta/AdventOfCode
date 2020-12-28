package adventofcode.common

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class CollectionExtensionsSpec : FreeSpec({
    "everyNth" - {
        "returns a collection that contains every nth element" {
            listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).everyNth(2) shouldBe listOf(0, 2, 4, 6, 8)
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9).everyNth(3) shouldBe listOf(1, 4, 7)

            listOf("The", "quick", "brown", "fox").everyNth(2) shouldBe listOf("The", "brown")
        }

        "supports specifying an optional offset" {
            listOf(1, 2, 3, 4).everyNth(2, 1) shouldBe listOf(2, 4)
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9).everyNth(3, 2) shouldBe listOf(3, 6, 9)
        }
    }

    "product" - {
        "returns the product of a collection of Ints" {
            listOf(1, 2, 3, 4, 5, 6).product() shouldBe 720
        }

        "returns the product of a collection of Longs" {
            listOf(1L, 2L, 3L, 4L, 5L).product() shouldBe 120L
        }
    }

    "permutations" - {
        "returns no permutations for an empty list" {
            emptyList<Any>().permutations() shouldBe listOf(listOf())
        }

        "returns the only permutation for a list of a single element" {
            listOf("A").permutations() shouldContainExactlyInAnyOrder listOf(listOf("A"))
        }

        "returns all permutations for a list of two elements" {
            listOf("A", "B").permutations() shouldContainExactlyInAnyOrder listOf(listOf("A", "B"), listOf("B", "A"))
        }

        "returns all permutations for a list of three elements" {
            listOf(1, 2, 3).permutations() shouldContainExactlyInAnyOrder listOf(
                listOf(1, 2, 3), listOf(1, 3, 2), listOf(2, 1, 3), listOf(2, 3, 1), listOf(3, 1, 2), listOf(3, 2, 1)
            )
        }
    }
})
