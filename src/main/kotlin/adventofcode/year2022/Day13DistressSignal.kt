package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.common.json
import adventofcode.common.product
import com.fasterxml.jackson.databind.JsonNode

class Day13DistressSignal(customInput: String? = null) : Puzzle(customInput) {
    private val packets by lazy { input.lines().filterNot { line -> line.isBlank() }.map { packet -> json.readTree(packet) } }

    override fun partOne() = packets
        .chunked(2)
        .mapIndexed { index, (left, right) -> index + 1 to (left < right) }
        .filter { (_, inOrder) -> inOrder }
        .sumOf { (index, _) -> index }

    override fun partTwo() = packets
        .union(DIVIDER_PACKETS)
        .sortedWith { a, b -> a.compareTo(b) }
        .mapIndexed { index, packet -> index + 1 to packet }
        .filter { (_, packet) -> packet in DIVIDER_PACKETS }
        .map { (index, _) -> index }
        .product()

    companion object {
        private val DIVIDER_PACKETS = setOf("[[2]]", "[[6]]").map { packet -> json.readTree(packet) }

        private operator fun JsonNode.compareTo(other: JsonNode): Int = when {
            isInt && other.isInt -> asInt().compareTo(other.asInt())

            isInt && other.isArray -> json.readTree("[$this]").compareTo(other)

            isArray && other.isInt -> compareTo(json.readTree("[$other]"))

            else -> zip(other)
                .map { (left, right) -> left.compareTo(right) }
                .filter { result -> result != 0 }
                .ifEmpty { listOf(count().compareTo(other.count())) }
                .first()
        }
    }
}
