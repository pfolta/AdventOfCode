package adventofcode.common

import adventofcode.common.Math.divisors
import adventofcode.common.Math.isEven
import adventofcode.common.Math.isOdd
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class MathSpec :
    FreeSpec({
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

        "Number.isEven()" - {
            "returns `true` for an even `Int`" {
                setOf(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20).all { number -> number.isEven() shouldBe true }
            }

            "returns `false` for an odd `Int`" {
                setOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19).all { number -> number.isEven() shouldBe false }
            }

            "returns `true` for an even `Long`" {
                setOf(0L, 2L, 4L, 6L, 8L, 10L, 12L, 14L, 16L, 18L, 20L).all { number -> number.isEven() shouldBe true }
            }

            "returns `false` for an odd `Long`" {
                setOf(1L, 3L, 5L, 7L, 9L, 11L, 13L, 15L, 17L, 19L).all { number -> number.isEven() shouldBe false }
            }
        }

        "Number.isOdd()" - {
            "returns `true` for an odd `Int`" {
                setOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19).all { number -> number.isOdd() shouldBe true }
            }

            "returns `false` for an even `Int`" {
                setOf(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20).all { number -> number.isOdd() shouldBe false }
            }

            "returns `true` for an odd `Long`" {
                setOf(1L, 3L, 5L, 7L, 9L, 11L, 13L, 15L, 17L, 19L).all { number -> number.isOdd() shouldBe true }
            }

            "returns `false` for an even `Long`" {
                setOf(0L, 2L, 4L, 6L, 8L, 10L, 12L, 14L, 16L, 18L, 20L).all { number -> number.isOdd() shouldBe false }
            }
        }
    })
