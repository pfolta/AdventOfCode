package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.common.product

class Day11MonkeyInTheMiddle(customInput: String? = null) : Puzzle(customInput) {
    override val name = "Monkey in the Middle"

    private val monkeys by lazy { input.lines().chunked(7).map(::parseMonkey) }

    override fun partOne() = monkeys.playRounds(20).business()

    companion object {
        private data class Monkey(
            val items: MutableList<Int>,
            val worryLevelChange: (Int) -> Int,
            val targetMonkeyTest: Int,
            val trueTarget: Int,
            val falseTarget: Int
        ) {
            var itemsInspected = 0

            fun takeTurn(monkeys: List<Monkey>) {
                items.forEach { item ->
                    val worryLevel = worryLevelChange(item) / 3

                    val targetMonkey = when (worryLevel % targetMonkeyTest) {
                        0 -> trueTarget
                        else -> falseTarget
                    }

                    monkeys[targetMonkey].items += worryLevel
                }

                itemsInspected += items.count()
                items.clear()
            }
        }

        private fun parseMonkey(description: List<String>) = Monkey(
            items = description[1].split(", ", " ").mapNotNull(String::toIntOrNull).toMutableList(),
            worryLevelChange = { worryLevel ->
                val (operation, changeAmount) = description[2].split(" ").takeLast(2)

                when {
                    operation == "*" && changeAmount == "old" -> worryLevel * worryLevel
                    operation == "*" -> worryLevel * changeAmount.toInt()
                    else -> worryLevel + changeAmount.toInt()
                }
            },
            targetMonkeyTest = description[3].split(" ").last().toInt(),
            trueTarget = description[4].split(" ").last().toInt(),
            falseTarget = description[5].split(" ").last().toInt()
        )

        private fun List<Monkey>.playRounds(count: Int): List<Monkey> {
            repeat(count) {
                forEach { monkey -> monkey.takeTurn(this) }
            }

            return this
        }

        private fun List<Monkey>.business() = map(Monkey::itemsInspected).sortedDescending().take(2).product()
    }
}
