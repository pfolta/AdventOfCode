package adventofcode.util

import com.diogonunes.jcolor.AnsiFormat
import com.diogonunes.jcolor.Attribute.BLUE_TEXT
import com.diogonunes.jcolor.Attribute.BOLD
import com.diogonunes.jcolor.Attribute.RED_BACK
import com.diogonunes.jcolor.Attribute.UNDERLINE
import com.diogonunes.jcolor.Attribute.WHITE_TEXT
import java.net.URI
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Every Advent of Code problem has a solution that completes in at most 15 seconds on ten-year-old hardware,
 * see https://adventofcode.com/about.
 *
 * Highlight runtimes that exceed 15 seconds in red.
 */
fun Duration.formatBenchmark(): String = when {
    this > 15.seconds -> AnsiFormat(RED_BACK(), WHITE_TEXT(), BOLD()).format(this.toString())
    else -> this.toString()
}

fun String.bold(): String = AnsiFormat(BOLD()).format(this)

fun URI.formatUri(): String = AnsiFormat(BLUE_TEXT(), UNDERLINE()).format(this.toString())
