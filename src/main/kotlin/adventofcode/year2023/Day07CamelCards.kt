package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.year2023.Day07CamelCards.Companion.HandType.FIVE_OF_A_KIND
import adventofcode.year2023.Day07CamelCards.Companion.HandType.FOUR_OF_A_KIND
import adventofcode.year2023.Day07CamelCards.Companion.HandType.FULL_HOUSE
import adventofcode.year2023.Day07CamelCards.Companion.HandType.HIGH_CARD
import adventofcode.year2023.Day07CamelCards.Companion.HandType.ONE_PAIR
import adventofcode.year2023.Day07CamelCards.Companion.HandType.THREE_OF_A_KIND
import adventofcode.year2023.Day07CamelCards.Companion.HandType.TWO_PAIR

class Day07CamelCards(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    override fun partOne() =
        input
            .lines()
            .asSequence()
            .map { it.split(" ") }
            .map { (hand, bid) -> Hand(hand) to bid.toInt() }
            .sortedBy { (hand) -> hand }
            .mapIndexed { rank, (_, bid) -> (rank + 1) * bid }
            .sum()

    companion object {
        private data class Card(
            val label: Char,
        ) : Comparable<Card> {
            override fun compareTo(other: Card) = cardRank.indexOf(label) - cardRank.indexOf(other.label)

            companion object {
                private val cardRank = listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')
            }
        }

        private data class Hand(
            val cards: List<Card>,
        ) : Comparable<Hand> {
            private val cardCounts = cards.groupingBy { it }.eachCount()

            fun handType() =
                when {
                    cardCounts.any { (_, count) -> count == 5 } -> FIVE_OF_A_KIND
                    cardCounts.any { (_, count) -> count == 4 } -> FOUR_OF_A_KIND
                    cardCounts.size == 2 -> FULL_HOUSE
                    cardCounts.any { (_, count) -> count == 3 } -> THREE_OF_A_KIND
                    cardCounts.filter { (_, count) -> count == 2 }.size == 2 -> TWO_PAIR
                    cardCounts.any { (_, count) -> count == 2 } -> ONE_PAIR
                    else -> HIGH_CARD
                }

            override fun compareTo(other: Hand) =
                when (val rank = handType().rank - other.handType().rank) {
                    0 ->
                        cards
                            .zip(other.cards)
                            .map { (thisHandCard, otherHandCard) -> thisHandCard.compareTo(otherHandCard) }
                            .first { it != 0 }

                    else -> rank
                }

            companion object {
                operator fun invoke(input: String) = Hand(input.toList().map(::Card))
            }
        }

        private enum class HandType(
            val rank: Int,
        ) {
            FIVE_OF_A_KIND(6),
            FOUR_OF_A_KIND(5),
            FULL_HOUSE(4),
            THREE_OF_A_KIND(3),
            TWO_PAIR(2),
            ONE_PAIR(1),
            HIGH_CARD(0),
        }
    }
}
