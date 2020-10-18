plugins {
    id("kotlin")
    kotlin("plugin.serialization") version "1.4.10"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    explicitApi()
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0")
    implementation("org.msgpack:msgpack-core:0.8.21")

    testImplementation("org.msgpack:jackson-dataformat-msgpack:0.8.21")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2")
    testImplementation("junit:junit:4.13.1")
}
