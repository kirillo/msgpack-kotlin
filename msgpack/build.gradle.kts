plugins {
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
    implementation(Libs.Kotlin.serialization)
    implementation(Libs.MessagePack.core)

    testImplementation(Libs.MessagePack.jackson)
    testImplementation(Libs.MessagePack.kotlin)
    testImplementation(Libs.Test.jUnit)
}
