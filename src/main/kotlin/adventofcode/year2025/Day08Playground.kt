package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product
import adventofcode.common.spatial.Point3d
import adventofcode.util.runningInTest

class Day08Playground(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun connectJunctionBoxes(): Sequence<Triple<Set<Set<Point3d>>, Int, Set<Point3d>>> {
        val junctionBoxes =
            input.lines().map {
                val (x, y, z) = junctionBoxRegex.find(it)!!.destructured
                Point3d(x.toLong(), y.toLong(), z.toLong())
            }

        val sortedJunctionBoxPairs =
            junctionBoxes
                .flatMapIndexed { index, a -> junctionBoxes.subList(index + 1, junctionBoxes.size).map { b -> a to b } }
                .map { (a, b) -> Triple(a, b, a euclideanDistanceTo b) }
                .sortedBy { (_, _, distance) -> distance }

        return generateSequence(Triple(junctionBoxes.map { box -> setOf(box) }.toSet(), 0, emptySet())) { (circuits, index) ->
            val (a, b) = sortedJunctionBoxPairs[index]
            val circuitsContainingAorB = circuits.filter { circuit -> circuit.contains(a) || circuit.contains(b) }

            Triple(
                circuits - circuitsContainingAorB.toSet() + setOf(circuitsContainingAorB.flatten().toSet() + setOf(a) + setOf(b)),
                index + 1,
                setOf(a, b),
            )
        }
    }

    override fun partOne(): Int {
        // The example case shows making 10 connections, the actual puzzle requires 1000 connections
        val numberOfConnections = if (runningInTest()) 10 else 1000

        return connectJunctionBoxes()
            .drop(1)
            .take(numberOfConnections)
            .last()
            .first
            .map { circuit -> circuit.size }
            .sorted()
            .takeLast(3)
            .product()
    }

    override fun partTwo(): Long =
        connectJunctionBoxes()
            .first { (circuits) -> circuits.size == 1 }
            .third
            .map { (x) -> x }
            .product()

    companion object {
        private val junctionBoxRegex = """(\d+),(\d+),(\d+)""".toRegex()
    }
}
