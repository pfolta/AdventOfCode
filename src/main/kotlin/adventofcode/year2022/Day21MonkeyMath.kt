package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.String.containsOnlyDigits
import adventofcode.year2022.Day21MonkeyMath.Companion.Operation.DIV
import adventofcode.year2022.Day21MonkeyMath.Companion.Operation.MINUS
import adventofcode.year2022.Day21MonkeyMath.Companion.Operation.PLUS
import adventofcode.year2022.Day21MonkeyMath.Companion.Operation.TIMES

class Day21MonkeyMath(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val monkeys by lazy {
        input
            .lines()
            .map { it.split(": ") }
            .associate { (name, expression) -> name to Monkey(expression) }
            .also { monkeys ->
                monkeys
                    .values
                    .filterIsInstance<MathMonkey>()
                    .forEach { monkey ->
                        monkey.leftMonkey = monkeys[monkey.leftMonkeyName]!!
                        monkey.rightMonkey = monkeys[monkey.rightMonkeyName]!!
                    }
            }
    }

    override fun partOne() = monkeys["root"]!!.yell()

    companion object {
        private enum class Operation(val operation: String) {
            PLUS("+"),
            MINUS("-"),
            TIMES("*"),
            DIV("/");

            companion object {
                operator fun invoke(operation: String) = entries.associateBy(Operation::operation)[operation]!!
            }
        }

        private sealed class Monkey {
            abstract fun yell(): Long

            companion object {
                operator fun invoke(expression: String): Monkey =
                    when (expression.containsOnlyDigits()) {
                        true -> NumberMonkey(expression.toLong())

                        false -> {
                            val (leftMonkeyName, operation, rightMonkeyName) = expression.split(" ")
                            MathMonkey(leftMonkeyName, Operation(operation), rightMonkeyName)
                        }
                    }
            }
        }

        private class NumberMonkey(val number: Long) : Monkey() {
            override fun yell() = number
        }

        private class MathMonkey(val leftMonkeyName: String, val operation: Operation, val rightMonkeyName: String) : Monkey() {
            lateinit var leftMonkey: Monkey
            lateinit var rightMonkey: Monkey

            override fun yell() =
                when (operation) {
                    PLUS -> leftMonkey.yell() + rightMonkey.yell()
                    MINUS -> leftMonkey.yell() - rightMonkey.yell()
                    TIMES -> leftMonkey.yell() * rightMonkey.yell()
                    DIV -> leftMonkey.yell() / rightMonkey.yell()
                }
        }
    }
}
