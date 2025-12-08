package adventofcode.util

/** Returns `true` if code is executed as part of the Gradle `test` task */
fun runningInTest(): Boolean = System.getProperties().stringPropertyNames().contains("org.gradle.test.worker")
