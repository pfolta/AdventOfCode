package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.common.product
import kotlin.math.ceil

class Day13ShuttleSearch(customInput: String? = null) : Puzzle(customInput) {
    private val buses by lazy { input.lines().last().split(",") }
    private val earliestDeparture by lazy { input.lines().first().toInt() }

    override fun partOne() = buses
        .mapNotNull(String::toIntOrNull)
        .map { Pair(it, ceil(earliestDeparture.toDouble() / it.toDouble()).toInt() * it - earliestDeparture) }
        .minByOrNull { it.second }!!
        .toList()
        .product()

    override fun partTwo(): Long {
        val busesWithOffsets = buses
            .mapIndexedNotNull { offset, busId -> if (busId == "x") null else Pair(busId.toInt(), offset) }

        return busesWithOffsets.fold(Pair(0L, 1L)) { (timestamp, step), (busId, busOffset) ->
            var curTimestamp = timestamp

            while ((curTimestamp + busOffset) % busId != 0L) curTimestamp += step

            Pair(curTimestamp, step * busId)
        }.first
    }
}
