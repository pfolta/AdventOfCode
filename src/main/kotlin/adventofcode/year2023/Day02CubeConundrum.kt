package adventofcode.year2023

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product

class Day02CubeConundrum(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() =
        input
            .lines()
            .map(Game::invoke)
            .filter { game -> game.isPossible(12, 13, 14) }
            .sumOf(Game::id)

    override fun partTwo() =
        input
            .lines()
            .map(Game::invoke)
            .sumOf(Game::minimumCubePower)

    companion object {
        private data class Turn(
            val red: Int,
            val green: Int,
            val blue: Int,
        ) {
            companion object {
                operator fun invoke(turn: String): Turn {
                    val red = """(\d+) red""".toRegex().find(turn)?.destructured?.component1()?.toInt() ?: 0
                    val green = """(\d+) green""".toRegex().find(turn)?.destructured?.component1()?.toInt() ?: 0
                    val blue = """(\d+) blue""".toRegex().find(turn)?.destructured?.component1()?.toInt() ?: 0

                    return Turn(red, green, blue)
                }
            }
        }

        private data class Game(
            val id: Int,
            val turns: List<Turn>,
        ) {
            fun isPossible(
                red: Int,
                green: Int,
                blue: Int,
            ) = turns
                .filterNot { turn -> turn.red <= red && turn.green <= green && turn.blue <= blue }
                .isEmpty()

            fun minimumCubePower() = listOf(turns.maxOf(Turn::red), turns.maxOf(Turn::green), turns.maxOf(Turn::blue)).product()

            companion object {
                operator fun invoke(input: String): Game {
                    val id = input.split(": ").first().split(" ").last().toInt()
                    val turns = input.split(": ").last().split("; ").map(Turn::invoke)

                    return Game(id, turns)
                }
            }
        }
    }
}
