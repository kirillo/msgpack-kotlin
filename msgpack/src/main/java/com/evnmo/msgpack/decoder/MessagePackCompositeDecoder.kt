package com.evnmo.msgpack.decoder

import com.evnmo.msgpack.MessagePackConf
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import org.msgpack.core.MessageUnpacker

@ExperimentalSerializationApi
internal class MessagePackCompositeDecoder(
    private val unpacker: MessageUnpacker,
    private val configuration: MessagePackConf
) : CompositeDecoder {

    private var elementIndex = 0

    override val serializersModule = configuration.serializersModule

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        if (elementIndex == descriptor.elementsCount) return CompositeDecoder.DECODE_DONE
        return elementIndex++
    }

    override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
        return unpacker.unpackBoolean()
    }

    override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
        return unpacker.unpackByte()
    }

    override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
        return unpacker.unpackString()[0]
    }

    override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
        return unpacker.unpackDouble()
    }

    override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
        return unpacker.unpackFloat()
    }

    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
        return unpacker.unpackInt()
    }

    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
        return unpacker.unpackLong()
    }

    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
        return unpacker.unpackShort()
    }

    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): String {
        return unpacker.unpackString()
    }

    /**
     * Decodes nullable value of the type [T] with the given [deserializer].
     *
     * If value at given [index] was already decoded with previous [decodeSerializableElement] call with the same index,
     * [previousValue] would contain a previously decoded value.
     * This parameter can be used to aggregate multiple values of the given property to the only one.
     * Implementation can safely ignore it and return a new value, efficiently using 'the last one wins' strategy,
     * or apply format-specific aggregating strategies, e.g. appending scattered Protobuf lists to a single one.
     */
    @ExperimentalSerializationApi
    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>,
        previousValue: T?
    ): T? {
        TODO("Not yet implemented")
    }

    /**
     * Decodes value of the type [T] with the given [deserializer].
     *
     * Implementations of [CompositeDecoder] may use their format-specific deserializers
     * for particular data types, e.g. handle [ByteArray] specifically if format is binary.
     *
     * If value at given [index] was already decoded with previous [decodeSerializableElement] call with the same index,
     * [previousValue] would contain a previously decoded value.
     * This parameter can be used to aggregate multiple values of the given property to the only one.
     * Implementation can safely ignore it and return a new value, effectively using 'the last one wins' strategy,
     * or apply format-specific aggregating strategies, e.g. appending scattered Protobuf lists to a single one.
     */
    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T {
        TODO("Not yet implemented")
    }

    /**
     * Denotes the end of the structure associated with current decoder.
     * For example, composite decoder of JSON format will expect (and parse)
     * a closing bracket in the underlying input.
     */
    override fun endStructure(descriptor: SerialDescriptor) {
        TODO("Not yet implemented")
    }

//    override fun decodeSequentially(): Boolean = true
}