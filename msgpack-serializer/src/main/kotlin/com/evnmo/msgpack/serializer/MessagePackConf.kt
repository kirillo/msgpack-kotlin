package com.evnmo.msgpack.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.serializersModuleOf
import java.util.*

@ExperimentalSerializationApi
public data class MessagePackConf(
    @JvmField val encodeEnumsAsStrings: Boolean = false,
    @JvmField val useDebugLogging: Boolean = false,
    @JvmField val serializersModule: SerializersModule = getSerializersModule()
)

private fun getSerializersModule(): SerializersModule {
    return serializersModuleOf(Date::class, DateSerializer)
}

@Serializer(forClass = Date::class)
internal object DateSerializer : KSerializer<Date> {

    const val DATE_LENGTH = 4

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Date")

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, DateSerializer, value)
        }
    }

    override fun deserialize(decoder: Decoder): Date {
        return decoder.decodeStructure(descriptor) {
            decodeSerializableElement(descriptor, 0, DateSerializer)
        }
    }
}