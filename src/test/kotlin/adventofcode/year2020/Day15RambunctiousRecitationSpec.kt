package adventofcode.year2020

import adventofcode.year2020.Day15RambunctiousRecitation.part1
import adventofcode.year2020.Day15RambunctiousRecitation.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day15RambunctiousRecitationSpec : FreeSpec({
    "Day 15: Rambunctious Recitation" - {
        "Part 1" - {
            "Example 1" {
                part1(listOf(0, 3, 6)) shouldBe 436
            }

            "Example 2" {
                part1(listOf(1, 3, 2)) shouldBe 1
            }

            "Example 3" {
                part1(listOf(2, 1, 3)) shouldBe 10
            }

            "Example 4" {
                part1(listOf(1, 2, 3)) shouldBe 27
            }

            "Example 5" {
                part1(listOf(2, 3, 1)) shouldBe 78
            }

            "Example 6" {
                part1(listOf(3, 2, 1)) shouldBe 438
            }

            "Example 7" {
                part1(listOf(3, 1, 2)) shouldBe 1836
            }
        }

        "Part 2" - {
            "Example 1" {
                part2(listOf(0, 3, 6)) shouldBe 175594
            }

            "Example 2" {
                part2(listOf(1, 3, 2)) shouldBe 2578
            }

            "Example 3" {
                part2(listOf(2, 1, 3)) shouldBe 3544142
            }

            "Example 4" {
                part2(listOf(1, 2, 3)) shouldBe 261214
            }

            "Example 5" {
                part2(listOf(2, 3, 1)) shouldBe 6895259
            }

            "Example 6" {
                part2(listOf(3, 2, 1)) shouldBe 18
            }

            "Example 7" {
                part2(listOf(3, 1, 2)) shouldBe 362
            }
        }
    }
})
