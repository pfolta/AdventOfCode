package adventofcode.year2015

import adventofcode.Puzzle
import adventofcode.PuzzleInput
import adventofcode.common.cartesianProduct
import kotlin.math.max

class Day15ScienceForHungryPeople(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    override val name = "Science for Hungry People"

    private val ingredients by lazy { input.lines().map(::Ingredient) }

    private val recipes by lazy {
        (1..ingredients.size)
            .map { (0..TABLESPOON_COUNT).toList() }
            .cartesianProduct()
            .filter { it.sum() == TABLESPOON_COUNT }
            .map { it.mapIndexed { index, amount -> ingredients[index] * amount } }
            .map { it.sum() }
    }

    override fun partOne() = recipes.map { it.score }.maxOrNull()!!

    override fun partTwo() = recipes.filter { it.calories == CALORIE_COUNT }.map { it.score }.maxOrNull()!!

    companion object {
        private val INPUT_REGEX = """capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (\d+)""".toRegex()

        private const val TABLESPOON_COUNT = 100
        private const val CALORIE_COUNT = 500

        data class Ingredient(
            val capacity: Int,
            val durability: Int,
            val flavor: Int,
            val texture: Int,
            val calories: Int
        ) {
            constructor(input: String) : this(
                INPUT_REGEX.find(input)!!.destructured.component1().toInt(),
                INPUT_REGEX.find(input)!!.destructured.component2().toInt(),
                INPUT_REGEX.find(input)!!.destructured.component3().toInt(),
                INPUT_REGEX.find(input)!!.destructured.component4().toInt(),
                INPUT_REGEX.find(input)!!.destructured.component5().toInt()
            )

            val score = max(0, capacity) * max(0, durability) * max(0, flavor) * max(0, texture)

            operator fun times(amount: Int) =
                Ingredient(amount * capacity, amount * durability, amount * flavor, amount * texture, amount * calories)

            operator fun plus(other: Ingredient) = Ingredient(
                capacity + other.capacity,
                durability + other.durability,
                flavor + other.flavor,
                texture + other.texture,
                calories + other.calories
            )
        }

        fun Collection<Ingredient>.sum(): Ingredient = reduce { sum, summand -> sum + summand }
    }
}
