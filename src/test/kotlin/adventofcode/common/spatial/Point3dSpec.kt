package adventofcode.common.spatial

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlin.math.sqrt

class Point3dSpec :
    FreeSpec({
        "constructor" - {
            "with `Double` coordinates" - {
                "returns the corresponding Point3d using `Long` coordinates" {
                    Point3d(1.0, 2.0, 3.0) shouldBe Point3d(1L, 2L, 3L)
                }
            }
        }

        "toString" - {
            "returns pretty formatted String representation of the point" {
                Point3d(1024, 2048, 4096).toString() shouldBe "(1024, 2048, 4096)"
            }
        }

        "operator plus" - {
            "other Point3d" - {
                "adds two Point3ds correctly together by adding each of their coordinates together" {
                    Point3d(1024, 2048, 4096) + Point3d(1024, 2048, 4096) shouldBe Point3d(2048, 4096, 8192)
                }
            }

            "constant Number offset" - {
                "shifts both coordinates of the point by the offset provided" {
                    Point3d(1, 2, 3) + 4 shouldBe Point3d(5, 6, 7)
                }
            }
        }

        "operator minus" - {
            "other Point3d" - {
                "adds two Point3ds correctly together by adding each of their coordinates together" {
                    Point3d(1024, 2048, 4096) - Point3d(1024, 2048, 4096) shouldBe Point3d(0, 0, 0)
                }
            }
        }

        "euclideanDistanceTo" - {
            "returns the Euclidean distance between two points" {
                Point3d(0, 0, 0) euclideanDistanceTo Point3d(1, 2, 3) shouldBe sqrt((1 + 4 + 9).toDouble())
            }
        }
    })
