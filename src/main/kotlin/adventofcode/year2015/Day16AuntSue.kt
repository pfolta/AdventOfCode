package adventofcode.year2015

import adventofcode.Puzzle

class Day16AuntSue(customInput: String? = null) : Puzzle(customInput) {
    private val aunts by lazy {
        input
            .lines()
            .map { aunt ->
                val id = aunt.split(": ").first().split(" ").last().toInt()
                val fields = aunt.substring(4 + id.toString().length + 2).split(", ").map { it.split(": ") }

                val children = fields.find { it.first() == "children" }?.last()?.toInt()
                val cats = fields.find { it.first() == "cats" }?.last()?.toInt()
                val samoyeds = fields.find { it.first() == "samoyeds" }?.last()?.toInt()
                val pomeranians = fields.find { it.first() == "pomeranians" }?.last()?.toInt()
                val akitas = fields.find { it.first() == "akitas" }?.last()?.toInt()
                val vizslas = fields.find { it.first() == "vizslas" }?.last()?.toInt()
                val goldfish = fields.find { it.first() == "goldfish" }?.last()?.toInt()
                val trees = fields.find { it.first() == "trees" }?.last()?.toInt()
                val cars = fields.find { it.first() == "cars" }?.last()?.toInt()
                val perfumes = fields.find { it.first() == "perfumes" }?.last()?.toInt()

                AuntSue(id, children, cats, samoyeds, pomeranians, akitas, vizslas, goldfish, trees, cars, perfumes)
            }
    }

    override fun partOne() = aunts
        .asSequence()
        .filter { it.children == null || it.children == TICKER_TAPE_SUE.children!! }
        .filter { it.cats == null || it.cats == TICKER_TAPE_SUE.cats!! }
        .filter { it.samoyeds == null || it.samoyeds == TICKER_TAPE_SUE.samoyeds!! }
        .filter { it.pomeranians == null || it.pomeranians == TICKER_TAPE_SUE.pomeranians!! }
        .filter { it.akitas == null || it.akitas == TICKER_TAPE_SUE.akitas!! }
        .filter { it.vizslas == null || it.vizslas == TICKER_TAPE_SUE.vizslas!! }
        .filter { it.goldfish == null || it.goldfish == TICKER_TAPE_SUE.goldfish!! }
        .filter { it.trees == null || it.trees == TICKER_TAPE_SUE.trees!! }
        .filter { it.cars == null || it.cars == TICKER_TAPE_SUE.cars!! }
        .filter { it.perfumes == null || it.perfumes == TICKER_TAPE_SUE.perfumes!! }
        .first()
        .id!!

    override fun partTwo() = aunts
        .asSequence()
        .filter { it.children == null || it.children == TICKER_TAPE_SUE.children!! }
        .filter { it.cats == null || it.cats > TICKER_TAPE_SUE.cats!! }
        .filter { it.samoyeds == null || it.samoyeds == TICKER_TAPE_SUE.samoyeds!! }
        .filter { it.pomeranians == null || it.pomeranians < TICKER_TAPE_SUE.pomeranians!! }
        .filter { it.akitas == null || it.akitas == TICKER_TAPE_SUE.akitas!! }
        .filter { it.vizslas == null || it.vizslas == TICKER_TAPE_SUE.vizslas!! }
        .filter { it.goldfish == null || it.goldfish < TICKER_TAPE_SUE.goldfish!! }
        .filter { it.trees == null || it.trees > TICKER_TAPE_SUE.trees!! }
        .filter { it.cars == null || it.cars == TICKER_TAPE_SUE.cars!! }
        .filter { it.perfumes == null || it.perfumes == TICKER_TAPE_SUE.perfumes!! }
        .first()
        .id!!

    companion object {
        private val TICKER_TAPE_SUE = AuntSue(
            children = 3,
            cats = 7,
            samoyeds = 2,
            pomeranians = 3,
            akitas = 0,
            vizslas = 0,
            goldfish = 5,
            trees = 3,
            cars = 2,
            perfumes = 1
        )

        data class AuntSue(
            val id: Int? = null,
            val children: Int? = null,
            val cats: Int? = null,
            val samoyeds: Int? = null,
            val pomeranians: Int? = null,
            val akitas: Int? = null,
            val vizslas: Int? = null,
            val goldfish: Int? = null,
            val trees: Int? = null,
            val cars: Int? = null,
            val perfumes: Int? = null
        )
    }
}
