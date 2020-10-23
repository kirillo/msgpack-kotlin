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
    implementation("msgpack-kotlin:msgpack-serializer:0.1")

    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Kotlin.serialization_core)

    testImplementation(Libs.Test.jUnit)
    testImplementation(Libs.Retrofit.mockServer)
}

version = 0.1
