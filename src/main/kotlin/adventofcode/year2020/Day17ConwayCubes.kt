package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.year2020.Day17ConwayCubes.Companion.Cube.Cube3d
import adventofcode.year2020.Day17ConwayCubes.Companion.Cube.Cube4d

class Day17ConwayCubes(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .lines()
        .map { it.toCharArray().map(Char::toString) }
        .mapIndexed { y, row -> row.mapIndexed { x, state -> Cube3d(x, y, 0) as Cube to state } }
        .flatten()
        .toMap()
        .simulateBootSequence()

    override fun partTwo() = input
        .lines()
        .map { it.toCharArray().map(Char::toString) }
        .mapIndexed { y, row -> row.mapIndexed { x, state -> Cube4d(x, y, 0, 0) as Cube to state } }
        .flatten()
        .toMap()
        .simulateBootSequence()

    companion object {
        private const val ACTIVE = "#"
        private const val INACTIVE = "."

        sealed class Cube {
            abstract fun neighbors(): Set<Cube>

            data class Cube3d(val x: Int, val y: Int, val z: Int) : Cube() {
                override fun neighbors() = (-1..1).flatMap { dx ->
                    (-1..1).flatMap { dy ->
                        (-1..1).map { dz ->
                            Cube3d(x + dx, y + dy, z + dz)
                        }
                    }
                }.minus(this).toSet()
            }

            data class Cube4d(val x: Int, val y: Int, val z: Int, val w: Int) : Cube() {
                override fun neighbors() = (-1..1).flatMap { dx ->
                    (-1..1).flatMap { dy ->
                        (-1..1).flatMap { dz ->
                            (-1..1).map { dw ->
                                Cube4d(x + dx, y + dy, z + dz, w + dw)
                            }
                        }
                    }
                }.minus(this).toSet()
            }
        }

        fun Map<Cube, String>.simulateBootSequence() =
            generateSequence(this) { next ->
                (
                    next + next.map { (cube, _) ->
                        (listOf(cube) + cube.neighbors())
                            .mapNotNull {
                                val active = next.getOrDefault(it, INACTIVE) == ACTIVE
                                val activeNeighbors = it.neighbors().count { next.getOrDefault(it, INACTIVE) == ACTIVE }

                                if (active && (activeNeighbors < 2 || activeNeighbors > 3)) {
                                    it to INACTIVE
                                } else if (!active && activeNeighbors == 3) {
                                    it to ACTIVE
                                } else {
                                    null
                                }
                            }
                            .toMap()
                    }.reduce { acc, elem -> acc + elem }
                    )
            }
                .drop(1)
                .take(6)
                .last()
                .values
                .count { it == ACTIVE }
    }
}
