package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.common.product

object Day16TicketTranslation : Puzzle() {
    private val ticketRules = input
        .split("\n\n")
        .first()
        .lines()
        .map { it.split(": ") }
        .map { TicketRule(it.first(), it.last().split(" or ").map { it.split("-").first().toInt()..it.split("-").last().toInt() }) }

    private val yourTicket = input.split("\n\n")[1].lines().last().split(",").map(String::toInt)

    private val nearbyTickets = input
        .split("\n\n")
        .last()
        .lines()
        .drop(1)
        .map { it.split(",").map(String::toInt) }

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
}

private data class TicketRule(
    val name: String,
    val ranges: List<IntRange>
)
