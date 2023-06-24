package adventofcode.common

import adventofcode.common.Math.divisors
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class MathSpec : FreeSpec({
    "Int.divisors()" - {
        "returns no divisors for 0" {
            0.divisors().shouldBeEmpty()
        }

        "returns the only divisor for 1" {
            1.divisors() shouldContainExactlyInAnyOrder setOf(1)
        }

        "returns all divisors for 36" {
            36.divisors() shouldContainExactlyInAnyOrder setOf(1, 2, 3, 4, 6, 9, 12, 18, 36)
        }

        "returns only 1 and itself as divisors for a prime number" {
            47.divisors() shouldContainExactlyInAnyOrder setOf(1, 47)
        }

        "returns all divisors for 100" {
            100.divisors() shouldContainExactlyInAnyOrder setOf(1, 2, 4, 5, 10, 20, 25, 50, 100)
        }
    }
})
