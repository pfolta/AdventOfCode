package adventofcode.year2024

import adventofcode.PuzzleBaseSpec

class Day03MullItOverSpec : PuzzleBaseSpec(
    listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))" to 161),
    listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))" to 48),
)