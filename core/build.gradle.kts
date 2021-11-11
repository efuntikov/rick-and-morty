import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {

    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

kotlin {

    apply(from = "../versions.gradle.kts")
    val kotlinVersion: String by extra
    val serializationVersion: String by extra
    val ktorVersion: String by extra
    val coroutinesVersion: String by extra
    val reactiveVersion: String by extra

    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") {
        binaries {
            framework("Core") {
                freeCompilerArgs = arrayListOf("-Xobjc-generics")
            }
        }
    }

    cocoapods {
        summary = "Some description for the Core Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "core"
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")

//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutinesVersion")

                // HTTP
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")

                // Reactive
                implementation("com.badoo.reaktive:reaktive:$reactiveVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation ("org.jetbrains.kotlin:kotlin-stdlib")

                // Coroutines
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

                // HTTP
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
                implementation("io.ktor:ktor-client-json-jvm:$ktorVersion")
                implementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization-jvm:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
//                implementation ("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serializationVersion")
                // HTTP
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
//                implementation("io.ktor:ktor-client-core-native:1.3.1")
//                implementation("io.ktor:ktor-client-json-native:$ktorVersion")
//                implementation("io.ktor:ktor-client-logging-native:$ktorVersion")
//                implementation("io.ktor:ktor-client-serialization-native:$ktorVersion")
            }
        }
        val iosTest by getting
    }
}

//tasks.
//
//// Xcode-specific
//task packForXCode(val type: Sync) {
//    val frameworkDir: File = new File(buildDir, "xcode-frameworks")
//    final String mode = project.findProperty("XCODE_CONFIGURATION")?.toUpperCase() ?: 'DEBUG'
//    final def framework = kotlin.targets.iOS.binaries.getFramework("Core", mode)
//
//    inputs.property "mode", mode
//    dependsOn framework.linkTask
//
//            from { framework.outputFile.parentFile }
//    into frameworkDir
//
//            doLast {
//                new File(frameworkDir, 'gradlew').with {
//                text = "#!/bin/bash\nexport 'JAVA_HOME=${System.getProperty("java.home")}'\ncd '${rootProject.rootDir}'\n./gradlew \$@\n"
//                setExecutable(true)
//            }
//            }
//}
//tasks.build.dependsOn(packForXCode)

android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "30.0.3"
}