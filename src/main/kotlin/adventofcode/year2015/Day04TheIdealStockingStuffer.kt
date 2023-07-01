package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.md5

class Day04TheIdealStockingStuffer(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override fun partOne() = generateSequence(0, Int::inc).first { (input + it).md5().startsWith("00000") }

    override fun partTwo() = generateSequence(0, Int::inc).first { (input + it).md5().startsWith("000000") }
}
