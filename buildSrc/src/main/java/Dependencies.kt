object Versions {
    const val kotlin = "1.4.10"
}

object Libs {
    object Evnmo {
        const val msgpack_serializer = "com.evnmo:msgpack-serializer:0.1"
    }

    object Kotlin {
        const val serialization_core = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0"
    }

    object MessagePack {
        private const val messagePack = "0.8.21"
        const val core = "org.msgpack:msgpack-core:${messagePack}"
        const val jackson = "org.msgpack:jackson-dataformat-msgpack:${messagePack}"
        const val jackson_kotlin_module =
            "com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val mockServer = "com.squareup.okhttp3:mockwebserver:3.14.9"
    }

    object Test {
        const val jUnit = "junit:junit:4.13.1"
    }
}