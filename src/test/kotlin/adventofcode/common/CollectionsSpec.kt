package adventofcode.common

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class CollectionsSpec : FreeSpec({
    "everyNth" - {
        "returns a collection that contains every nth element" {
            listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).everyNth(2) shouldContainExactly listOf(0, 2, 4, 6, 8)
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9).everyNth(3) shouldContainExactly listOf(1, 4, 7)

            listOf("The", "quick", "brown", "fox").everyNth(2) shouldContainExactly listOf("The", "brown")
        }

        "supports specifying an optional offset" {
            listOf(1, 2, 3, 4).everyNth(2, 1) shouldContainExactly listOf(2, 4)
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9).everyNth(3, 2) shouldContainExactly listOf(3, 6, 9)
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

    "transpose" - {
        "rotates a collection of collections 90deg clockwise" {
            listOf(
                listOf('A', 'B', 'C'),
                listOf('D', 'E', 'F'),
                listOf('G', 'H', 'I')
            ).transpose() shouldContainExactly listOf(
                listOf('G', 'D', 'A'),
                listOf('H', 'E', 'B'),
                listOf('I', 'F', 'C')
            )
        }
    }
})
