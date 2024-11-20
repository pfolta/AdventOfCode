package adventofcode.year2018

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.String.removeAt
import adventofcode.common.product

class Day02InventoryManagementSystem(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val boxes by lazy { input.lines() }

    override fun partOne() =
        boxes
            .map { boxId -> boxId.groupingBy { it }.eachCount() }
            .map { it.values.contains(2) to it.values.contains(3) }
            .fold(0 to 0) { total, box -> total.first + box.first.toInt() to total.second + box.second.toInt() }
            .toList()
            .product()

    override fun partTwo() =
        boxes
            .filter { boxId -> boxes.any { other -> boxId.difference(other).size == 1 } }
            .reduce { acc, box -> acc.removeAt(acc.difference(box).first()) }

    companion object {
        private fun Boolean.toInt() = if (this) 1 else 0

        private fun String.difference(other: String) = (indices).filter { this[it] != other[it] }
    }
}
