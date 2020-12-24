package adventofcode

import adventofcode.year2015.Day1NotQuiteLisp
import adventofcode.year2015.Day2IWasToldThereWouldBeNoMath
import adventofcode.year2019.Day1TheTyrannyOfTheRocketEquation
import adventofcode.year2020.Day10AdapterArray
import adventofcode.year2020.Day11SeatingSystem
import adventofcode.year2020.Day12RainRisk
import adventofcode.year2020.Day13ShuttleSearch
import adventofcode.year2020.Day14DockingData
import adventofcode.year2020.Day15RambunctiousRecitation
import adventofcode.year2020.Day16TicketTranslation
import adventofcode.year2020.Day17ConwayCubes
import adventofcode.year2020.Day19MonsterMessages
import adventofcode.year2020.Day1ReportRepair
import adventofcode.year2020.Day20JurassicJigsaw
import adventofcode.year2020.Day21AllergenAssessment
import adventofcode.year2020.Day22CrabCombat
import adventofcode.year2020.Day23CrabCups
import adventofcode.year2020.Day2PasswordPhilosophy
import adventofcode.year2020.Day3TobogganTrajectory
import adventofcode.year2020.Day4PassportProcessing
import adventofcode.year2020.Day5BinaryBoarding
import adventofcode.year2020.Day6CustomCustoms
import adventofcode.year2020.Day7HandyHaversacks
import adventofcode.year2020.Day8HandheldHalting
import adventofcode.year2020.Day9EncodingError
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main(vararg args: String) {
    listOf(
        Day1NotQuiteLisp,
        Day2IWasToldThereWouldBeNoMath,

        Day1TheTyrannyOfTheRocketEquation,

        Day1ReportRepair,
        Day2PasswordPhilosophy,
        Day3TobogganTrajectory,
        Day4PassportProcessing,
        Day5BinaryBoarding,
        Day6CustomCustoms,
        Day7HandyHaversacks,
        Day8HandheldHalting,
        Day9EncodingError,
        Day10AdapterArray,
        Day11SeatingSystem,
        Day12RainRisk,
        Day13ShuttleSearch,
        Day14DockingData,
        Day15RambunctiousRecitation,
        Day16TicketTranslation,
        Day17ConwayCubes,
        Day19MonsterMessages,
        Day20JurassicJigsaw,
        Day21AllergenAssessment,
        Day22CrabCombat,
        Day23CrabCups
    )
        .forEach { day ->
            day.run()
            println()
        }
}
