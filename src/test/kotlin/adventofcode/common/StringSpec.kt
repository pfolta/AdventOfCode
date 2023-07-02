package adventofcode.common

import adventofcode.common.String.containsOnlyDigits
import adventofcode.common.String.md5
import adventofcode.common.String.removeAt
import adventofcode.common.String.replaceAt
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class StringSpec : FreeSpec({
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

    "containsOnlyDigits" - {
        "returns `true` for a String containing only digits" {
            "0513215".containsOnlyDigits() shouldBe true
        }

        "returns `false` for a String containing other characters" {
            "445hjk".containsOnlyDigits() shouldBe false
        }

        "returns `false` for a String containing a negative number" {
            "-1234".containsOnlyDigits() shouldBe false
        }

        "returns `false` for a non-integer number" {
            "123.456".containsOnlyDigits() shouldBe false
        }
    }
})
