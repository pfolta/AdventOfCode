import org.gradle.api.file.DuplicatesStrategy.EXCLUDE
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML

plugins {
    application
    jacoco

    kotlin("jvm") version "1.8.22"

    id("org.jlleitschuh.gradle.ktlint") version "11.4.2"
    id("com.adarshr.test-logger") version "3.2.0"
}

application {
    mainClass.set("adventofcode.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.diogonunes:JColor:5.5.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    implementation("org.reflections:reflections:0.10.2")
    implementation("org.slf4j:slf4j-nop:2.0.7")

    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
    testImplementation("io.kotest:kotest-assertions-core:5.6.2")
}

ktlint {
    reporters {
        reporter(HTML)
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
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
