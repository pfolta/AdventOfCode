package adventofcode.year2020

import adventofcode.year2020.Day13ShuttleSearch.part1
import adventofcode.year2020.Day13ShuttleSearch.part2
import adventofcode.utils.readInputAsLines
import kotlin.math.ceil

object Day13ShuttleSearch {
    fun part1(buses: List<String>, earliestDeparture: Int) = buses
        .mapNotNull(String::toIntOrNull)
        .map { Pair(it, ceil(earliestDeparture.toDouble() / it.toDouble()).toInt() * it - earliestDeparture) }
        .minBy { it.second }!!
        .toList()
        .reduce { product, factor -> product * factor }

    fun part2(buses: List<String>): Long {
        val busesWithOffsets = buses.mapIndexedNotNull { offset, busId -> if (busId == "x") null else Pair(busId.toInt(), offset) }

        return busesWithOffsets.fold(Pair(0L, 1L)) { (timestamp, step), (busId, busOffset) ->
            var curTimestamp = timestamp

            while ((curTimestamp + busOffset) % busId != 0L) curTimestamp += step

            Pair(curTimestamp, step * busId)
        }.first
    }
}

fun main() {
    val input = readInputAsLines(2020, 13)

    println("Part 1: ${part1(input.last().split(","), input.first().toInt())}")
    println("Part 2: ${part2(input.last().split(","))}")
}
