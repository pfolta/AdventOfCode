package adventofcode.year2019

import adventofcode.year2019.Day21202ProgramAlarm.part1
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day21202ProgramAlarmSpec : FreeSpec({
    "Day 2: 1202 Program Alarm" - {
        "Part 1" {
            forAll(
                row(listOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50), 3500),
                row(listOf(1, 0, 0, 0, 99), 2),
                row(listOf(2, 3, 0, 3, 99), 2),
                row(listOf(2, 4, 4, 5, 99, 0), 2),
                row(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99), 30)
            ) { input, expectedOutput -> part1(input.toMutableList()) shouldBe expectedOutput }
        }
    }
})
