package com.evnmo.msgpack

import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

internal data class MessagePackConf(
    @JvmField val encodeEnumsAsStrings: Boolean = false,
    @JvmField val useDebugLogging: Boolean = true,
    @JvmField val serializersModule: SerializersModule = EmptySerializersModule
)