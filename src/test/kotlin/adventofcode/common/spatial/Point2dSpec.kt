package adventofcode.common.spatial

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class Point2dSpec : FreeSpec({
    "toString" - {
        "returns pretty formatted String representation of the point" {
            Point2d(1024, 2048).toString() shouldBe "(1024, 2048)"
        }
    }

    "operator plus" - {
        "other Point2d" - {
            "adds two Point2ds correctly together by adding each of their coordinates together" {
                Point2d(1024, 2048) + Point2d(1024, 2048) shouldBe Point2d(2048, 4096)
            }
        }

        "constant Number offset" - {
            "shifts both coordinates of the point by the offset provided" {
                Point2d(1, 2) + 3 shouldBe Point2d(4, 5)
            }
        }
    }

    "neighbors" - {
        "excluding diagonals" - {
            "returns the cardinal neighbors of the point" {
                Point2d(0, 0).neighbors() shouldContainExactlyInAnyOrder
                    setOf(
                        Point2d(0, -1),
                        Point2d(1, 0),
                        Point2d(0, 1),
                        Point2d(-1, 0),
                    )
            }
        }

        "including diagnoals" - {
            "returns all neighbors of the point" {
                Point2d(5, 4).neighbors(includeDiagonals = true) shouldContainExactlyInAnyOrder
                    setOf(
                        Point2d(5, 3),
                        Point2d(6, 3),
                        Point2d(6, 4),
                        Point2d(6, 5),
                        Point2d(5, 5),
                        Point2d(4, 5),
                        Point2d(4, 4),
                        Point2d(4, 3),
                    )
            }
        }
    }

    "distanceTo" - {
        "returns the Manhattan distance between two points" {
            Point2d(1, 1) distanceTo Point2d(2, 3) shouldBe 3
        }

        "returns the Manhattan distance between two points with negative coordinates" {
            Point2d(-2, -3) distanceTo Point2d(4, 5) shouldBe 14
        }
    }
})
