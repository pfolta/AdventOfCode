package adventofcode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

abstract class PuzzleBaseSpec(partOne: List<Pair<String?, Any>>, partTwo: List<Pair<String?, Any>>? = null) : FreeSpec() {
    constructor(partOne: Any, partTwo: Any? = null) : this(
        listOf(null to partOne),
        partTwo?.let { listOf(null to partTwo) }
    )

    private val puzzleConstructor = Class.forName(javaClass.name.removeSuffix("Spec")).getConstructor(String::class.java)

    init {
        partOne.forEachIndexed { index, (input, expectedOutput) ->
            val puzzle = puzzleConstructor.newInstance(input) as Puzzle

            "$puzzle, Part 1, Example ${index + 1}" {
                puzzle.partOne() shouldBe expectedOutput
            }
        }

        partTwo?.let {
            it.forEachIndexed { index, (input, expectedOutput) ->
                val puzzle = puzzleConstructor.newInstance(input) as Puzzle

                "$puzzle, Part 2, Example ${index + 1}" {
                    puzzle.partTwo() shouldBe expectedOutput
                }
            }
        }
    }
}

