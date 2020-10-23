plugins {
    id("maven")
    id("maven-publish")
    id("kotlin")
    kotlin("plugin.serialization") version Versions.kotlin
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    explicitApi()
}

dependencies {
    api(Libs.Kotlin.serialization_core)

    implementation(Libs.MessagePack.core)

    testImplementation(Libs.MessagePack.jackson)
    testImplementation(Libs.MessagePack.jackson_kotlin_module)
    testImplementation(Libs.Test.jUnit)
}

group = "com.evnmo"
version = 0.1
