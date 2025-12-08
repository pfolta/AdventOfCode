package adventofcode.year2025

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product
import adventofcode.common.spatial.Point3d
import adventofcode.util.runningInTest

class Day08Playground(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private fun parseInput() =
        input.lines().map {
            val (x, y, z) = junctionBoxRegex.find(it)!!.destructured
            Point3d(x.toLong(), y.toLong(), z.toLong())
        }

    override fun partOne(): Int {
        val junctionBoxes = parseInput()
        val sortedJunctionBoxPairs =
            junctionBoxes
                .flatMapIndexed { index, a -> junctionBoxes.subList(index + 1, junctionBoxes.size).map { b -> a to b } }
                .map { (a, b) -> Triple(a, b, a euclideanDistanceTo b) }
                .sortedBy { (_, _, distance) -> distance }

        // The example case shows making 10 connections, the actual puzzle requires 1000 connections
        val numberOfConnections = if (runningInTest()) 10 else 1000

        return (0 until numberOfConnections)
            .fold(junctionBoxes.map { box -> setOf(box) }.toSet()) { circuits, index ->
                val (a, b) = sortedJunctionBoxPairs[index]
                val circuitsContainingAorB = circuits.filter { circuit -> circuit.contains(a) || circuit.contains(b) }
                circuits - circuitsContainingAorB.toSet() + setOf(circuitsContainingAorB.flatten().toSet() + setOf(a) + setOf(b))
            }.map { circuit -> circuit.size }
            .sorted()
            .takeLast(3)
            .product()
    }

    companion object {
        private val junctionBoxRegex = """(\d+),(\d+),(\d+)""".toRegex()
    }
}
