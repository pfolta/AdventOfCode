package adventofcode.year2020

import adventofcode.Puzzle

class Day04PassportProcessing(customInput: String? = null) : Puzzle(customInput) {
    private val passports by lazy { input.split("\n\n").map { it.replace("\n", " ").split(" ") }.map(::Passport) }

    override fun partOne() = passports
        .count { it.byr != null && it.iyr != null && it.eyr != null && it.hgt != null && it.hcl != null && it.ecl != null && it.pid != null }

    override fun partTwo() = passports
        .asSequence()
        .filter { it.byr != null && it.iyr != null && it.eyr != null && it.hgt != null && it.hcl != null && it.ecl != null && it.pid != null }
        .filter { it.byr!!.toInt() in 1920..2002 }
        .filter { it.iyr!!.toInt() in 2010..2020 }
        .filter { it.eyr!!.toInt() in 2020..2030 }
        .filter {
            (it.hgt!!.endsWith("cm") && it.hgt.replace("cm", "").toInt() in 150..193) ||
                    (it.hgt.endsWith("in") && it.hgt.replace("in", "").toInt() in 59..76)
        }
        .filter { """#([0-9a-f]{6})""".toRegex().matches(it.hcl!!) }
        .filter { listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").any { color -> it.ecl!! == color } }
        .filter { it.pid!!.toIntOrNull() != null && it.pid.length == 9 }
        .count()

    companion object {
        data class Passport(
            val byr: String?,
            val iyr: String?,
            val eyr: String?,
            val hgt: String?,
            val hcl: String?,
            val ecl: String?,
            val pid: String?,
            val cid: String?
        ) {
            constructor(fields: List<String>) : this(
                fields.find { it.startsWith("byr") }?.split(":")?.get(1),
                fields.find { it.startsWith("iyr") }?.split(":")?.get(1),
                fields.find { it.startsWith("eyr") }?.split(":")?.get(1),
                fields.find { it.startsWith("hgt") }?.split(":")?.get(1),
                fields.find { it.startsWith("hcl") }?.split(":")?.get(1),
                fields.find { it.startsWith("ecl") }?.split(":")?.get(1),
                fields.find { it.startsWith("pid") }?.split(":")?.get(1),
                fields.find { it.startsWith("cid") }?.split(":")?.get(1)
            )
        }
    }
}
