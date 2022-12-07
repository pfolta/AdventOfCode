package adventofcode.year2022

import adventofcode.Puzzle

class Day07NoSpaceLeftOnDevice(customInput: String? = null) : Puzzle(customInput) {
    private val files by lazy {
        generateSequence(Triple(0, emptyList<String>(), emptySet<File>())) { (inputIndex, path, files) ->
            input.lines().getOrNull(inputIndex)?.let { line ->
                val nextIndex = inputIndex + 1
                val (size, name) = line.split(" ")

                when {
                    line == "$ cd .." -> Triple(nextIndex, path.dropLast(1), files)
                    line.startsWith("$ cd ") -> Triple(nextIndex, path + listOf(line.split(" ").last()), files)
                    line.startsWith("dir") -> Triple(nextIndex, path, files + File(name, path.joinToString("/"), 0))
                    line.first().isDigit() -> Triple(nextIndex, path, files + File(name, path.joinToString("/"), size.toInt()))
                    else -> Triple(nextIndex, path, files)
                }
            }
        }
            .map { (_, _, files) -> files }
            .last()
    }

    private val directorySizes by lazy {
        files
            .map(File::parent)
            .toSet()
            .map { directory -> files.filter { file -> file.parent.startsWith(directory) }.sumOf { file -> file.size } }
    }

    override fun partOne() = directorySizes
        .filter { size -> size <= 100000 }
        .sum()

    override fun partTwo() = directorySizes
        .filter { size -> size >= REQUIRED_SPACE - (TOTAL_DISK_SPACE - directorySizes.max()) }
        .min()

    companion object {
        private const val TOTAL_DISK_SPACE = 70000000
        private const val REQUIRED_SPACE = 30000000

        private data class File(
            val name: String,
            val parent: String,
            val size: Int
        )
    }
}
