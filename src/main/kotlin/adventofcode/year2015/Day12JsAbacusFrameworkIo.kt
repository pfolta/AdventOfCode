package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.Json.objectMapper
import com.fasterxml.jackson.databind.JsonNode

class Day12JsAbacusFrameworkIo(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    override val name = "JSAbacusFramework.io"

    override fun partOne() = objectMapper.readTree(input).sum()

    override fun partTwo() = objectMapper.readTree(input).stripRed().sum()

    companion object {
        private fun JsonNode.stripRed(): JsonNode {
            if (isObject && any { child -> child.isValueNode && child.textValue() == "red" }) {
                removeAll { true }
            }

            forEach { child -> child.stripRed() }

            return this
        }

        private fun JsonNode.sum(): Int =
            when {
                isInt -> intValue()
                else -> sumOf { it.sum() }
            }
    }
}
