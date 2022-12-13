package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.common.json
import adventofcode.common.product
import com.fasterxml.jackson.databind.JsonNode

class Day13DistressSignal(customInput: String? = null) : Puzzle(customInput) {
    private val packets by lazy { input.lines().filterNot { line -> line.isBlank() }.map(Packet::of) }

    override fun partOne() = packets
        .chunked(2)
        .mapIndexed { index, (left, right) -> index + 1 to (left < right) }
        .filter { (_, inOrder) -> inOrder }
        .sumOf { (index, _) -> index }

    override fun partTwo() = (packets + DIVIDER_PACKETS)
        .sorted()
        .mapIndexed { index, packet -> index + 1 to packet }
        .filter { (_, packet) -> packet in DIVIDER_PACKETS }
        .map { (index, _) -> index }
        .product()

    companion object {
        private val DIVIDER_PACKETS = setOf("[[2]]", "[[6]]").map(Packet::of)

        private class Packet(private val contents: JsonNode) : Comparable<Packet> {
            override fun compareTo(other: Packet): Int =
                when {
                    contents.isInt && other.contents.isInt -> contents.asInt().compareTo(other.contents.asInt())

                    contents.isInt && other.contents.isArray -> of("[${this.contents}]").compareTo(other)

                    contents.isArray && other.contents.isInt -> compareTo(of("[${other.contents}]"))

                    else -> contents.zip(other.contents)
                        .map { (left, right) -> Packet(left).compareTo(Packet(right)) }
                        .firstOrNull { result -> result != 0 } ?: contents.count().compareTo(other.contents.count())
                }

            companion object {
                fun of(input: String) = Packet(json.readTree(input))
            }
        }
    }
}
