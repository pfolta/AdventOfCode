package adventofcode.common

import java.math.BigInteger
import java.security.MessageDigest

/**
 * Replaces the character of the string at the specified index with the replacement character.
 */
fun String.replaceAt(index: Int, replacement: Char) = substring(0 until index) + replacement + substring(index + 1 until length)

/**
 * Removes the slice of given length at the specified index.
 */
fun String.removeAt(index: Int, slice: Int = 1) = removeRange(index until index + slice)

fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16).padStart(32, '0')
