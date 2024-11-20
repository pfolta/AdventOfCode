package adventofcode.year2016

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.everyNth

class Day07InternetProtocolVersion7(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val ipv7Addresses by lazy {
        input.lines().map { ipAddress -> ipAddress.split("[", "]") }.map { parts -> parts.everyNth(2) to parts.everyNth(2, 1) }
    }

    override fun partOne() =
        ipv7Addresses
            .count { (supernets, hypernets) ->
                supernets.any { supernet -> supernet.containsAbba() } && hypernets.none { hypernet -> hypernet.containsAbba() }
            }

    override fun partTwo() =
        ipv7Addresses
            .count { (supernets, hypernets) ->
                val abas = supernets.flatMap { supernet -> supernet.getAbas() }
                hypernets.any { hypernet -> abas.any { (a, b) -> hypernet.contains("$b$a$b") } }
            }

    companion object {
        private fun String.containsAbba() = windowed(4).map(String::toList).any { (a, b, c, d) -> (a != b && "$a$b" == "$d$c") }

        private fun String.getAbas() = windowed(3).map(String::toList).filter { (a, b, c) -> (a != b && a == c) }
    }
}
