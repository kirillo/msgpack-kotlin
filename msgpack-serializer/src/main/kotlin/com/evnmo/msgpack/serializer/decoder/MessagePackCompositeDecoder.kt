package com.evnmo.msgpack.serializer.decoder

import com.evnmo.msgpack.serializer.DateSerializer
import com.evnmo.msgpack.serializer.Logger
import com.evnmo.msgpack.serializer.MessagePackConf
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import org.msgpack.core.MessageUnpacker
import java.nio.ByteBuffer
import java.util.*

@ExperimentalSerializationApi
internal class MessagePackCompositeDecoder(
    private val unpacker: MessageUnpacker,
    private val configuration: MessagePackConf
) : CompositeDecoder {

    private var elementIndex = 0
    private val logger = Logger(configuration)

    override val serializersModule = configuration.serializersModule

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        logger.log("decodeElementIndex")
        if (elementIndex == descriptor.elementsCount) return CompositeDecoder.DECODE_DONE
        return elementIndex++
    }

    override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
        return unpacker.unpackBoolean().apply {
            logger.log("decodeBooleanElement: $this")
        }
    }

    override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
        return unpacker.unpackByte().apply {
            logger.log("decodeByteElement: $this")
        }
    }

    override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
        return unpacker.unpackString()[0].apply {
            logger.log("decodeCharElement: $this")
        }
    }

    override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
        return unpacker.unpackDouble().apply {
            logger.log("decodeDoubleElement: $this")
        }
    }

    override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
        return unpacker.unpackFloat().apply {
            logger.log("decodeFloatElement: $this")
        }
    }

    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
        return unpacker.unpackInt().apply {
            logger.log("decodeIntElement: $this")
        }
    }

    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
        return unpacker.unpackLong().apply {
            logger.log("decodeLongElement: $this")
        }
    }

    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
        return unpacker.unpackShort().apply {
            logger.log("decodeShortElement: $this")
        }
    }

    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
        return unpacker.unpackString().apply {
            logger.log("decodeStringElement: $this")
        }
    }

    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>,
        previousValue: T?
    ): T? {
        return if (unpacker.tryUnpackNil()) {
            logger.log("decodeNullableSerializableElement: null")
            null
        } else {
            decodeSerializableElement(descriptor, index, deserializer, previousValue)
        }
    }

    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T {
        return if (descriptor.serialName == DateSerializer.descriptor.serialName) {
            logger.log("decodeDate")
            val byteArray = unpacker.readPayload(DateSerializer.DATE_LENGTH)
            val byteBuffer = ByteBuffer.wrap(byteArray)
            val unixSeconds = byteBuffer.int
            return Date(unixSeconds * 1000L) as T
        } else {
            deserializer.deserialize(MessagePackDecoder(unpacker, configuration))
        }
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        logger.log("endStructure")
    }

    override fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
        return when (descriptor.kind) {
            is StructureKind.LIST -> {
                if (descriptor.getElementDescriptor(0).kind == PrimitiveKind.BYTE) {
                    logger.log("decodeCollectionSize: binary")
                    unpacker.unpackBinaryHeader()
                } else {
                    logger.log("decodeCollectionSize: list")
                    unpacker.unpackArrayHeader()
                }
            }
            is StructureKind.MAP -> {
                logger.log("decodeCollectionSize: map")
                unpacker.unpackMapHeader()
            }
            else -> -1
        }
    }

    override fun decodeSequentially(): Boolean = true
}