package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.year2020.Cube.Cube3d
import adventofcode.year2020.Cube.Cube4d

private const val ACTIVE = "#"
private const val INACTIVE = "."

object Day17ConwayCubes : Puzzle() {
    override fun partOne() = (input
        .lines()
        .map { it.toCharArray().map(Char::toString) }
        .mapIndexed { y, row -> row.mapIndexed { x, state -> Cube3d(x, y, 0) to state } }
        .flatten()
        .toMap() as Map<Cube, String>)
        .simulateBootSequence()

    override fun partTwo() = (input
        .lines()
        .map { it.toCharArray().map(Char::toString) }
        .mapIndexed { y, row -> row.mapIndexed { x, state -> Cube4d(x, y, 0, 0) to state } }
        .flatten()
        .toMap() as Map<Cube, String>)
        .simulateBootSequence()
}

private sealed class Cube {
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

private fun Map<Cube, String>.simulateBootSequence() =
    generateSequence(this) { next ->
        (next + next.entries.map { (cube, _) ->
            (listOf(cube) + cube.neighbors())
                .mapNotNull { cube ->
                    val active = next.getOrDefault(cube, INACTIVE) == ACTIVE
                    val activeNeighbors = cube.neighbors().count { next.getOrDefault(it, INACTIVE) == ACTIVE }

                    if (active && (activeNeighbors < 2 || activeNeighbors > 3)) {
                        cube to INACTIVE
                    } else if (!active && activeNeighbors == 3) {
                        cube to ACTIVE
                    } else {
                        null
                    }
                }
                .toMap()
        }.reduce { acc, elem -> acc + elem })
    }
        .drop(1)
        .take(6)
        .last()
        .values
        .count { it == ACTIVE }
