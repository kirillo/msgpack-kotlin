package com.evnmo.msgpack.converter

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.Converter


internal class JsonResponseBodyConverter<T>(
    private val json: Json,
    private val serializer: DeserializationStrategy<T>
) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T {
        val string = value.string()
        return json.decodeFromString(serializer, string)
    }
}
