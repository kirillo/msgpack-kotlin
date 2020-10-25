package com.evnmo.msgpack.converter

import com.evnmo.msgpack.serializer.MessagePack
import kotlinx.serialization.DeserializationStrategy
import okhttp3.ResponseBody
import retrofit2.Converter


internal class MsgPackResponseBodyConverter<T>(
    private val messagePack: MessagePack,
    private val serializer: DeserializationStrategy<T>
) : Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody): T? =
        messagePack.decodeFromByteArray(serializer, value.bytes())
}