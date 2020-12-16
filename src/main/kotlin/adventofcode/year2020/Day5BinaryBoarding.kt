package adventofcode.year2020

import adventofcode.year2020.Day5BinaryBoarding.part1
import adventofcode.year2020.Day5BinaryBoarding.part2
import adventofcode.utils.readInputAsLines

object Day5BinaryBoarding {
    data class Seat(
        val row: Int,
        val column: Int
    ) {
        val seatId = row * 8 + column

        constructor(boardingPass: String) : this(
            boardingPass.replace("F", "0").replace("B", "1").substring(0 until 7).toInt(2),
            boardingPass.replace("L", "0").replace("R", "1").substring(7 until 10).toInt(2)
        )
    }

    fun part1(seats: List<String>) = seats.map(::Seat).maxBy { it.seatId }!!.seatId

    fun part2(seats: List<String>) = (seats.indices).last { !seats.map(::Seat).map(Seat::seatId).contains(it) }
}

fun main() {
    val boardingPasses = readInputAsLines(2020, 5)

    println("Part 1: ${part1(boardingPasses)}")
    println("Part 2: ${part2(boardingPasses)}")
}
