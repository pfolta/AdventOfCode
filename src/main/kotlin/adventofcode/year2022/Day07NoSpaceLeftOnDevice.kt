package adventofcode.year2022

import adventofcode.Puzzle
import adventofcode.PuzzleInput

class Day07NoSpaceLeftOnDevice(customInput: PuzzleInput? = null) : Puzzle(customInput) {
    private val files by lazy {
        input.lines().fold(emptyList<String>() to emptySet<File>()) { (currentPath, files), line ->
            val (size, name) = line.split(" ")

            when {
                line == "$ cd .." -> currentPath.dropLast(1) to files
                line.startsWith("$ cd ") -> currentPath + listOf(line.split(" ").last()) to files
                line.startsWith("dir") -> currentPath to files + File(name, currentPath.joinToString("/"), 0)
                line.first().isDigit() -> currentPath to files + File(name, currentPath.joinToString("/"), size.toInt())
                else -> currentPath to files
            }
        }
            .second
    }

    private val directories by lazy {
        files
            .map(File::path)
            .toSet()
            .map { directory -> directory to files.filter { file -> file.path.startsWith(directory) }.sumOf { file -> file.size } }
    }

    override fun partOne() = directories
        .filter { (_, size) -> size <= 100000 }
        .sumOf { (_, size) -> size }

    override fun partTwo() = directories
        .filter { (_, size) -> size >= REQUIRED_SPACE - (TOTAL_DISK_SPACE - directories.maxOf { (_, size) -> size }) }
        .minBy { (_, size) -> size }
        .second

    companion object {
        private const val TOTAL_DISK_SPACE = 70000000
        private const val REQUIRED_SPACE = 30000000

        private data class File(
            val name: String,
            val path: String,
            val size: Int
        )
    }
}
