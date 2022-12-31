package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day21MonkeyMath(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val monkeys by lazy {
        input
            .lines()
            .map { it.split(": ") }
            .associate { (name, input) -> name to Monkey.of(input) }
    }

    override fun partOne() = monkeys["root"]!!.yell(monkeys)

    companion object {
        private sealed class Monkey {
            abstract fun yell(monkeys: Map<String, Monkey>): Long

            companion object {
                fun of(input: String): Monkey =
                    when (input.all { it.isDigit() }) {
                        true -> NumberMonkey(input.toLong())

                        false -> {
                            val (left, operation, right) = input.split(" ")
                            MathMonkey(left, operation, right)
                        }
                    }
            }
        }

        private class NumberMonkey(val number: Long) : Monkey() {
            override fun yell(monkeys: Map<String, Monkey>) = number
        }

        private class MathMonkey(val left: String, val operation: String, val right: String) : Monkey() {
            override fun yell(monkeys: Map<String, Monkey>) =
                when (operation) {
                    "+" -> monkeys[left]!!.yell(monkeys) + monkeys[right]!!.yell(monkeys)
                    "-" -> monkeys[left]!!.yell(monkeys) - monkeys[right]!!.yell(monkeys)
                    "*" -> monkeys[left]!!.yell(monkeys) * monkeys[right]!!.yell(monkeys)
                    "/" -> monkeys[left]!!.yell(monkeys) / monkeys[right]!!.yell(monkeys)
                    else -> throw IllegalArgumentException("'$operation' is not a supported math operation")
                }
        }
    }
}
