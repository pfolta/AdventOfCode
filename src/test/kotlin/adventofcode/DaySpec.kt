package adventofcode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

abstract class DaySpec(partOneExpected: Any, partTwoExpected: Any? = null) : FreeSpec() {
    private val day = Class.forName(javaClass.name.removeSuffix("Spec")).kotlin.objectInstance as Day

    init {
        "$day, Part 1" {
            day.partOne() shouldBe partOneExpected
        }

        partTwoExpected?.let {
            "$day, Part 2" {
                day.partTwo() shouldBe partTwoExpected
            }
        }
    }
}

