package adventofcode.year2020

import adventofcode.year2020.Day14DockingData.part1
import adventofcode.year2020.Day14DockingData.part2
import adventofcode.year2020.InitializationProgram.MaskInstruction
import adventofcode.year2020.InitializationProgram.MemoryInstruction
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day14DockingDataSpec : FreeSpec({
    "Day 14: Docking Data" - {
        "Part 1" {
            val program = listOf(
                MaskInstruction("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"),
                MemoryInstruction(8, 11),
                MemoryInstruction(7, 101),
                MemoryInstruction(8, 0)
            )

            part1(program) shouldBe 165
        }

        "Part 2" {
            val program = listOf(
                MaskInstruction("000000000000000000000000000000X1001X"),
                MemoryInstruction(42, 100),
                MaskInstruction("00000000000000000000000000000000X0XX"),
                MemoryInstruction(26, 1)
            )

            part2(program) shouldBe 208
        }
    }
})
