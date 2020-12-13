package adventofcode.year2020

import adventofcode.year2020.Day12RainRisk.part1
import adventofcode.year2020.Day12RainRisk.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day12RainRiskSpec : FreeSpec({
    "Day 12: Rain Risk" - {
        val exampleInput = listOf("F10", "N3", "F7", "R90", "F11").map { NavigationInstruction(it) }

        "Part 1" {
            part1(exampleInput) shouldBe 25
        }

        "Part 2" {
            part2(exampleInput) shouldBe 286
        }
    }
})
