package adventofcode.year2020

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day21AllergenAssessment(
    customInput: PuzzleInput? = null,
) : Puzzle(customInput) {
    private val foods by lazy { input.lines().map(Food::invoke) }

    override fun partOne(): Int {
        val ingredientsToAllergens = foods.ingredientsAllergensMap()

        return foods.flatMap(Food::ingredients).filter { !ingredientsToAllergens.contains(it) }.size
    }

    override fun partTwo() =
        foods
            .ingredientsAllergensMap()
            .entries
            .sortedBy { it.value }
            .joinToString(",") { it.key }

    companion object {
        private data class Food(
            val ingredients: List<String>,
            val allergens: List<String>,
        ) {
            companion object {
                operator fun invoke(input: String): Food {
                    val ingredients =
                        input
                            .split("(")
                            .first()
                            .trim()
                            .split(" ")
                    val allergens =
                        when {
                            input.contains("(") ->
                                input
                                    .split("(")
                                    .last()
                                    .replace("contains ", "")
                                    .replace(")", "")
                                    .split(", ")
                            else -> emptyList()
                        }

                    return Food(ingredients, allergens)
                }
            }
        }

        private fun List<Food>.ingredientsAllergensMap() =
            flatMap { food ->
                food.allergens.map { allergen ->
                    food.ingredients.last { ingredient ->
                        filter { it.allergens.contains(allergen) }.all { it.ingredients.contains(ingredient) }
                    } to allergen
                }
            }.toMap()
    }
}
