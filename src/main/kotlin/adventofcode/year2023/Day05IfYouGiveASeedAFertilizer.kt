package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day05IfYouGiveASeedAFertilizer(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne(): Long {
        val seeds = input.lines().first().split(" ").mapNotNull(String::toLongOrNull)
        val maps = input.split("\n\n").drop(1).map { map -> map.lines().drop(1).map(ConversionMap::invoke) }

        return seeds.minOf { seed -> maps.fold(seed) { dst, map -> dst.mapsTo(map) } }
    }

    companion object {
        private fun Long.mapsTo(maps: List<ConversionMap>) = when (val map = maps.find { it.contains(this) }) {
            null -> this
            else -> map.dstStart + (this - map.srcStart)
        }

        private data class ConversionMap(
            val srcStart: Long,
            val dstStart: Long,
            val length: Long
        ) {
            fun contains(item: Long) = (item >= srcStart && item < srcStart + length)

            companion object {
                operator fun invoke(input: String): ConversionMap {
                    val (dstStart, srcStart, length) = input.split(" ").map(String::toLong)
                    return ConversionMap(srcStart, dstStart, length)
                }
            }
        }
    }
}
