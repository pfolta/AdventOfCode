import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.21"
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

    testImplementation("io.kotest:kotest-runner-junit5:4.3.2")
    testImplementation("io.kotest:kotest-assertions-core:4.3.2")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
