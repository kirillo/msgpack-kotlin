package com.evnmo.msgpack.decoder

import com.evnmo.msgpack.Logger
import com.evnmo.msgpack.MessagePackConf
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import org.msgpack.core.MessageUnpacker

@ExperimentalSerializationApi
internal class MessagePackDecoder(
    private val unpacker: MessageUnpacker,
    private val configuration: MessagePackConf
) : Decoder {

    private val logger = Logger(configuration)

    override val serializersModule = configuration.serializersModule

    override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
        logger.log("-- decodeSerializableValue --")
        return super.decodeSerializableValue(deserializer)
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        logger.log("beginStructure")
        return MessagePackCompositeDecoder(unpacker, configuration)
    }

    override fun decodeBoolean(): Boolean {
        return unpacker.unpackBoolean().apply {
            logger.log("decodeBoolean: $this")
        }
    }

    override fun decodeByte(): Byte {
        return unpacker.unpackByte().apply {
            logger.log("decodeByte: $this")
        }
    }

    override fun decodeChar(): Char {
        return unpacker.unpackString()[0].apply {
            logger.log("decodeChar: $this")
        }
    }

    override fun decodeDouble(): Double {
        return unpacker.unpackDouble().apply {
            logger.log("decodeDouble: $this")
        }
    }

    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
        TODO("Not yet implemented")
    }

    override fun decodeFloat(): Float {
        return unpacker.unpackFloat().apply {
            logger.log("decodeFloat: $this")
        }
    }

    override fun decodeInt(): Int {
        return unpacker.unpackInt().apply {
            logger.log("decodeInt: $this")
        }
    }

    override fun decodeLong(): Long {
        return unpacker.unpackLong().apply {
            logger.log("decodeLong: $this")
        }
    }

    override fun decodeNotNullMark(): Boolean {
        return unpacker.tryUnpackNil().apply {
            logger.log("decodeNotNullMark: $this")
        }
    }

    override fun decodeNull(): Nothing? {
        logger.log("decodeNull")
        unpacker.unpackNil()
        return null
    }

    override fun decodeShort(): Short {
        return unpacker.unpackShort().apply {
            logger.log("decodeShort: $this")
        }
    }

    override fun decodeString(): String {
        return unpacker.unpackString().apply {
            logger.log("decodeString: $this")
        }
    }
}