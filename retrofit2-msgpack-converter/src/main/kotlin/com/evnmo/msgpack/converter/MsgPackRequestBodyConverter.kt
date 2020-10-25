package com.evnmo.msgpack.converter

import com.evnmo.msgpack.serializer.MessagePack
import kotlinx.serialization.SerializationStrategy
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Converter


internal class MsgPackRequestBodyConverter<T>(
    private val messagePack: MessagePack,
    private val serializer: SerializationStrategy<T>
) : Converter<T, RequestBody> {
    private val mediaType = "application/x-msgpack".toMediaType()

    override fun convert(value: T): RequestBody? {
        val bytes = messagePack.encodeToByteArray(serializer, value)
        return bytes.toRequestBody(mediaType)
    }
}