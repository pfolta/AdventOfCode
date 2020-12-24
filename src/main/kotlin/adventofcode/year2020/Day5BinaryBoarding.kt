package adventofcode.year2020

import adventofcode.Day

object Day5BinaryBoarding : Day() {
    private val seats = input.lines().map(::Seat)

    override fun partOne() = seats.maxByOrNull { it.seatId }!!.seatId

    override fun partTwo() = (input.lines().indices).last { !seats.map(Seat::seatId).contains(it) }
}

private data class Seat(
    val row: Int,
    val column: Int
) {
    val seatId = row * 8 + column

    constructor(boardingPass: String) : this(
        boardingPass.replace("F", "0").replace("B", "1").substring(0 until 7).toInt(2),
        boardingPass.replace("L", "0").replace("R", "1").substring(7 until 10).toInt(2)
    )
}
