package adventofcode.year2020

import adventofcode.utils.readInputAsLines
import adventofcode.year2020.Day17ConwayCubes.Cube.Cube3d
import adventofcode.year2020.Day17ConwayCubes.Cube.Cube4d
import adventofcode.year2020.Day17ConwayCubes.part1
import adventofcode.year2020.Day17ConwayCubes.part2

object Day17ConwayCubes {
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

    private fun simulateBootSequence(initialSlice: Map<Cube, String>) =
        generateSequence(initialSlice) { next ->
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

    fun part1(input: List<List<String>>) =
        simulateBootSequence(input.mapIndexed { y, row -> row.mapIndexed { x, state -> Cube3d(x, y, 0) to state } }.flatten().toMap())

    fun part2(input: List<List<String>>) =
        simulateBootSequence(input.mapIndexed { y, row -> row.mapIndexed { x, state -> Cube4d(x, y, 0, 0) to state } }.flatten().toMap())
}

fun main() {
    val input = readInputAsLines(2020, 17).map { it.toCharArray().map(Char::toString) }

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
