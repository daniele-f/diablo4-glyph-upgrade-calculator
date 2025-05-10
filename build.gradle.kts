import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType

plugins {
    kotlin("js") version "2.1.20"
}

group = "com.github.daniele_f"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js(KotlinJsCompilerType.IR) {
        browser {
            binaries.executable()
        }
    }
}
