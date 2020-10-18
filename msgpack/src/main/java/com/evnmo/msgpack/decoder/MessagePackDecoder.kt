package com.evnmo.msgpack.decoder

import com.evnmo.msgpack.MessagePackConf
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

    override val serializersModule = configuration.serializersModule

    /**
     * Decodes the beginning of the nested structure in a serialized form
     * and returns [CompositeDecoder] responsible for decoding this very structure.
     *
     * Typically, classes, collections and maps are represented as a nested structure in a serialized form.
     * E.g. the following JSON
     * ```
     * {
     *     "a": 2,
     *     "b": { "nested": "c" }
     *     "c": [1, 2, 3],
     *     "d": null
     * }
     * ```
     * has three nested structures: the very beginning of the data, "b" value and "c" value.
     */
    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        return MessagePackCompositeDecoder(unpacker, configuration)
    }

    override fun decodeBoolean(): Boolean {
        return unpacker.unpackBoolean()
    }

    override fun decodeByte(): Byte {
        return unpacker.unpackByte()
    }

    override fun decodeChar(): Char {
        return unpacker.unpackString()[0]
    }

    override fun decodeDouble(): Double {
        return unpacker.unpackDouble()
    }

    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
        TODO("Not yet implemented")
    }

    override fun decodeFloat(): Float {
        return unpacker.unpackFloat()
    }

    override fun decodeInt(): Int {
        return unpacker.unpackInt()
    }

    override fun decodeLong(): Long {
        return unpacker.unpackLong()
    }

    override fun decodeNotNullMark(): Boolean {
        return unpacker.tryUnpackNil()
    }

    override fun decodeNull(): Nothing? {
        unpacker.unpackNil()
        return null
    }

    override fun decodeShort(): Short {
        return unpacker.unpackShort()
    }

    override fun decodeString(): String {
        return unpacker.unpackString()
    }
}