package adventofcode.year2017

import adventofcode.Puzzle

class Day04HighEntropyPassphrases(customInput: String? = null) : Puzzle(customInput) {
    override val name = "High-Entropy Passphrases"

    override fun partOne() = input
        .lines()
        .map { passphrase -> passphrase.split(" ").groupingBy { it }.eachCount().values }
        .filter { passphrase -> passphrase.none { it > 1 } }
        .count()
}
