package adventofcode.common

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.String

object String {
    /**
     * Replaces the character of this `String` at the specified index with the replacement character.
     */
    fun String.replaceAt(
        index: Int,
        replacement: Char,
    ) = replaceRange(index..index, replacement.toString())

    /**
     * Removes the slice of given length at the specified index.
     */
    fun String.removeAt(
        index: Int,
        slice: Int = 1,
    ) = removeRange(index until index + slice)

    /**
     * Computes the MD5 hash of this `String` and returns the hash's hexadecimal `String` representation
     */
    fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16).padStart(32, '0')

    /**
     * Checks if this `String` only contains digits, i.e. is a positive number or 0
     */
    fun String.containsOnlyDigits() = all { char -> char.isDigit() }
}
