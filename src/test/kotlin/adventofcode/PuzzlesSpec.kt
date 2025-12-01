package adventofcode

import adventofcode.year2020.Day25ComboBreaker
import adventofcode.year2022.Day06TuningTrouble
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf

class PuzzlesSpec : FreeSpec({
    "all" - {
        "returns puzzles for all years" {
            Puzzles.all().map(Puzzle::year).toSet() shouldContainExactlyInAnyOrder (2015..2025).toSet()
        }
    }

    "forYear" - {
        "returns all puzzles in order for a given year" {
            val puzzles = Puzzles.forYear(2020)
            puzzles.shouldForAll { puzzle -> puzzle.year shouldBe 2020 }
            puzzles.map(Puzzle::day) shouldContainExactly puzzles.map(Puzzle::day).sorted()
        }

        "throws for a missing year" {
            val exception = shouldThrow<ClassNotFoundException> { Puzzles.forYear(2014) }
            exception.message shouldBe "Puzzles for year 2014 not found"
        }
    }

    "forDay" - {
        "returns the correct puzzle for a given year and day" {
            val puzzle = Puzzles.forDay(2020, 25)
            puzzle should beInstanceOf<Day25ComboBreaker>()
        }

        "returns the correct puzzle for a given year and single-digit day" {
            val puzzle = Puzzles.forDay(2022, 6)
            puzzle should beInstanceOf<Day06TuningTrouble>()
        }

        "throws for a missing year and day" {
            val exception = shouldThrow<ClassNotFoundException> { Puzzles.forDay(2021, 26) }
            exception.message shouldBe "Puzzle 2021/26 not found"
        }
    }
})
