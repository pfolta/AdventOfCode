package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.common.json
import adventofcode.common.product
import com.fasterxml.jackson.databind.JsonNode

class Day13DistressSignal(customInput: String? = null) : Puzzle(customInput) {
    private val packetPairs by lazy { input.lines().chunked(3).map { (left, right) -> json.readTree(left) to json.readTree(right) } }

    override fun partOne() = packetPairs
        .mapIndexed { index, (left, right) -> index + 1 to (left isInOrderWith right)!! }
        .filter { (_, inOrder) -> inOrder }
        .sumOf { (index, _) -> index }

    override fun partTwo() = (packetPairs.flatMap { (left, right) -> listOf(left, right) } + DIVIDER_PACKETS)
        .sortedWith { a, b -> if ((a isInOrderWith b)!!) -1 else 1 }
        .mapIndexed { index, packet -> index + 1 to packet }
        .filter { (_, packet) -> packet in DIVIDER_PACKETS }
        .map { (index, _) -> index }
        .product()

    companion object {
        private val DIVIDER_PACKETS = listOf(listOf(listOf(2)), listOf(listOf(6))).map { packet -> json.valueToTree<JsonNode>(packet) }

        private infix fun JsonNode.isInOrderWith(other: JsonNode): Boolean? = when {
            canConvertToInt() && other.canConvertToInt() -> when {
                asInt() < other.asInt() -> true
                asInt() > other.asInt() -> false
                else -> null
            }

            canConvertToInt() && other.isArray -> json.valueToTree<JsonNode>(listOf(asInt())) isInOrderWith other

            isArray && other.canConvertToInt() -> this isInOrderWith json.valueToTree(listOf(other.asInt()))

            else -> zip(other).firstNotNullOfOrNull { (left, right) -> left isInOrderWith right }.let { result ->
                when {
                    result != null -> result
                    count() < other.count() -> true
                    count() > other.count() -> false
                    else -> null
                }
            }
        }
    }
}
