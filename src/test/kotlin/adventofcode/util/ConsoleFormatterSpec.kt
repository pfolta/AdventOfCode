package adventofcode.util

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class ConsoleFormatterSpec : FreeSpec({
    "Duration.formatBenchmark()" - {
        "formats Durations of less than 15 seconds as plain text" {
            5.seconds.formatBenchmark() shouldBe "5s"
        }

        "formats Durations of exactly 15 seconds as plain text" {
            15.seconds.formatBenchmark() shouldBe "15s"
        }

        "highlights Durations of more than 15s in red" {
            1.minutes.formatBenchmark() shouldBe "\u001B[41;37;1m1m\u001B[0m"
        }
    }
})
