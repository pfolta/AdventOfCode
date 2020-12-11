package adventofcode.year2020

import adventofcode.year2020.Day10AdapterArray.part1
import adventofcode.year2020.Day10AdapterArray.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day10AdapterArraySpec : FreeSpec({
    "Day 10: Adapter Array" - {
        val exampleInput = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)
        val largeExampleInput = listOf(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3)

        "Part 1" - {
            "Example" {
                part1(exampleInput) shouldBe 35
            }

            "Larger Example" {
                part1(largeExampleInput) shouldBe 220
            }
        }

        "Part 2" - {
            "Example" {
                part2(exampleInput) shouldBe 8
            }

            "Larger Example" {
                part2(largeExampleInput) shouldBe 19208
            }
        }
    }
})
