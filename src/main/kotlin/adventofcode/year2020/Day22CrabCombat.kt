package adventofcode.year2020

import adventofcode.Puzzle

class Day22CrabCombat(customInput: String? = null) : Puzzle(customInput) {
    private val player1 = input.split("\n\n").first().lines().drop(1).map(String::toInt)
    private val player2 = input.split("\n\n").last().lines().drop(1).map(String::toInt)

    override fun partOne() = CrabCombatGame(player1.toMutableList(), player2.toMutableList()).playRegularGame()

    override fun partTwo() = CrabCombatGame(player1.toMutableList(), player2.toMutableList()).playRecursiveGame(true)

    companion object {
        data class CrabCombatGame(
            val player1: MutableList<Int>,
            val player2: MutableList<Int>
        ) {
            private fun computeScore() = (player1 + player2).reversed().mapIndexed { position, card -> card * (position + 1) }.sum()

            fun playRegularGame(): Int {
                while (player1.isNotEmpty() && player2.isNotEmpty()) {
                    val card1 = player1.removeAt(0)
                    val card2 = player2.removeAt(0)

                    if (card1 > card2) {
                        player1.addAll(listOf(card1, card2))
                    } else {
                        player2.addAll(listOf(card2, card1))
                    }
                }

                return computeScore()
            }

            fun playRecursiveGame(startingGame: Boolean): Int {
                val previousConfigurations = hashSetOf<CrabCombatGame>()

                while (player1.isNotEmpty() && player2.isNotEmpty()) {
                    if (previousConfigurations.contains(this)) {
                        return 1
                    }

                    previousConfigurations.add(this)

                    val card1 = player1.removeAt(0)
                    val card2 = player2.removeAt(0)

                    val winningPlayer = when {
                        player1.size >= card1 && player2.size >= card2 ->
                            CrabCombatGame(
                                player1.take(card1).toMutableList(),
                                player2.take(card2).toMutableList()
                            ).playRecursiveGame(false)
                        card1 > card2 -> 1
                        else -> 2
                    }

                    if (winningPlayer == 1) {
                        player1.addAll(listOf(card1, card2))
                    } else {
                        player2.addAll(listOf(card2, card1))
                    }
                }

                return when (startingGame) {
                    true -> computeScore()
                    false -> if (player1.size > player2.size) 1 else 2
                }
            }
        }
    }
}
