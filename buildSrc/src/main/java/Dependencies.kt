object Versions {
    const val kotlin = "1.4.10"
    const val serialization = "1.0.0"
    const val messagePack = "0.8.21"
    const val retrofit = "2.9.0"
    const val okhttp = "3.14.9"
    const val jUnit = "4.13.1"
}

object Libs {
    object Kotlin {
        const val serialization_core =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.serialization}"
    }

    object MessagePack {
        const val core = "org.msgpack:msgpack-core:${Versions.messagePack}"
        const val jackson = "org.msgpack:jackson-dataformat-msgpack:${Versions.messagePack}"
        const val kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val mockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.jUnit}"
    }
}