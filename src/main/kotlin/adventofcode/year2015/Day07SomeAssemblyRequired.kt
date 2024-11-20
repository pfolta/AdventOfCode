package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.String.containsOnlyDigits
import adventofcode.year2015.Day07SomeAssemblyRequired.Companion.Operation.AND
import adventofcode.year2015.Day07SomeAssemblyRequired.Companion.Operation.LSHIFT
import adventofcode.year2015.Day07SomeAssemblyRequired.Companion.Operation.NOT
import adventofcode.year2015.Day07SomeAssemblyRequired.Companion.Operation.OR
import adventofcode.year2015.Day07SomeAssemblyRequired.Companion.Operation.PASSTHRU
import adventofcode.year2015.Day07SomeAssemblyRequired.Companion.Operation.RSHIFT

class Day07SomeAssemblyRequired(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private fun parseInput() =
        input
            .lines()
            .map { it.split(" -> ") }
            .associate { (expression, id) -> id to Wire(expression) }

    override fun partOne() = parseInput().resolveWires()["a"]!!.value

    override fun partTwo() = parseInput().minus("b").plus("b" to Wire(null, PASSTHRU, partOne().toString())).resolveWires()["a"]!!.value

    companion object {
        private enum class Operation(val operation: String) {
            AND("AND"),
            LSHIFT("LSHIFT"),
            NOT("NOT"),
            OR("OR"),
            PASSTHRU(""),
            RSHIFT("RSHIFT"),
            ;

            companion object {
                operator fun invoke(operation: String) = entries.associateBy(Operation::operation)[operation]!!
            }
        }

        private class Wire(val leftSide: String?, val operation: Operation, val rightSide: String) {
            lateinit var leftWire: Wire
            lateinit var rightWire: Wire

            // Use lazy delegate to compute value once on first access and return memoized result in subsequent calls
            val value: UShort by lazy {
                val leftValue =
                    when (leftSide?.containsOnlyDigits()) {
                        true -> leftSide.toUShort()
                        false -> leftWire.value
                        null -> null
                    }

                val rightValue =
                    when (rightSide.containsOnlyDigits()) {
                        true -> rightSide.toUShort()
                        false -> rightWire.value
                    }

                when (operation) {
                    AND -> leftValue!! and rightValue
                    LSHIFT -> leftValue!! shl rightValue
                    NOT -> rightValue.inv()
                    OR -> leftValue!! or rightValue
                    PASSTHRU -> rightValue
                    RSHIFT -> leftValue!! shr rightValue
                }
            }

            companion object {
                operator fun invoke(expression: String): Wire {
                    val parts = expression.split(" ")

                    return when (parts.size) {
                        1 -> Wire(null, PASSTHRU, parts.first())
                        2 -> Wire(null, Operation(parts.first()), parts.last())
                        else -> Wire(parts[0], Operation(parts[1]), parts[2])
                    }
                }
            }
        }

        private fun Map<String, Wire>.resolveWires(): Map<String, Wire> =
            onEach { (_, wire) ->
                if (wire.leftSide != null && !wire.leftSide.containsOnlyDigits()) wire.leftWire = this[wire.leftSide]!!
                if (!wire.rightSide.containsOnlyDigits()) wire.rightWire = this[wire.rightSide]!!
            }

        // Kotlin does not have bit-shifting operations on `UShort`s, so temporarily convert to `Int`, shift and then back to `UShort`
        private infix fun UShort.shl(other: UShort): UShort = (this.toInt() shl other.toInt()).toUShort()

        private infix fun UShort.shr(other: UShort): UShort = (this.toInt() shr other.toInt()).toUShort()
    }
}
