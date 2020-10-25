package com.evnmo.msgpack.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
public data class MessagePackConf(
    @JvmField val encodeEnumsAsStrings: Boolean = false,
    @JvmField val useDebugLogging: Boolean = false,
    @JvmField val serializersModule: SerializersModule = EmptySerializersModule
)