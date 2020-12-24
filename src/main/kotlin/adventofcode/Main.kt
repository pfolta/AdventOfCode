package adventofcode

import adventofcode.year2015.Day01NotQuiteLisp
import adventofcode.year2015.Day02IWasToldThereWouldBeNoMath
import adventofcode.year2019.Day01TheTyrannyOfTheRocketEquation
import adventofcode.year2020.Day01ReportRepair
import adventofcode.year2020.Day02PasswordPhilosophy
import adventofcode.year2020.Day03TobogganTrajectory
import adventofcode.year2020.Day04PassportProcessing
import adventofcode.year2020.Day05BinaryBoarding
import adventofcode.year2020.Day06CustomCustoms
import adventofcode.year2020.Day07HandyHaversacks
import adventofcode.year2020.Day08HandheldHalting
import adventofcode.year2020.Day09EncodingError
import adventofcode.year2020.Day10AdapterArray
import adventofcode.year2020.Day11SeatingSystem
import adventofcode.year2020.Day12RainRisk
import adventofcode.year2020.Day13ShuttleSearch
import adventofcode.year2020.Day14DockingData
import adventofcode.year2020.Day15RambunctiousRecitation
import adventofcode.year2020.Day16TicketTranslation
import adventofcode.year2020.Day17ConwayCubes
import adventofcode.year2020.Day19MonsterMessages
import adventofcode.year2020.Day20JurassicJigsaw
import adventofcode.year2020.Day21AllergenAssessment
import adventofcode.year2020.Day22CrabCombat
import adventofcode.year2020.Day23CrabCups
import adventofcode.year2020.Day24LobbyLayout
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main(vararg args: String) {
    listOf(
        Day01NotQuiteLisp,
        Day02IWasToldThereWouldBeNoMath,

        Day01TheTyrannyOfTheRocketEquation,

        Day01ReportRepair,
        Day02PasswordPhilosophy,
        Day03TobogganTrajectory,
        Day04PassportProcessing,
        Day05BinaryBoarding,
        Day06CustomCustoms,
        Day07HandyHaversacks,
        Day08HandheldHalting,
        Day09EncodingError,
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
        Day23CrabCups,
        Day24LobbyLayout
    )
        .forEach { day ->
            day.run()
            println()
        }
}
