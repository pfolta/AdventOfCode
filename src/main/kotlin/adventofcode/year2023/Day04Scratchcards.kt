package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.pow

class Day04Scratchcards(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .lines()
        .map(Scratchcard::invoke)
        .sumOf(Scratchcard::value)

    companion object {
        private val CARD_REGEX = """Card\s+(\d+): ([\d\s]+) \| ([\d\s]+)""".toRegex()

        private data class Scratchcard(
            val id: String,
            val winningNumbers: Set<Int>,
            val cardNumbers: Set<Int>
        ) {
            fun value() = 2.0.pow(cardNumbers.intersect(winningNumbers).size.minus(1)).toInt()

            companion object {
                operator fun invoke(input: String): Scratchcard {
                    val (id, winningNumbers, cardNumbers) = CARD_REGEX.find(input)!!.destructured

                    return Scratchcard(
                        id,
                        winningNumbers.split(" ").mapNotNull(String::toIntOrNull).toSet(),
                        cardNumbers.split(" ").mapNotNull(String::toIntOrNull).toSet()
                    )
                }
            }
        }
    }
}
