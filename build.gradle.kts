// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin("jvm") version "2.0.0" apply false
}

buildscript {
    val kotlinVersion by rootProject.extra { "2.0.0" }
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.4")
        classpath(kotlin("gradle-plugin", version = kotlinVersion))


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
