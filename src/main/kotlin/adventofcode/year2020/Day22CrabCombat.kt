package adventofcode.year2020

import adventofcode.utils.readInputAsString
import adventofcode.year2020.Day22CrabCombat.part1
import adventofcode.year2020.Day22CrabCombat.part2

object Day22CrabCombat {
    private fun computeScore(player1: List<Int>, player2: List<Int>) =
        (player1 + player2).reversed().mapIndexed { position, card -> card * (position + 1) }.sum()

    private fun subGame(player1: MutableList<Int>, player2: MutableList<Int>): Int {
        val previousConfigurations = hashSetOf<Pair<List<Int>, List<Int>>>()

        while (player1.isNotEmpty() && player2.isNotEmpty()) {
            if (previousConfigurations.contains(Pair(player1, player2))) {
                return 1
            }

            previousConfigurations.add(Pair(player1, player2))

            val card1 = player1.removeAt(0)
            val card2 = player2.removeAt(0)

            val winningPlayer = when {
                player1.size >= card1 && player2.size >= card2 ->
                    subGame(player1.take(card1).toMutableList(), player2.take(card2).toMutableList())
                card1 > card2 -> 1
                else -> 2
            }

            if (winningPlayer == 1) {
                player1.addAll(listOf(card1, card2))
            } else {
                player2.addAll(listOf(card2, card1))
            }
        }

        return if (player1.size > player2.size) 1 else 2
    }

    fun part1(input: String): Int {
        val player1 = input.split("\n\n").first().lines().drop(1).map(String::toInt).toMutableList()
        val player2 = input.split("\n\n").last().lines().drop(1).map(String::toInt).toMutableList()

        while (player1.isNotEmpty() && player2.isNotEmpty()) {
            val card1 = player1.removeAt(0)
            val card2 = player2.removeAt(0)

            if (card1 > card2) {
                player1.addAll(listOf(card1, card2))
            } else {
                player2.addAll(listOf(card2, card1))
            }
        }

        return computeScore(player1, player2)
    }

    fun part2(input: String): Int {
        val player1 = input.split("\n\n").first().lines().drop(1).map(String::toInt).toMutableList()
        val player2 = input.split("\n\n").last().lines().drop(1).map(String::toInt).toMutableList()

        subGame(player1, player2)

        return computeScore(player1, player2)
    }
}

fun main() {
    val input = readInputAsString(2020, 22)

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
