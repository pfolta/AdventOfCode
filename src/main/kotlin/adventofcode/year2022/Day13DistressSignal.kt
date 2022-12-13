package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.common.json
import com.fasterxml.jackson.databind.JsonNode

class Day13DistressSignal(customInput: String? = null) : Puzzle(customInput) {
    private val packetPairs by lazy { input.lines().chunked(3).map { (left, right) -> json.readTree(left) to json.readTree(right) } }

    override fun partOne() = packetPairs
        .mapIndexed { index, (left, right) -> index + 1 to (left isInOrderWith right) }
        .filter { (_, inOrder) -> inOrder ?: false }
        .sumOf { (index, _) -> index }

    companion object {
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
