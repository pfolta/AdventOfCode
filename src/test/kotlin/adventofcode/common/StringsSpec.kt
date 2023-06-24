package adventofcode.common

import io.kotest.core.spec.style.FreeSpec
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

    "md5" - {
        "computes MD5 hash correctly" {
            "test12345".md5() shouldBe "c06db68e819be6ec3d26c6038d8e8d1f"
        }
    }
})
