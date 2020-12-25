# [Advent of Code](https://adventofcode.com)

## How to run

Puzzles can be run using the Gradle `run` task or by executing the project's JAR file:

* All available puzzles (all years and days):
  * `./gradlew run` or `java -jar build/libs/AdventOfCode.jar`
* All puzzles from a given year:
  * `./gradlew run --args "2020"` or `java -jar build/libs/AdventOfCode.jar 2020`
* A single puzzle for a given year/day:
  * `./gradlew run --args "2020/25"` or `java -jar build/libs/AdventOfCode.jar 2020/25`

These arguments can be combined to run multiple puzzles, e.g.
`./gradlew run --args "2015/1 2019 2020/6 2020/7"` will run the first puzzle from 2015, then all puzzles from 2019 and lastly the 6th and 7th puzzle from 2020. 
