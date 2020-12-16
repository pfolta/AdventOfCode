package adventofcode.year2020

import adventofcode.year2020.Day16TicketTranslation.TicketRule
import adventofcode.year2020.Day16TicketTranslation.part1
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day16TicketTranslationSpec : FreeSpec({
    "Day 16: Ticket Translation" - {
        "Part 1" {
            val ticketRules = listOf(
                TicketRule("class", listOf(1..3, 5..7)),
                TicketRule("row", listOf(6..11, 33..44)),
                TicketRule("seat", listOf(13..40, 45..50))
            )

            val nearbyTickets = listOf(listOf(7, 3, 47), listOf(40, 4, 50), listOf(55, 2, 20), listOf(38, 6, 12))

            part1(ticketRules, nearbyTickets) shouldBe 71
        }
    }
})
