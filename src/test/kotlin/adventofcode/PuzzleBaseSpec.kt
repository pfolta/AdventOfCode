package adventofcode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

abstract class PuzzleBaseSpec(
    partOne: List<Pair<PuzzleInput?, PuzzleOutput>>,
    partTwo: List<Pair<PuzzleInput?, PuzzleOutput>>? = null
) : FreeSpec() {
    constructor(partOne: PuzzleOutput, partTwo: PuzzleOutput? = null) : this(
        listOf(null to partOne),
        partTwo?.let { listOf(null to partTwo) }
    )

    private val puzzleConstructor = Class
        .forName(javaClass.name.removeSuffix("Spec"))
        .asSubclass(Puzzle::class.java)
        .getConstructor(String::class.java)

    init {
        "${puzzleConstructor.newInstance("")}" - {
            "⭐️ Part 1" - {
                partOne.forEachIndexed { index, (input, expectedOutput) ->
                    val puzzle = puzzleConstructor.newInstance(input)

                    "Example ${index + 1}" {
                        puzzle.partOne() shouldBe expectedOutput
                    }
                }
            }

            partTwo?.let {
                "⭐️ Part 2" - {
                    partTwo.forEachIndexed { index, (input, expectedOutput) ->
                        val puzzle = puzzleConstructor.newInstance(input)

                        "Example ${index + 1}" {
                            puzzle.partTwo() shouldBe expectedOutput
                        }
                    }
                }
            }
        }
    }
}
