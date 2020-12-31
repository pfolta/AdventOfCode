package adventofcode.common

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class StringsSpec : FreeSpec({
    "replaceAt" - {
        "replaces a single character at the specified index" {
            "abcdef".replaceAt(2, 'X') shouldBe "abXdef"
        }
    }

    "removeAt" - {
        "removes one character at the specified index" {
            "abcdef".removeAt(2) shouldBe "abdef"
        }

        "removes a slice of given length at the specified index" {
            "abcdef".removeAt(1, 2) shouldBe "adef"
        }
    }
})
