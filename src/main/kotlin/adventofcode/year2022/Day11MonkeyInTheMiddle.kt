package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.product

class Day11MonkeyInTheMiddle(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Monkey in the Middle"

    override fun partOne() = input
        .parseMonkeys()
        .playRounds(20) { worryLevel -> worryLevel / 3 }
        .business()

    override fun partTwo(): Long {
        val monkeys = input.parseMonkeys()
        val targetMonkeyTestProduct = monkeys.map(Monkey::targetMonkeyTest).product()

        return monkeys
            .playRounds(10000) { worryLevel -> worryLevel % targetMonkeyTestProduct }
            .business()
    }

    companion object {
        private data class Monkey(
            val items: MutableList<Long>,
            val worryLevelChange: (Long) -> Long,
            val targetMonkeyTest: Long,
            val trueTarget: Int,
            val falseTarget: Int
        ) {
            var itemsInspected = 0L

            constructor(description: List<String>) : this(
                items = description[1].split(", ", " ").mapNotNull(String::toLongOrNull).toMutableList(),
                worryLevelChange = { worryLevel ->
                    val (operation, changeAmount) = description[2].split(" ").takeLast(2)

                    when {
                        operation == "*" && changeAmount == "old" -> worryLevel * worryLevel
                        operation == "*" -> worryLevel * changeAmount.toLong()
                        else -> worryLevel + changeAmount.toLong()
                    }
                },
                targetMonkeyTest = description[3].split(" ").last().toLong(),
                trueTarget = description[4].split(" ").last().toInt(),
                falseTarget = description[5].split(" ").last().toInt()
            )

            fun takeTurn(monkeys: List<Monkey>, worryLevelRelief: (Long) -> Long) {
                items.forEach { item ->
                    val worryLevel = worryLevelRelief(worryLevelChange(item))

                    val targetMonkey = when (worryLevel % targetMonkeyTest) {
                        0L -> trueTarget
                        else -> falseTarget
                    }

                    monkeys[targetMonkey].items += worryLevel
                }

                itemsInspected += items.count()
                items.clear()
            }
        }

        private fun String.parseMonkeys() = lines().chunked(7).map(::Monkey)

        private fun List<Monkey>.playRounds(count: Int, worryLevelRelief: (Long) -> Long): List<Monkey> {
            repeat(count) {
                forEach { monkey -> monkey.takeTurn(this, worryLevelRelief) }
            }

            return this
        }

        private fun List<Monkey>.business() = map(Monkey::itemsInspected).sortedDescending().take(2).product()
    }
}
