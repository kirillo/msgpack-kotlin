plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization") version "1.4.10"
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kotlin {
    explicitApi()
}

dependencies {
    implementation("androidx.core:core-ktx:1.3.2")

    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")
    implementation("org.msgpack:msgpack-core:0.8.21")

    testImplementation("org.msgpack:jackson-dataformat-msgpack:0.8.21")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2")
    testImplementation("junit:junit:4.13.1")
}