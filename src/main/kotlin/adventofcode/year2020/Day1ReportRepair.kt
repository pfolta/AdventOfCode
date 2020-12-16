package adventofcode.year2020

import adventofcode.year2020.Day1ReportRepair.part1
import adventofcode.year2020.Day1ReportRepair.part2
import adventofcode.utils.readInputAsLines

object Day1ReportRepair {
    fun part1(expenses: List<Int>, magicSum: Int) = expenses
        .flatMap { fst -> expenses.map { snd -> listOf(fst, snd) } }
        .filter { it.sum() == magicSum }
        .map { it.reduce { product, factor -> product * factor } }
        .first()

    fun part2(expenses: List<Int>, magicSum: Int) = expenses
        .flatMap { fst -> expenses.flatMap { snd -> expenses.map { trd -> listOf(fst, snd, trd) } } }
        .filter { it.sum() == magicSum }
        .map { it.reduce { product, factor -> product * factor } }
        .first()
}

fun main() {
    val expenses = readInputAsLines(2020, 1).map(String::toInt)

    println("Part 1: ${part1(expenses, 2020)}")
    println("Part 2: ${part2(expenses, 2020)}")
}
