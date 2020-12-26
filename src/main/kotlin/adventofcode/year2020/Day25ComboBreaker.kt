package adventofcode.year2020

import adventofcode.Puzzle

object Day25ComboBreaker : Puzzle() {
    private const val SUBJECT_NUMBER = 7L
    private const val MODULUS = 20201227

    private val cardPublicKey = input.lines().first().toLong()
    private val doorPublicKey = input.lines().last().toLong()

    private fun Long.transform(subjectNumber: Long) = (this * subjectNumber) % MODULUS

    private fun Long.computeLoopSize() =
        generateSequence(0 to 1L) { (loopSize, target) -> loopSize + 1 to target.transform(SUBJECT_NUMBER) }
            .first { it.second == this }
            .first

    override fun partOne() = (1..cardPublicKey.computeLoopSize()).fold(1L) { encryptionKey, _ -> encryptionKey.transform(doorPublicKey) }
}
