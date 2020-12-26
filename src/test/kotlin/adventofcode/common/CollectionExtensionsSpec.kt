package adventofcode.common

import io.kotest.core.spec.style.FreeSpec
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
})
