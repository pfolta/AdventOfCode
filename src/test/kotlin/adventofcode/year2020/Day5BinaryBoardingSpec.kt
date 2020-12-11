package adventofcode.year2020

import adventofcode.year2020.Day5BinaryBoarding.part1
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day5BinaryBoardingSpec : FreeSpec({
    "Day 5: Binary Boarding" - {
        "Part 1" {
            val input = listOf("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")
            val expected = 820

            part1(input) shouldBe expected
        }
    }
})
