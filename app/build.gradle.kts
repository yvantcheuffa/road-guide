plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.diffplug.spotless")
}

android {
    namespace = "com.southafrica.roadguide"
    compileSdk = Version.compileSdkVersion
    buildToolsVersion = Version.buildToolsVersion

    defaultConfig {
        applicationId = "com.southafrica.roadguide"
        minSdk = Version.minSdkVersion
        targetSdk = Version.targetSdkVersion
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Annotation processors
    kapt(Hilt.hiltCompiler)

    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.datastorePreferences)
    implementation(AndroidX.ktx)
    implementation(AndroidX.process)
    implementation(AndroidX.runtime)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.splashScreen)
    implementation(AndroidX.navigationFragment)
    implementation(AndroidX.navigationUi)
    implementation(Hilt.hiltAndroid)
    implementation(Google.material)
    implementation(Google.gson)
    implementation(Coroutines.android)
    implementation(Coroutines.core)
    implementation(CircleImageView.circleImageView)

    // Testing dependencies
    testImplementation(Testing.junit)
    androidTestImplementation(Testing.junitExt)
    androidTestImplementation(Testing.espresso)
}