package com.evnmo.msgpack.serializer.decoder

import com.evnmo.msgpack.serializer.DateSerializer
import com.evnmo.msgpack.serializer.Logger
import com.evnmo.msgpack.serializer.MessagePackConf
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.descriptors.elementNames
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

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        if (descriptor.kind == StructureKind.CLASS) {
            if (descriptor.serialName == DateSerializer.descriptor.serialName) {
                logger.log("beginStructure: date")
                unpacker.unpackExtensionTypeHeader()
            } else {
                logger.log("beginStructure: class")
                unpacker.unpackArrayHeader()
            }
        }
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
        return if (configuration.encodeEnumsAsStrings) {
            val enumAsString = unpacker.unpackString()
            enumDescriptor.elementNames.indexOf(enumAsString)
        } else {
            unpacker.unpackInt()
        }.apply {
            logger.log("decodeEnum: $this")
        }
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
        return !unpacker.tryUnpackNil().apply {
            logger.log("decodeNotNullMark: ${!this}")
        }
    }

    override fun decodeNull(): Nothing? {
        logger.log("decodeNull")
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