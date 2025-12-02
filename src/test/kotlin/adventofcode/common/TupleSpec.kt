package adventofcode.common

import adventofcode.common.Tuple.minus
import adventofcode.common.Tuple.plus
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TupleSpec :
    FreeSpec({
        "Pair<Int, Int> + Pair<Int, Int>" - {
            "adds each component independently" {
                (1 to 0) + (2 to 1) shouldBe (3 to 1)
            }
        }

        "Pair<Int, Int> - Pair<Int, Int>" - {
            "subtracts each component independently" {
                (1 to 0) - (2 to 1) shouldBe (-1 to -1)
            }
        }
    })
