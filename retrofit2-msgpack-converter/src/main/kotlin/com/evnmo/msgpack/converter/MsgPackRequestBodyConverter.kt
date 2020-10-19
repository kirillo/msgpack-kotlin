package com.evnmo.msgpack.converter

import com.evnmo.msgpack.serializer.MessagePack
import kotlinx.serialization.SerializationStrategy
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Converter


internal class MsgPackRequestBodyConverter<T>(
    private val messagePack: MessagePack,
    private val strategy: SerializationStrategy<T>
) : Converter<T, RequestBody> {
    private val MEDIA_TYPE: MediaType = MediaType.get("application/x-msgpack")

    override fun convert(value: T): RequestBody? {
        val bytes = messagePack.encodeToByteArray(strategy, value)
        return RequestBody.create(MEDIA_TYPE, bytes)
    }
}