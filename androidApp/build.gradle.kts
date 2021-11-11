plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
}

dependencies {
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    apply(from = "versions.gradle.kts")
    val navVersion: String by extra
    val glideVersion: String by extra

    implementation(project(":core"))
    implementation("com.badoo.reaktive:reaktive:1.2.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")

    // Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    annotationProcessor("com.github.bumptech.glide:compiler:$glideVersion")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "com.efuntikov.rickandmorty.android"
        minSdk = 26
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "30.0.3"
}