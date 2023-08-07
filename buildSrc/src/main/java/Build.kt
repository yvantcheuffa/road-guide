object Build {
    private const val androidBuildToolsVersion = "7.1.2"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.version}"

    private const val spotlessGradlePluginVersion = "6.20.0"
    const val spotlessGradlePlugin = "com.diffplug.spotless:com.diffplug.spotless.gradle.plugin:$spotlessGradlePluginVersion"
}