package adventofcode.year2020

import adventofcode.utils.readInputAsString
import adventofcode.year2020.Day21AllergenAssessment.part1
import adventofcode.year2020.Day21AllergenAssessment.part2

object Day21AllergenAssessment {
    private data class Food(
        val ingredients: List<String>,
        val allergens: List<String>
    ) {
        constructor(food: String) : this(
            food.split("(").first().trim().split(" "),
            if (food.contains("(")) food.split("(").last().replace("contains ", "").replace(")", "").split(", ") else emptyList()
        )
    }

    private fun buildIngredientsToAllergensMap(foods: List<Food>) = foods.flatMap { food ->
        food.allergens.map { allergen ->
            food.ingredients.last { ingredient ->
                foods.filter { it.allergens.contains(allergen) }.all { it.ingredients.contains(ingredient) }
            } to allergen
        }
    }.toMap()

    fun part1(input: String): Int {
        val foods = input.lines().map(::Food)

        val ingredientsToAllergens = buildIngredientsToAllergensMap(foods)

        return foods.flatMap(Food::ingredients).filter { !ingredientsToAllergens.contains(it) }.size
    }

    fun part2(input: String) =
        buildIngredientsToAllergensMap(input.lines().map(::Food)).entries.sortedBy { it.value }.joinToString(",") { it.key }
}

fun main() {
    val input = readInputAsString(2020, 21)

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
