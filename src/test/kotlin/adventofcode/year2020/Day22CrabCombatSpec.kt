package adventofcode.year2020

import adventofcode.year2020.Day22CrabCombat.part1
import adventofcode.year2020.Day22CrabCombat.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

@ExperimentalTime
class Day22CrabCombatSpec : FreeSpec({
    "Day 22: Crab Combat" - {
        val input = """
            Player 1:
            9
            2
            6
            3
            1

            Player 2:
            5
            8
            4
            7
            10
        """.trimIndent()

        "Part 1" {
            part1(input) shouldBe 306
        }

        "Part 2" - {
            "Simple Example" {
                part2(input) shouldBe 291
            }

            "Does not loop infinitely".config(timeout = 1.seconds) {
                val input = """
                    Player 1:
                    43
                    19

                    Player 2:
                    2
                    29
                    14
                """.trimIndent()

                part2(input)
            }
        }
    }
})
