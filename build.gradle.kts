import org.gradle.api.file.DuplicatesStrategy.EXCLUDE
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML

plugins {
    application
    jacoco

    kotlin("jvm") version "1.9.20"

    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("com.adarshr.test-logger") version "4.0.0"
}

application {
    mainClass.set("adventofcode.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("com.diogonunes:JColor:5.5.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.3")
    implementation("org.reflections:reflections:0.10.2")
    implementation("org.slf4j:slf4j-nop:2.0.9")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.7.2")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.7.2")
}

ktlint {
    reporters {
        reporter(HTML)
    }
}

testlogger {
    // 15 seconds, see adventofcode.util.ConsoleFormatterKt.formatBenchmark
    slowThreshold = 15_000
}

tasks {
    withType<Wrapper> {
        gradleVersion = "8.6"
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_21.toString()
    }

    withType<Test> {
        useJUnitPlatform()

        finalizedBy("jacocoTestReport")
    }

    withType<JacocoReport> {
        reports {
            xml.required.set(true)
        }
    }

    withType<Jar> {
        manifest.attributes["Main-Class"] = application.mainClass

        // Include runtime dependencies (create a fat jar)
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        duplicatesStrategy = EXCLUDE
    }
}
