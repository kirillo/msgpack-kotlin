object Versions {
    const val kotlin = "1.4.10"
    const val serialization = "1.0.0"
    const val messagePack = "0.8.21"
}

object Libs {
    object Kotlin {
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.serialization}"
    }

    object MessagePack {
        const val core = "org.msgpack:msgpack-core:${Versions.messagePack}"
        const val jackson = "org.msgpack:jackson-dataformat-msgpack:${Versions.messagePack}"
        const val kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2"
    }

    object Test {
        const val jUnit = "junit:junit:4.13.1"
    }
}