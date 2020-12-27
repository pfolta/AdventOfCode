package adventofcode.year2020

import adventofcode.Puzzle

class Day23CrabCups(customInput: String? = null) : Puzzle(customInput) {
    override fun partOne(): Int {
        val result = input.toCharArray().map(Char::toString).map(String::toInt).toMutableList().playCrabCups(100)
        return (result.subList(result.indexOf(1) + 1, result.size) + result.subList(0, result.indexOf(1))).joinToString("").toInt()
    }

    companion object {
        private fun List<Int>.destination(current: Int) = if (current - 1 < minOrNull()!!) maxOrNull()!! else current - 1

        private fun MutableList<Int>.playCrabCups(rounds: Int): MutableList<Int> {
            var currentCup = first()

            repeat((1..rounds).count()) {
                val pickUp = (1..3).map { this[(indexOf(currentCup) + it) % size] }
                removeAll(pickUp)

                val destination = generateSequence(destination(currentCup)) { destination(it) }
                    .first { !pickUp.contains(it) }

                val destinationIndex = indexOf(destination)

                addAll(destinationIndex + 1, pickUp)

                currentCup = this[(indexOf(currentCup) + 1) % size]
            }

            return this
        }
    }
}
