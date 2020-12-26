package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.year2020.Day24LobbyLayout.Companion.TileColor.BLACK
import adventofcode.year2020.Day24LobbyLayout.Companion.TileColor.WHITE

class Day24LobbyLayout(puzzleInput: String? = null) : Puzzle(puzzleInput) {
    private val tileMap = input.lines()
        .asSequence()
        .map { tile -> DIRECTION_REGEX.findAll(tile).toList().map { it.value }.mapNotNull(DIRECTIONS::get) }
        .map { it.reduce { tile, direction -> tile.first + direction.first to tile.second + direction.second } }
        .fold(emptyMap<Pair<Int, Int>, TileColor>()) { tiles, tile ->
            when (tiles[tile]) {
                BLACK -> tiles + (tile to WHITE)
                else -> tiles + (tile to BLACK)
            }
        }

    override fun partOne() = tileMap.values.count { it == BLACK }

    override fun partTwo() = generateSequence(tileMap) { previous ->
        previous + previous.map { (tile, _) ->
            (listOf(tile) + tile.neighbors())
                .mapNotNull { position ->
                    val color = previous.getOrDefault(position, WHITE)
                    val blackNeighbors = position.neighbors().filter { previous.getOrDefault(it, WHITE) == BLACK }

                    if (color == BLACK && (blackNeighbors.isEmpty() || blackNeighbors.size > 2)) {
                        position to WHITE
                    } else if (color == WHITE && blackNeighbors.size == 2) {
                        position to BLACK
                    } else {
                        null
                    }
                }
                .toMap()
        }.reduce { tileMap, partialTileMap -> tileMap + partialTileMap }
    }
        .drop(1)
        .take(100)
        .last()
        .values
        .count { it == BLACK }

    companion object {
        private val DIRECTION_REGEX = "(e|se|sw|w|nw|ne)".toRegex()

        private val DIRECTIONS = mapOf(
            "e" to Pair(2, 0),
            "se" to Pair(1, -1),
            "sw" to Pair(-1, -1),
            "w" to Pair(-2, 0),
            "nw" to Pair(-1, 1),
            "ne" to Pair(1, 1)
        )

        private enum class TileColor {
            BLACK,
            WHITE
        }

        private fun Pair<Int, Int>.neighbors() = DIRECTIONS.values.map { first + it.first to second + it.second }
    }
}




