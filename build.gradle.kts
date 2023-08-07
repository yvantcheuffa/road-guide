// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Build.spotlessGradlePlugin)
    }
}

allprojects {
    apply<com.diffplug.gradle.spotless.SpotlessPlugin>()
    repositories {
        google()
        mavenCentral()
    }
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    ratchetFrom = "origin/main"
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/**/*.kt", "**/buildSrc/**")
        ktlint("0.48.2")
    }
    format("kts") {
        target("**/*.kts")
        targetExclude("**/build/**/*.kts")
    }
}