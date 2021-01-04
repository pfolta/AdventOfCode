package adventofcode.common

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MathSpec : FreeSpec({
    "Int.divisors()" - {
        "returns no divisors for 0" {
            (0).divisors() shouldBe emptySet()
        }

        "returns the only divisor for 1" {
            (1).divisors() shouldBe setOf(1)
        }

        "returns all divisors for 36" {
            (36).divisors() shouldBe setOf(1, 2, 3, 4, 6, 9, 12, 18, 36)
        }

        "returns only 1 and itself as divisors for a prime number" {
            (47).divisors() shouldBe setOf(1, 47)
        }

        "returns all divisors for 100" {
            (100).divisors() shouldBe setOf(1, 2, 4, 5, 10, 20, 25, 50, 100)
        }
    }
})
