package adventofcode.year2020

import adventofcode.year2020.Day11SeatingSystem.part1
import adventofcode.year2020.Day11SeatingSystem.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day11SeatingSystemSpec : FreeSpec({
    "Day 11: Seating System" - {
        val exampleInput = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent().lines().map { it.toCharArray().map { it.toString() } }

        "Part 1" {
            part1(exampleInput) shouldBe 37
        }

        "Part 2" {
            part2(exampleInput) shouldBe 26
        }
    }
})
