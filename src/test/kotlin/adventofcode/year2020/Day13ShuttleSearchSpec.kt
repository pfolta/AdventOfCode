package adventofcode.year2020

import adventofcode.year2020.Day13ShuttleSearch.part1
import adventofcode.year2020.Day13ShuttleSearch.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day13ShuttleSearchSpec : FreeSpec({
    "Day 13: Shuttle Search" - {
        val buses = listOf("7", "13", "x", "x", "59", "x", "31", "19")

        "Part 1" {
            val earliestDeparture = 939

            part1(buses, earliestDeparture) shouldBe 295
        }

        "Part 2" - {
            "Example 1" {
                part2(buses) shouldBe 1068781
            }

            "Example 2" {
                part2(listOf("17", "x", "13", "19")) shouldBe 3417
            }

            "Example 3" {
                part2(listOf("67", "7", "59", "61")) shouldBe 754018
            }

            "Example 4" {
                part2(listOf("67", "x", "7", "59", "61")) shouldBe 779210
            }

            "Example 5" {
                part2(listOf("67", "7", "x", "59", "61")) shouldBe 1261476
            }

            "Example 6" {
                part2(listOf("1789", "37", "47", "1889")) shouldBe 1202161486
            }
        }
    }
})
