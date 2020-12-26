package adventofcode.utils

import java.io.FileNotFoundException

fun readInputAsText(year: Int, day: Int) = object {}.javaClass.classLoader.getResource("inputs/year$year/day$day.txt")?.readText()?.trim()
    ?: throw FileNotFoundException("Input file for puzzle $year/$day not found")
