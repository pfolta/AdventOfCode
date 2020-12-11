package adventofcode.utils

fun readInputAsLines(year: Int, day: Int) = readInputAsString(year, day).lines()

fun readInputAsString(year: Int, day: Int) = object {}.javaClass.getResource("/inputs/year$year/day$day.txt").readText().trim()
