package adventofcode.year2020

import adventofcode.utils.readInputAsString
import adventofcode.year2020.Day16TicketTranslation.TicketRule
import adventofcode.year2020.Day16TicketTranslation.part1
import adventofcode.year2020.Day16TicketTranslation.part2

typealias Ticket = List<Int>

object Day16TicketTranslation {
    data class TicketRule(
        val name: String,
        val ranges: List<IntRange>
    )

    fun part1(ticketRules: List<TicketRule>, nearbyTickets: List<Ticket>) = nearbyTickets
        .flatMap { ticket -> ticket.filter { ticketRules.flatMap(TicketRule::ranges).none { rule -> rule.contains(it) } } }
        .sum()

    fun part2(ticketRules: List<TicketRule>, yourTicket: Ticket, nearbyTickets: List<Ticket>): Long {
        val validNearbyTickets = nearbyTickets
            .filter { ticket -> ticket.all { ticketRules.flatMap(TicketRule::ranges).any { rule -> rule.contains(it) } } }

        val rulesWithIndex = ticketRules
            .map { rule ->
                val possibleIndices = (yourTicket.indices)
                    .filter { index -> validNearbyTickets.map { it[index] }.all { rule.ranges.any { range -> range.contains(it) } } }

                Pair(rule, possibleIndices.toMutableList())
            }

        repeat((ticketRules.indices).count()) {
            val unique = rulesWithIndex.map { it.second }.filter { it.size == 1 }.flatten()
            rulesWithIndex.filter { it.second.size > 1 }.map { Pair(it.first, it.second.removeAll(unique)) }
        }

        return rulesWithIndex
            .map { Pair(it.first, it.second.first()) }
            .filter { it.first.name.startsWith("departure") }
            .map { yourTicket[it.second].toLong() }
            .reduce { product, factor -> product * factor }
    }
}

fun main() {
    val input = readInputAsString(2020, 16).split("\n\n")

    val ticketRules = input
        .first()
        .lines()
        .map { it.split(": ") }
        .map { TicketRule(it.first(), it.last().split(" or ").map { it.split("-").first().toInt()..it.split("-").last().toInt() }) }

    val yourTicket = input[1].lines().last().split(",").map(String::toInt)

    val nearbyTickets = input.last().lines().drop(1).map { it.split(",").map(String::toInt) }

    println("Part 1: ${part1(ticketRules, nearbyTickets)}")
    println("Part 2: ${part2(ticketRules, yourTicket, nearbyTickets)}")
}
