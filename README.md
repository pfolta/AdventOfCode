# [Advent of Code](https://adventofcode.com)

## How to run

Puzzles can be run using the Gradle `run` task or by executing the project's JAR file:

* All available puzzles (all years and days):  
  `./gradlew run` or `java -jar build/libs/AdventOfCode.jar`
* All puzzles from a given year:  
  `./gradlew run --args "2020"` or `java -jar build/libs/AdventOfCode.jar 2020`
* A single puzzle for a given year/day:  
  `./gradlew run --args "2020/25"` or `java -jar build/libs/AdventOfCode.jar 2020/25`

These arguments can be combined to run multiple puzzles, e.g.
`./gradlew run --args "2015/1 2019 2020/6 2020/7"` will run the first puzzle from 2015, then all puzzles from 2019 and lastly the 6th and 7th puzzle from 2020.

## Structure

***Legend:** `XXXX`: Year (4 digits, e.g. `2020`) &bullet; `YY`: Day (leading zero, e.g. `07`) &bullet; `ZZZZ`: Puzzle name (UpperCamelCase, e.g. `HandyHaversacks`)*

* Inputs go into `src/main/resources/inputs` and follow the naming convention `yearXXXX/dayYY.txt`
* Solutions go into `src/main/kotlin/adventofcode` and follow the naming convention `yearXXXX/DayYYZZZZ.kt`
* Solutions extend the `Puzzle` class and call its constructor passing an optional alternative input
* Solutions can have one or two parts sharing the same input. Not overriding `fun partTwo(): Any` will skip the second part when running the puzzle

## Index

### 2022

| Day | Puzzle                  | Links                                                                                                                                                                                                              | ⭐️ Part 1 | ⭐️ Part 2 |
|-----|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------|-----------|
| 1   | Calorie Counting        | [[AoC](https://adventofcode.com/2022/day/1)] [[Solution](src/main/kotlin/adventofcode/year2022/Day01CalorieCounting.kt)] [[Test](src/test/kotlin/adventofcode/year2022/Day01CalorieCountingSpec.kt)]               | `72240`   | `210957`  |
| 2   | Rock Paper Scissors     | [[AoC](https://adventofcode.com/2022/day/2)] [[Solution](src/main/kotlin/adventofcode/year2022/Day02RockPaperScissors.kt)] [[Test](src/test/kotlin/adventofcode/year2022/Day02RockPaperScissorsSpec.kt)]           | `12458`   | `12683`   |
| 3   | Rucksack Reorganization | [[AoC](https://adventofcode.com/2022/day/3)] [[Solution](src/main/kotlin/adventofcode/year2022/Day03RucksackReorganization.kt)] [[Test](src/test/kotlin/adventofcode/year2022/Day03RucksackReorganizationSpec.kt)] | `7878`    | `2760`    |

### 2021

| Day | Puzzle                  | Links                                                                                                                                                                                                               | ⭐️ Part 1 | ⭐️ Part 2       |
|-----|-------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------|-----------------|
| 1   | Sonar Sweep             | [[AoC](https://adventofcode.com/2021/day/1)] [[Solution](src/main/kotlin/adventofcode/year2021/Day01SonarSweep.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day01SonarSweepSpec.kt)]                          | `1696`    | `1737`          |
| 2   | Dive!                   | [[AoC](https://adventofcode.com/2021/day/2)] [[Solution](src/main/kotlin/adventofcode/year2021/Day02Dive.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day02DiveSpec.kt)]                                      | `1762050` | `1855892637`    |
| 3   | Binary Diagnostic       | [[AoC](https://adventofcode.com/2021/day/3)] [[Solution](src/main/kotlin/adventofcode/year2021/Day03BinaryDiagnostic.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day03BinaryDiagnosticSpec.kt)]              | `2967914` | `7041258`       |
| 4   | Giant Squid             | [[AoC](https://adventofcode.com/2021/day/4)] [[Solution](src/main/kotlin/adventofcode/year2021/Day04GiantSquid.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day04GiantSquidSpec.kt)]                          | `38913`   | `16836`         |
| 5   | Hydrothermal Venture    | [[AoC](https://adventofcode.com/2021/day/5)] [[Solution](src/main/kotlin/adventofcode/year2021/Day05HydrothermalVenture.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day05HydrothermalVentureSpec.kt)]        | `3990`    | `21305`         |
| 6   | Lanternfish             | [[AoC](https://adventofcode.com/2021/day/6)] [[Solution](src/main/kotlin/adventofcode/year2021/Day06Lanternfish.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day06LanternfishSpec.kt)]                        | `359999`  | `1631647919273` |
| 7   | The Treachery of Whales | [[AoC](https://adventofcode.com/2021/day/7)] [[Solution](src/main/kotlin/adventofcode/year2021/Day07TheTreacheryOfWhales.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day07TheTreacheryOfWhalesSpec.kt)]      | `328187`  | `91257582`      |
| 8   | Seven Segment Search    | [[AoC](https://adventofcode.com/2021/day/8)] [[Solution](src/main/kotlin/adventofcode/year2021/Day08SevenSegmentSearch.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day08SevenSegmentSearchSpec.kt)]          | `288`     | `940724`        |
| 9   | Smoke Basin             | [[AoC](https://adventofcode.com/2021/day/9)] [[Solution](src/main/kotlin/adventofcode/year2021/Day09SmokeBasin.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day09SmokeBasinSpec.kt)]                          | `544`     | `1017792`       |
| 10  | Syntax Scoring          | [[AoC](https://adventofcode.com/2021/day/10)] [[Solution](src/main/kotlin/adventofcode/year2021/Day10SyntaxScoring.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day10SyntaxScoringSpec.kt)]                   | `319233`  | `1118976874`    |
| 11  | Dumbo Octopus           | [[AoC](https://adventofcode.com/2021/day/11)] [[Solution](src/main/kotlin/adventofcode/year2021/Day11DumboOctopus.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day11DumboOctopusSpec.kt)]                     | `1640`    | `312`           |
| 13  | Transparent Origami     | [[AoC](https://adventofcode.com/2021/day/13)] [[Solution](src/main/kotlin/adventofcode/year2021/Day13TransparentOrigami.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day13TransparentOrigamiSpec.kt)]         | `671`     | `PCPHARKL`      |
| 14  | Extended Polymerization | [[AoC](https://adventofcode.com/2021/day/14)] [[Solution](src/main/kotlin/adventofcode/year2021/Day14ExtendedPolymerization.kt)] [[Test](src/test/kotlin/adventofcode/year2021/Day14ExtendedPolymerizationSpec.kt)] | `2584`    | `3816397135460` |
