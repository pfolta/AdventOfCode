package adventofcode.common.spatial

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.beEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class Grid2dSpec :
    FreeSpec({
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

        "toString" - {
            "returns pretty formatted String representation of the point" {
                grid.toString() shouldBe
                    """
                    [A, B, C]
                    [D, E, F]
                    [G, H, I]
                    """.trimIndent()
            }
        }

        "row" - {
            "returns the contents of the row with the given index" {
                grid.row(1) shouldContainExactly listOf('D', 'E', 'F')
            }

            "throws if the index is outside the grid" {
                shouldThrow<IndexOutOfBoundsException> { grid.row(-1) }
                shouldThrow<IndexOutOfBoundsException> { grid.row(grid.rows + 1) }
            }
        }

        "column" - {
            "returns the contents of the column with the given index" {
                grid.column(1) shouldContainExactly listOf('B', 'E', 'H')
            }

            "throws if the index is outside the grid" {
                shouldThrow<IndexOutOfBoundsException> { grid.column(-1) }
                shouldThrow<IndexOutOfBoundsException> { grid.column(grid.columnsInRow(0) + 1) }
            }
        }

        "rows" - {
            "returns the values of the grid as a List<List<T>>" {
                grid.rows() shouldContainExactly grid.values
            }
        }

        "columns" - {
            "returns the columns of the grid as a List<List<T>>" {
                grid.columns() shouldContainExactly
                    listOf(
                        listOf('A', 'D', 'G'),
                        listOf('B', 'E', 'H'),
                        listOf('C', 'F', 'I'),
                    )
            }
        }

        "operator contains" - {
            "by value" - {
                "returns `true` if the grid contains that value" {
                    ('E' in grid) shouldBe true
                }

                "returns `false` if the grid does not contain that value" {
                    ('Z' in grid) shouldBe false
                }
            }

            "by coordinates" - {
                "returns `true` if the grid contains that point" {
                    (Point2d(0, 0) in grid) shouldBe true
                    (Point2d(1, 1) in grid) shouldBe true
                }

                "returns `false` if the grid does not contain that value" {
                    (Point2d(0, -1) in grid) shouldBe false
                    (Point2d(4, 0) in grid) shouldBe false
                }
            }
        }

        "findAll" - {
            "returns the point of that value if the grid contains that value exactly once" {
                grid.findAll('E') shouldContainExactly listOf(Point2d(1, 1))
            }

            "returns the points of all instances if the grid contains that value" {
                Grid2d(
                    listOf(
                        listOf('A', 'B', 'C'),
                        listOf('D', 'A', 'F'),
                        listOf('G', 'H', 'A'),
                    ),
                ).findAll('A') shouldContainExactly listOf(Point2d(0, 0), Point2d(1, 1), Point2d(2, 2))
            }

            "returns an empty list if the grid does not contain that value" {
                grid.findAll('Z') should beEmpty()
            }
        }

        "operator get" - {
            "by value" - {
                "returns the coordinates of the singular point containing the value" {
                    grid['F'] shouldBe Point2d(2, 1)
                }

                "throws if the grid does not contain the value" {
                    shouldThrow<NoSuchElementException> { grid['Z'] }
                }
            }

            "by coordinates" - {
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
                grid.neighborsOf(Point2d(1, 1)) shouldContainExactlyInAnyOrder
                    setOf(Point2d(1, 0), Point2d(2, 1), Point2d(1, 2), Point2d(0, 1))
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

        "rotate" - {
            "returns a new grid correctly rotated" {
                grid.rotate() shouldBe
                    Grid2d(
                        listOf(
                            listOf('G', 'D', 'A'),
                            listOf('H', 'E', 'B'),
                            listOf('I', 'F', 'C'),
                        ),
                    )
            }

            "leaves the original grid untouched" {
                grid.rotate()
                grid shouldBe
                    Grid2d(
                        listOf(
                            listOf('A', 'B', 'C'),
                            listOf('D', 'E', 'F'),
                            listOf('G', 'H', 'I'),
                        ),
                    )
            }

            "rotating 4 times results in the original grid" {
                generateSequence(grid, Grid2d<Char>::rotate).drop(1).take(4).last() shouldBe grid
            }
        }
    })
