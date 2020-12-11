package adventofcode.year2020

import adventofcode.year2020.Day9EncodingError.part1
import adventofcode.year2020.Day9EncodingError.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day9EncodingErrorSpec : FreeSpec({
    "Day 9: Encoding Error" - {
        "Part 1" {
            val input: List<Long> = listOf(35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)
            val expected = 127

            part1(input, 5) shouldBe expected
        }

        "Part 2" {
            val input: List<Long> = listOf(35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)
            val searchPattern = 127L
            val expected = 62

            part2(input, searchPattern) shouldBe expected
        }
    }
})
