# 🎄 [Advent of Code](https://adventofcode.com)

## How to run

Puzzles can be run using the Gradle `run` task or by executing the project's JAR file:

* All available puzzles (all years and days):  
  `./gradlew run` or `java -jar build/libs/AdventOfCode.jar`
* All puzzles from a given year:  
  `./gradlew run --args "2020"` or `java -jar build/libs/AdventOfCode.jar 2020`
* A single puzzle for a given year and day:  
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

### 2015

| Day | Puzzle                                 | Links                                                                                                                                                                                                                                | ⭐️ Part 1  | ⭐️ Part 2  |
|-----|----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|------------|
| 1   | Not Quite Lisp                         | [[AoC](https://adventofcode.com/2015/day/1)] [[Code](src/main/kotlin/adventofcode/year2015/Day01NotQuiteLisp.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day01NotQuiteLispSpec.kt)]                                           | `232`      | `1783`     |
| 2   | I Was Told There Would Be No Math      | [[AoC](https://adventofcode.com/2015/day/2)] [[Code](src/main/kotlin/adventofcode/year2015/Day02IWasToldThereWouldBeNoMath.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day02IWasToldThereWouldBeNoMathSpec.kt)]               | `1598415`  | `3812909`  |
| 3   | Perfectly Spherical Houses in a Vacuum | [[AoC](https://adventofcode.com/2015/day/3)] [[Code](src/main/kotlin/adventofcode/year2015/Day03PerfectlySphericalHousesInAVacuum.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day03PerfectlySphericalHousesInAVacuumSpec.kt)] | `2565`     | `2639`     |
| 4   | The Ideal Stocking Stuffer             | [[AoC](https://adventofcode.com/2015/day/4)] [[Code](src/main/kotlin/adventofcode/year2015/Day04TheIdealStockingStuffer.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day04TheIdealStockingStufferSpec.kt)]                     | `282749`   | `9962624`  |
| 5   | Doesn't He Have Intern-Elves For This? | [[AoC](https://adventofcode.com/2015/day/5)] [[Code](src/main/kotlin/adventofcode/year2015/Day05DoesntHeHaveInternElvesForThis.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day05DoesntHeHaveInternElvesForThisSpec.kt)]       | `238`      | `69`       |
| 6   | Probably a Fire Hazard                 | [[AoC](https://adventofcode.com/2015/day/6)] [[Code](src/main/kotlin/adventofcode/year2015/Day06ProbablyAFireHazard.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day06ProbablyAFireHazardSpec.kt)]                             | `377891`   | `14110788` |
| 8   | Matchsticks                            | [[AoC](https://adventofcode.com/2015/day/8)] [[Code](src/main/kotlin/adventofcode/year2015/Day08Matchsticks.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day08MatchsticksSpec.kt)]                                             | `1333`     | `2046`     |
| 9   | All in a Single Night                  | [[AoC](https://adventofcode.com/2015/day/9)] [[Code](src/main/kotlin/adventofcode/year2015/Day09AllInASingleNight.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day09AllInASingleNightSpec.kt)]                                 | `207`      | `804`      |
| 10  | Elves Look, Elves Say                  | [[AoC](https://adventofcode.com/2015/day/10)] [[Code](src/main/kotlin/adventofcode/year2015/Day10ElvesLookElvesSay.kt)]                                                                                                              | `492982`   | `6989950`  |
| 11  | Corporate Policy                       | [[AoC](https://adventofcode.com/2015/day/11)] [[Code](src/main/kotlin/adventofcode/year2015/Day11CorporatePolicy.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day11CorporatePolicySpec.kt)]                                    | `vzbxxyzz` | `vzcaabcc` |
| 12  | JSAbacusFramework.io                   | [[AoC](https://adventofcode.com/2015/day/12)] [[Code](src/main/kotlin/adventofcode/year2015/Day12JSAbacusFrameworkIo.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day12JSAbacusFrameworkIoSpec.kt)]                            | `111754`   | `65402`    |
| 13  | Knights of the Dinner Table            | [[AoC](https://adventofcode.com/2015/day/13)] [[Code](src/main/kotlin/adventofcode/year2015/Day13KnightsOfTheDinnerTable.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day13KnightsOfTheDinnerTableSpec.kt)]                    | `618`      | `601`      |
| 14  | Reindeer Olympics                      | [[AoC](https://adventofcode.com/2015/day/14)] [[Code](src/main/kotlin/adventofcode/year2015/Day14ReindeerOlympics.kt)]                                                                                                               | `2696`     | `1084`     |
| 15  | Science for Hungry People              | [[AoC](https://adventofcode.com/2015/day/15)] [[Code](src/main/kotlin/adventofcode/year2015/Day15ScienceForHungryPeople.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day15ScienceForHungryPeopleSpec.kt)]                      | `21367368` | `1766400`  |
| 16  | Aunt Sue                               | [[AoC](https://adventofcode.com/2015/day/16)] [[Code](src/main/kotlin/adventofcode/year2015/Day16AuntSue.kt)]                                                                                                                        | `213`      | `323`      |
| 17  | No Such Thing as Too Much              | [[AoC](https://adventofcode.com/2015/day/17)] [[Code](src/main/kotlin/adventofcode/year2015/Day17NoSuchThingAsTooMuch.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day17NoSuchThingAsTooMuchSpec.kt)]                          | `1638`     | `17`       |
| 18  | Like a GIF For Your Yard               | [[AoC](https://adventofcode.com/2015/day/18)] [[Code](src/main/kotlin/adventofcode/year2015/Day18LikeAGifForYourYard.kt)]                                                                                                            | `768`      | `781`      |
| 20  | Infinite Elves and Infinite Houses     | [[AoC](https://adventofcode.com/2015/day/20)] [[Code](src/main/kotlin/adventofcode/year2015/Day20InfiniteElvesAndInfiniteHouses.kt)] [[Test](src/test/kotlin/adventofcode/year2015/Day20InfiniteElvesAndInfiniteHousesSpec.kt)]      | `665280`   | `705600`   |

### 2017

| Day | Puzzle                   | Links                                                                                                                                                                                                              | ⭐️ Part 1 | ⭐️ Part 2 |
|-----|--------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------|-----------|
| 1   | Inverse Captcha          | [[AoC](https://adventofcode.com/2017/day/1)] [[Solution](src/main/kotlin/adventofcode/year2017/Day01InverseCaptcha.kt)] [[Test](src/test/kotlin/adventofcode/year2017/Day01InverseCaptchaSpec.kt)]                 | `1047`    | `982`     |
| 2   | Corruption Checksum      | [[AoC](https://adventofcode.com/2017/day/2)] [[Solution](src/main/kotlin/adventofcode/year2017/Day02CorruptionChecksum.kt)] [[Test](src/test/kotlin/adventofcode/year2017/Day02CorruptionChecksumSpec.kt)]         | `36766`   | `261`     |
| 4   | High-Entropy Passphrases | [[AoC](https://adventofcode.com/2017/day/4)] [[Solution](src/main/kotlin/adventofcode/year2017/Day04HighEntropyPassphrases.kt)] [[Test](src/test/kotlin/adventofcode/year2017/Day04HighEntropyPassphrasesSpec.kt)] | `466`     | `251`     |

### 2018

| Day | Puzzle                      | Links                                                                                                                                                                                                                    | ⭐️ Part 1 | ⭐️ Part 2                   |
|-----|-----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------|-----------------------------|
| 1   | Chronal Calibration         | [[AoC](https://adventofcode.com/2018/day/1)] [[Solution](src/main/kotlin/adventofcode/year2018/Day01ChronalCalibration.kt)] [[Test](src/test/kotlin/adventofcode/year2018/Day01ChronalCalibrationSpec.kt)]               | `522`     | `73364`                     |
| 2   | Inventory Management System | [[AoC](https://adventofcode.com/2018/day/2)] [[Solution](src/main/kotlin/adventofcode/year2018/Day02InventoryManagementSystem.kt)] [[Test](src/test/kotlin/adventofcode/year2018/Day02InventoryManagementSystemSpec.kt)] | `4693`    | `pebjqsalrdnckzfihvtxysomg` |
| 3   | No Matter How You Slice It  | [[AoC](https://adventofcode.com/2018/day/3)] [[Solution](src/main/kotlin/adventofcode/year2018/Day03NoMatterHowYouSliceIt.kt)] [[Test](src/test/kotlin/adventofcode/year2018/Day03NoMatterHowYouSliceItSpec.kt)]         | `109143`  | `506`                       |

### 2019

| Day | Puzzle                             | Links                                                                                                                                  | ⭐️ Part 1 | ⭐️ Part 2 |
|-----|------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|-----------|-----------|
| 1   | The Tyranny of the Rocket Equation | [[AoC](https://adventofcode.com/2019/day/1)] [[Solution](src/main/kotlin/adventofcode/year2019/Day01TheTyrannyOfTheRocketEquation.kt)] | `3392373` | `5085699` |
| 2   | 1202 Program Alarm                 | [[AoC](https://adventofcode.com/2019/day/2)] [[Solution](src/main/kotlin/adventofcode/year2019/Day021202ProgramAlarm.kt)]              | `3716250` | `6472`    |

### 2020

| Day | Puzzle                  | Links                                                                                                                                                                                                               | ⭐️ Part 1        | ⭐️ Part 2                                        |
|-----|-------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------|--------------------------------------------------|
| 1   | Report Repair           | [[AoC](https://adventofcode.com/2020/day/1)] [[Solution](src/main/kotlin/adventofcode/year2020/Day01ReportRepair.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day01ReportRepairSpec.kt)]                      | `1005459`        | `92643264`                                       |
| 2   | Password Philosophy     | [[AoC](https://adventofcode.com/2020/day/2)] [[Solution](src/main/kotlin/adventofcode/year2020/Day02PasswordPhilosophy.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day02PasswordPhilosophySpec.kt)]          | `493`            | `593`                                            |
| 3   | Toboggan Trajectory     | [[AoC](https://adventofcode.com/2020/day/3)] [[Solution](src/main/kotlin/adventofcode/year2020/Day03TobogganTrajectory.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day03TobogganTrajectorySpec.kt)]          | `280`            | `4355551200`                                     |
| 4   | Passport Processing     | [[AoC](https://adventofcode.com/2020/day/4)] [[Solution](src/main/kotlin/adventofcode/year2020/Day04PassportProcessing.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day04PassportProcessingSpec.kt)]          | `216`            | `150`                                            |
| 5   | Binary Boarding         | [[AoC](https://adventofcode.com/2020/day/5)] [[Solution](src/main/kotlin/adventofcode/year2020/Day05BinaryBoarding.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day05BinaryBoardingSpec.kt)]                  | `842`            | `617`                                            |
| 6   | Custom Customs          | [[AoC](https://adventofcode.com/2020/day/6)] [[Solution](src/main/kotlin/adventofcode/year2020/Day06CustomCustoms.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day06CustomCustomsSpec.kt)]                    | `6530`           | `3323`                                           |
| 7   | Handy Haversacks        | [[AoC](https://adventofcode.com/2020/day/7)] [[Solution](src/main/kotlin/adventofcode/year2020/Day07HandyHaversacks.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day07HandyHaversacksSpec.kt)]                | `213`            | `38426`                                          |
| 8   | Handheld Halting        | [[AoC](https://adventofcode.com/2020/day/8)] [[Solution](src/main/kotlin/adventofcode/year2020/Day08HandheldHalting.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day08HandheldHaltingSpec.kt)]                | `1939`           | `2212`                                           |
| 9   | Encoding Error          | [[AoC](https://adventofcode.com/2020/day/9)] [[Solution](src/main/kotlin/adventofcode/year2020/Day09EncodingError.kt)]                                                                                              | `1492208709`     | `238243506`                                      |
| 10  | Adapter Array           | [[AoC](https://adventofcode.com/2020/day/10)] [[Solution](src/main/kotlin/adventofcode/year2020/Day10AdapterArray.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day10AdapterArraySpec.kt)]                     | `2590`           | `226775649501184`                                |
| 11  | Seating System          | [[AoC](https://adventofcode.com/2020/day/11)] [[Solution](src/main/kotlin/adventofcode/year2020/Day11SeatingSystem.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day11SeatingSystemSpec.kt)]                   | `2386`           | `2091`                                           |
| 12  | Rain Risk               | [[AoC](https://adventofcode.com/2020/day/12)] [[Solution](src/main/kotlin/adventofcode/year2020/Day12RainRisk.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day12RainRiskSpec.kt)]                             | `1533`           | `25235`                                          |
| 13  | Shuttle Search          | [[AoC](https://adventofcode.com/2020/day/13)] [[Solution](src/main/kotlin/adventofcode/year2020/Day13ShuttleSearch.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day13ShuttleSearchSpec.kt)]                   | `370`            | `894954360381385`                                |
| 14  | Docking Data            | [[AoC](https://adventofcode.com/2020/day/14)] [[Solution](src/main/kotlin/adventofcode/year2020/Day14DockingData.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day14DockingDataSpec.kt)]                       | `14862056079561` | `3296185383161`                                  |
| 15  | Rambunctious Recitation | [[AoC](https://adventofcode.com/2020/day/15)] [[Solution](src/main/kotlin/adventofcode/year2020/Day15RambunctiousRecitation.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day15RambunctiousRecitationSpec.kt)] | `206`            | `955`                                            |
| 16  | Ticket Translation      | [[AoC](https://adventofcode.com/2020/day/16)] [[Solution](src/main/kotlin/adventofcode/year2020/Day16TicketTranslation.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day16TicketTranslationSpec.kt)]           | `22000`          | `410460648673`                                   |
| 17  | Conway Cubes            | [[AoC](https://adventofcode.com/2020/day/17)] [[Solution](src/main/kotlin/adventofcode/year2020/Day17ConwayCubes.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day17ConwayCubesSpec.kt)]                       | `362`            | `1980`                                           |
| 19  | Monster Messages        | [[AoC](https://adventofcode.com/2020/day/19)] [[Solution](src/main/kotlin/adventofcode/year2020/Day19MonsterMessages.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day19MonsterMessagesSpec.kt)]               | `151`            |                                                  |
| 20  | Jurassic Jigsaw         | [[AoC](https://adventofcode.com/2020/day/20)] [[Solution](src/main/kotlin/adventofcode/year2020/Day20JurassicJigsaw.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day20JurassicJigsawSpec.kt)]                 | `68781323018729` |                                                  |
| 21  | Allergen Assessment     | [[AoC](https://adventofcode.com/2020/day/21)] [[Solution](src/main/kotlin/adventofcode/year2020/Day21AllergenAssessment.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day21AllergenAssessmentSpec.kt)]         | `2078`           | `lmcqt,kcddk,npxrdnd,cfb,ldkt,fqpt,jtfmtpd,tsch` |
| 22  | Crab Combat             | [[AoC](https://adventofcode.com/2020/day/22)] [[Solution](src/main/kotlin/adventofcode/year2020/Day22CrabCombat.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day22CrabCombatSpec.kt)]                         | `33434`          | `31657`                                          |
| 23  | Crab Cups               | [[AoC](https://adventofcode.com/2020/day/23)] [[Solution](src/main/kotlin/adventofcode/year2020/Day23CrabCups.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day23CrabCupsSpec.kt)]                             | `97632548`       |                                                  |
| 24  | Lobby Layout            | [[AoC](https://adventofcode.com/2020/day/24)] [[Solution](src/main/kotlin/adventofcode/year2020/Day24LobbyLayout.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day24LobbyLayoutSpec.kt)]                       | `269`            | `3667`                                           |
| 25  | Combo Breaker           | [[AoC](https://adventofcode.com/2020/day/25)] [[Solution](src/main/kotlin/adventofcode/year2020/Day25ComboBreaker.kt)] [[Test](src/test/kotlin/adventofcode/year2020/Day25ComboBreakerSpec.kt)]                     | `3286137`        |                                                  |

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

### 2022

| Day | Puzzle                  | Links                                                                                                                                                                                                              | ⭐️ Part 1 | ⭐️ Part 2 |
|-----|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------|-----------|
| 1   | Calorie Counting        | [[AoC](https://adventofcode.com/2022/day/1)] [[Solution](src/main/kotlin/adventofcode/year2022/Day01CalorieCounting.kt)] [[Test](src/test/kotlin/adventofcode/year2022/Day01CalorieCountingSpec.kt)]               | `72240`   | `210957`  |
| 2   | Rock Paper Scissors     | [[AoC](https://adventofcode.com/2022/day/2)] [[Solution](src/main/kotlin/adventofcode/year2022/Day02RockPaperScissors.kt)] [[Test](src/test/kotlin/adventofcode/year2022/Day02RockPaperScissorsSpec.kt)]           | `12458`   | `12683`   |
| 3   | Rucksack Reorganization | [[AoC](https://adventofcode.com/2022/day/3)] [[Solution](src/main/kotlin/adventofcode/year2022/Day03RucksackReorganization.kt)] [[Test](src/test/kotlin/adventofcode/year2022/Day03RucksackReorganizationSpec.kt)] | `7878`    | `2760`    |
