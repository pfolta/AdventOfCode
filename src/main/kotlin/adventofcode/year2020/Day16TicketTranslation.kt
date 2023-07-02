package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product

class Day16TicketTranslation(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val ticketRules by lazy {
        input
            .split("\n\n")
            .first()
            .lines()
            .map { it.split(": ") }
            .map { TicketRule(it.first(), it.last().split(" or ").map { it.split("-").first().toInt()..it.split("-").last().toInt() }) }
    }

    private val yourTicket by lazy { input.split("\n\n")[1].lines().last().split(",").map(String::toInt) }

    private val nearbyTickets by lazy {
        input
            .split("\n\n")
            .last()
            .lines()
            .drop(1)
            .map { it.split(",").map(String::toInt) }
    }

    override fun partOne() = nearbyTickets
        .flatMap { ticket -> ticket.filter { ticketRules.flatMap(TicketRule::ranges).none { rule -> rule.contains(it) } } }
        .sum()

    override fun partTwo(): Long {
        val validNearbyTickets = nearbyTickets
            .filter { ticket -> ticket.all { ticketRules.flatMap(TicketRule::ranges).any { rule -> rule.contains(it) } } }

        val rulesWithIndex = ticketRules
            .map { rule ->
                val possibleIndices = (yourTicket.indices)
                    .filter { index -> validNearbyTickets.map { it[index] }.all { rule.ranges.any { range -> range.contains(it) } } }

                rule to possibleIndices.toMutableList()
            }

        repeat((ticketRules.indices).count()) {
            val unique = rulesWithIndex.map { it.second }.filter { it.size == 1 }.flatten()
            rulesWithIndex.filter { it.second.size > 1 }.map { Pair(it.first, it.second.removeAll(unique)) }
        }

        return rulesWithIndex
            .map { Pair(it.first, it.second.first()) }
            .filter { it.first.name.startsWith("departure") }
            .map { yourTicket[it.second].toLong() }
            .product()
    }

    companion object {
        private data class TicketRule(
            val name: String,
            val ranges: List<IntRange>
        )
    }
}
