package adventofcode.year2020

import adventofcode.Day

object Day1ReportRepair : Day() {
    private val expenses = input.lines().map(String::toInt)

    override fun partOne() = expenses
        .flatMap { fst -> expenses.minus(fst).map { snd -> listOf(fst, snd) } }
        .first { it.sum() == 2020 }
        .reduce { product, factor -> product * factor }

    override fun partTwo() = expenses
        .flatMap { fst -> expenses.minus(fst).flatMap { snd -> expenses.minus(listOf(fst, snd)).map { trd -> listOf(fst, snd, trd) } } }
        .first { it.sum() == 2020 }
        .reduce { product, factor -> product * factor }
}
