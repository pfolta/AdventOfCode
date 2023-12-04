package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import kotlin.math.pow

class Day04Scratchcards(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val scratchcards by lazy { input.lines().map(Scratchcard::invoke) }

    override fun partOne() = scratchcards.sumOf(Scratchcard::points)

    override fun partTwo(): Int {
        val map = scratchcards.map(Scratchcard::id).associateWith { 1 }.toMutableMap()

        scratchcards.forEach { scratchcard ->
            scratchcard.cardsWon().forEach { wonCard ->
                map[wonCard] = map[wonCard]!! + map[scratchcard.id]!!
            }
        }

        return map.values.sum()
    }

    companion object {
        private val CARD_REGEX = """Card\s+(\d+): ([\d\s]+) \| ([\d\s]+)""".toRegex()

        private data class Scratchcard(
            val id: Int,
            val winningNumbers: Set<Int>,
            val cardNumbers: Set<Int>
        ) {
            val matchingNumbers = cardNumbers.intersect(winningNumbers)

            fun points() = 2.0.pow(matchingNumbers.size.minus(1)).toInt()

            fun cardsWon() = (id + 1..id + matchingNumbers.size).toList()

            companion object {
                operator fun invoke(input: String): Scratchcard {
                    val (id, winningNumbers, cardNumbers) = CARD_REGEX.find(input)!!.destructured

                    return Scratchcard(
                        id.toInt(),
                        winningNumbers.split(" ").mapNotNull(String::toIntOrNull).toSet(),
                        cardNumbers.split(" ").mapNotNull(String::toIntOrNull).toSet()
                    )
                }
            }
        }
    }
}
