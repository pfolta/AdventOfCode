package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day07HandyHaversacks(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val bagRules by lazy {
        input.lines().map { rule ->
            val (color) = BAG_RULE_REGEX.find(rule)!!.destructured

            val contents =
                BAG_CONTENTS_REGEX
                    .findAll(rule)
                    .map { it.destructured }
                    .associate { (amount, color) -> color to amount.toInt() }

            Bag(color, contents)
        }
    }

    override fun partOne() =
        bagRules
            .count { it.contains(bagRules, "shiny gold") }

    override fun partTwo() =
        bagRules
            .get("shiny gold")
            .size(bagRules)
            .minus(1)

    companion object {
        val BAG_RULE_REGEX = """(\w+ \w+) bags contain (.*)""".toRegex()
        val BAG_CONTENTS_REGEX = """(\d+) (\w+ \w+) bags?(, )?""".toRegex()

        private data class Bag(
            val color: String,
            val contents: Map<String, Int>,
        ) {
            fun contains(
                bagRules: List<Bag>,
                searchPattern: String,
            ): Boolean {
                if (contents.isEmpty()) return false
                if (contents.containsKey(searchPattern)) return true

                return contents
                    .map { bagRules.get(it.key).contains(bagRules, searchPattern) }
                    .contains(true)
            }

            fun size(bagRules: List<Bag>): Int {
                if (contents.isEmpty()) return 1

                return contents
                    .map { bagRules.get(it.key).size(bagRules) * it.value }
                    .sum()
                    .plus(1)
            }
        }

        private fun List<Bag>.get(color: String) = this.first { it.color == color }
    }
}
