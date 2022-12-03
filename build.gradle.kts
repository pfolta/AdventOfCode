import org.gradle.api.file.DuplicatesStrategy.EXCLUDE
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.7.21"
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
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0")
    implementation("org.reflections:reflections:0.10.2")
    implementation("org.slf4j:slf4j-nop:2.0.5")

    testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<Test> {
        useJUnitPlatform()
    }

    withType<Jar> {
        manifest.attributes["Main-Class"] = application.mainClass

        // Include runtime dependencies (create a fat jar)
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        duplicatesStrategy = EXCLUDE
    }
}
