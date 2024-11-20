package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day05DoesntHeHaveInternElvesForThis(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Doesn't He Have Intern-Elves For This?"

    override fun partOne() =
        input
            .lines()
            .filter {
                    str ->
                str.groupingBy { it }.eachCount().filter { setOf('a', 'e', 'i', 'o', 'u').contains(it.key) }.values.sum() >= 3
            }
            .filter { str -> ('a'..'z').any { str.contains(it.toString().repeat(2)) } }
            .filter { str -> setOf("ab", "cd", "pq", "xy").none { str.contains(it) } }
            .size

    override fun partTwo() =
        input
            .lines()
            .filter {
                    str ->
                ('a'..'z').flatMap { a -> ('a'..'z').map { b -> "$a$b" } }.any { """$it\w*$it""".toRegex().containsMatchIn(str) }
            }
            .filter { str -> ('a'..'z').any { """$it\w$it""".toRegex().containsMatchIn(str) } }
            .size
}
