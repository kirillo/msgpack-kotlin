plugins {
    id("maven")
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
    implementation(project(":msgpack-serializer"))
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Kotlin.serialization_core)

    testImplementation(Libs.Test.jUnit)
    testImplementation(Libs.Retrofit.mockServer)
}

