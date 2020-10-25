package com.evnmo.msgpack.serializer

import com.evnmo.msgpack.serializer.decoder.MessagePackDecoder
import com.evnmo.msgpack.serializer.encoder.MessagePackEncoder
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
public open class MessagePack(public val configuration: MessagePackConf) : BinaryFormat {

    override val serializersModule: SerializersModule
        get() = configuration.serializersModule

    public companion object Default : MessagePack(MessagePackConf())

    override fun <T> decodeFromByteArray(
        deserializer: DeserializationStrategy<T>,
        bytes: ByteArray
    ): T {
        val unpacker = org.msgpack.core.MessagePack.newDefaultUnpacker(bytes)
        val decoder = MessagePackDecoder(unpacker, configuration)
        val result = decoder.decodeSerializableValue(deserializer)
        unpacker.close()

        return result
    }

    override fun <T> encodeToByteArray(
        serializer: SerializationStrategy<T>,
        value: T
    ): ByteArray {
        val packer = org.msgpack.core.MessagePack.newDefaultBufferPacker()
        val encoder = MessagePackEncoder(packer, configuration)
        encoder.encodeSerializableValue(serializer, value)
        packer.close()

        return packer.toByteArray()
    }
}

@Suppress("FunctionName")
public fun MessagePack(
    from: MessagePack = MessagePack.Default,
    builderAction: MessagePackBuilder.() -> Unit
): MessagePack {
    val builder = MessagePackBuilder(from.configuration)
    builder.builderAction()
    val conf = builder.build()
    return MessagePack(conf)
}

/**
 * Builder of the [MessagePack] instance provided by `MessagePack { ... }` factory function.
 */
@Suppress("MemberVisibilityCanBePrivate")
public class MessagePackBuilder internal constructor(conf: MessagePackConf) {
    /**
     * Encode the enums as Strings, otherwise encode them as an index
     */
    public var encodeEnumsAsStrings: Boolean = conf.encodeEnumsAsStrings

    /**
     * Write information about detailed object structure to the log to detect serialization errors
     */
    public var useDebugLogging: Boolean = conf.useDebugLogging

    /**
     * Module with contextual and polymorphic serializers to be used in the resulting [MessagePack] instance.
     */
    public var serializersModule: SerializersModule = conf.serializersModule

    internal fun build(): MessagePackConf {
        return MessagePackConf(
            encodeEnumsAsStrings = encodeEnumsAsStrings,
            useDebugLogging = useDebugLogging,
            serializersModule = serializersModule
        )
    }
}