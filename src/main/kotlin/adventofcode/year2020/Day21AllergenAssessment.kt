package adventofcode.year2020

import adventofcode.Puzzle

class Day21AllergenAssessment(customInput: String? = null) : Puzzle(customInput) {
    private val foods = input.lines().map(::Food)

    override fun partOne(): Int {
        val ingredientsToAllergens = foods.ingredientsAllergensMap()

        return foods.flatMap(Food::ingredients).filter { !ingredientsToAllergens.contains(it) }.size
    }

    override fun partTwo() = foods.ingredientsAllergensMap().entries.sortedBy { it.value }.joinToString(",") { it.key }

    companion object {
        data class Food(
            val ingredients: List<String>,
            val allergens: List<String>
        ) {
            constructor(food: String) : this(
                food.split("(").first().trim().split(" "),
                if (food.contains("(")) food.split("(").last().replace("contains ", "").replace(")", "").split(", ") else emptyList()
            )
        }

        fun List<Food>.ingredientsAllergensMap() = flatMap { food ->
            food.allergens.map { allergen ->
                food.ingredients.last { ingredient -> filter { it.allergens.contains(allergen) }.all { it.ingredients.contains(ingredient) } } to allergen
            }
        }.toMap()
    }
}
