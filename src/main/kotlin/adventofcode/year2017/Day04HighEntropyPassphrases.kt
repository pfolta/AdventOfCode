package adventofcode.year2017

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day04HighEntropyPassphrases(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "High-Entropy Passphrases"

    override fun partOne() = input
        .lines()
        .map { passphrase -> passphrase.split(" ").groupingBy { it }.eachCount().values }
        .count { passphrase -> passphrase.none { it > 1 } }

    override fun partTwo() = input
        .lines()
        .map { passphrase -> passphrase.split(" ").map { password -> password.groupingBy { it }.eachCount() } }
        .count { passphrase -> passphrase == passphrase.distinct() }
}
