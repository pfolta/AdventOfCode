package adventofcode.year2020

import adventofcode.year2020.Day17ConwayCubes.part1
import adventofcode.year2020.Day17ConwayCubes.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day17ConwayCubesSpec : FreeSpec({
    "Day 17: Conway Cubes" - {
        val input = """
                .#.
                ..#
                ###
            """.trimIndent().lines().map { it.toCharArray().map(Char::toString) }

        "Part 1" {
            part1(input) shouldBe 112
        }

        "Part 2" {
            part2(input) shouldBe 848
        }
    }
})
