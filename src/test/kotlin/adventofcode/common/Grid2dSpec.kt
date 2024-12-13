package adventofcode.common

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.beEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class Grid2dSpec : FreeSpec({
    val grid =
        Grid2d(
            listOf(
                listOf('A', 'B', 'C'),
                listOf('D', 'E', 'F'),
                listOf('G', 'H', 'I'),
            ),
        )

    "isSquare" - {
        "is `true` for a square grid" {
            grid.isSquare shouldBe true
        }

        "is `false` for a non-square grid" {
            Grid2d(
                listOf(
                    listOf('A', 'B', 'C', 'D'),
                    listOf('E', 'F', 'G', 'H'),
                    listOf('I', 'J', 'K', 'L'),
                ),
            ).isSquare shouldBe false
        }
    }

    "contains value operator" - {
        "returns `true` if the grid contains that value" {
            ('E' in grid) shouldBe true
        }

        "returns `false` if the grid does not contain that value" {
            ('Z' in grid) shouldBe false
        }
    }

    "contains Point2d operator" - {
        "returns `true` if the grid contains that point" {
            (Point2d(0, 0) in grid) shouldBe true
            (Point2d(1, 1) in grid) shouldBe true
        }

        "returns `false` if the grid does not contain that value" {
            (Point2d(0, -1) in grid) shouldBe false
            (Point2d(4, 0) in grid) shouldBe false
        }
    }

    "find" - {
        "returns the point of that value if the grid contains that value exactly once" {
            grid.find('E') shouldContainExactly listOf(Point2d(1, 1))
        }

        "returns the points of all instances if the grid contains that value" {
            Grid2d(
                listOf(
                    listOf('A', 'B', 'C'),
                    listOf('D', 'A', 'F'),
                    listOf('G', 'H', 'A'),
                ),
            ).find('A') shouldContainExactly listOf(Point2d(0, 0), Point2d(1, 1), Point2d(2, 2))
        }

        "returns an empty list if the grid does not contain that value" {
            grid.find('Z') should beEmpty()
        }
    }

    "get" - {
        "returns the value at the given point if it exists" {
            grid[Point2d(2, 1)] shouldBe 'F'
        }

        "throws if the point is not part of the grid" {
            shouldThrow<IndexOutOfBoundsException> {
                grid[Point2d(10, 10)]
            }.apply {
                message shouldBe "Point (10, 10) is outside of the grid"
            }
        }
    }

    "getOrNull" - {
        "returns the value at the given point if it exists" {
            grid.getOrNull(Point2d(2, 1)) shouldBe 'F'
        }

        "returns null if the point is not part of the grid" {
            grid.getOrNull(Point2d(10, 10)) shouldBe null
        }
    }

    "neighborsOf" - {
        "returns the cardinal neighbors of a point in the grid" {
            grid.neighborsOf(Point2d(1, 1)) shouldContainExactlyInAnyOrder setOf(Point2d(1, 0), Point2d(2, 1), Point2d(1, 2), Point2d(0, 1))
        }

        "returns only valid cardinal neighbors of a point on the edge of grid" {
            grid.neighborsOf(Point2d(0, 0)) shouldContainExactlyInAnyOrder setOf(Point2d(1, 0), Point2d(0, 1))
            grid.neighborsOf(Point2d(2, 1)) shouldContainExactlyInAnyOrder setOf(Point2d(2, 0), Point2d(2, 2), Point2d(1, 1))
        }

        "returns cardinal and diagonal neighbors of a point in the grid when includeDiagonals is `true`" {
            grid.neighborsOf(Point2d(1, 1), includeDiagonals = true) shouldContainExactlyInAnyOrder
                setOf(
                    Point2d(1, 0),
                    Point2d(2, 0),
                    Point2d(2, 1),
                    Point2d(2, 2),
                    Point2d(1, 2),
                    Point2d(0, 2),
                    Point2d(0, 1),
                    Point2d(0, 0),
                )
        }
    }

    "toString" {
        grid.toString() shouldBe
            """
            [A, B, C]
            [D, E, F]
            [G, H, I]
            """.trimIndent()
    }
})
