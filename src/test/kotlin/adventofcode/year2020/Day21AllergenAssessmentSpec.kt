package adventofcode.year2020

import adventofcode.year2020.Day21AllergenAssessment.part1
import adventofcode.year2020.Day21AllergenAssessment.part2
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day21AllergenAssessmentSpec : FreeSpec({
    "Day 21: Allergen Assessment" - {
        "Part 1" {
            val input = """
                mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                trh fvjkl sbzzf mxmxvkd (contains dairy)
                sqjhc fvjkl (contains soy)
                sqjhc mxmxvkd sbzzf (contains fish)
            """.trimIndent()

            part1(input) shouldBe 5
        }

        "Part 2" {
            val input = """
                mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                trh fvjkl sbzzf mxmxvkd (contains dairy)
                sqjhc fvjkl (contains soy)
                sqjhc mxmxvkd sbzzf (contains fish)
            """.trimIndent()

            part2(input) shouldBe "mxmxvkd,sqjhc,fvjkl"
        }
    }
})
