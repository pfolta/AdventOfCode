package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product

class Day20JurassicJigsaw(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val tiles by lazy { input.split("\n\n").map(::Tile) }

    override fun partOne(): Long {
        val tileMap = generateSequence(mapOf(Pair(0, 0) to tiles.first())) { previous ->
            previous + previous.keys.flatMap { tile ->
                val left = tiles
                    .filter { previous.values.none { previousTile -> it.id == previousTile.id } }
                    .firstNotNullOfOrNull { newTile ->
                        val left = previous[tile]!!.left
                        newTile.variations().firstOrNull { it.right == left }?.let { Pair(tile.first - 1, tile.second) to it }
                    }

                val right = tiles
                    .filter { previous.values.none { previousTile -> it.id == previousTile.id } }
                    .firstNotNullOfOrNull { newTile ->
                        val right = previous[tile]!!.right
                        newTile.variations().firstOrNull { it.left == right }?.let { Pair(tile.first + 1, tile.second) to it }
                    }

                val bottom = tiles
                    .filter { previous.values.none { previousTile -> it.id == previousTile.id } }
                    .firstNotNullOfOrNull { newTile ->
                        val bottom = previous[tile]!!.bottom
                        newTile.variations().firstOrNull { it.top == bottom }?.let { Pair(tile.first, tile.second - 1) to it }
                    }

                val top = tiles
                    .filter { previous.values.none { previousTile -> it.id == previousTile.id } }
                    .firstNotNullOfOrNull { newTile ->
                        val top = previous[tile]!!.top
                        newTile.variations().firstOrNull { it.bottom == top }?.let { Pair(tile.first, tile.second + 1) to it }
                    }

                listOfNotNull(left, right, bottom, top)
            }.toMap()
        }
            .first { it.size == tiles.size }

        return listOf(
            tileMap.entries.sortedBy { it.key.first }.minByOrNull { it.key.second }!!,
            tileMap.entries.sortedBy { it.key.first }.maxByOrNull { it.key.second }!!,
            tileMap.entries.sortedByDescending { it.key.first }.minByOrNull { it.key.second }!!,
            tileMap.entries.sortedByDescending { it.key.first }.maxByOrNull { it.key.second }!!
        )
            .map { it.value.id }
            .product()
    }

    companion object {
        private data class Tile(
            val id: Long,
            val content: List<String>
        ) {
            constructor(tile: String) : this(tile.lines().first().split(" ").last().replace(":", "").toLong(), tile.lines().drop(1))

            val left = col(0)
            val top = row(0)
            val right = col(content.first().length - 1)
            val bottom = row(content.size - 1)

            private fun col(n: Int) = content.fold("") { col, row -> col + row[n] }
            private fun row(n: Int) = content[n]

            // Rotate content 90 degrees clockwise
            private fun rotate() = copy(content = List(content.size) { n -> col(n).reversed() })

            private fun flipX() = copy(content = content.map(String::reversed))
            private fun flipY() = copy(content = content.reversed())

            fun variations() =
                listOf(this, flipX(), flipY()).flatMap { listOf(it, it.rotate(), it.rotate().rotate(), it.rotate().rotate().rotate()) }
        }
    }
}
