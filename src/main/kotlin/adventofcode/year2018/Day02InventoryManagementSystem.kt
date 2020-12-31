package adventofcode.year2018

import adventofcode.Puzzle
import adventofcode.common.product

class Day02InventoryManagementSystem(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne() = input
        .lines()
        .map { boxId -> boxId.groupingBy { it }.eachCount() }
        .map { it.values.contains(2) to it.values.contains(3) }
        .fold(0 to 0) { total, box -> total.first + box.first.toInt() to total.second + box.second.toInt() }
        .toList()
        .product()

    companion object {
        private fun Boolean.toInt() = if (this) 1 else 0
    }
}
