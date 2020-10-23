package com.evnmo.msgpack.converter

import com.evnmo.msgpack.serializer.MessagePack
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

@ExperimentalSerializationApi
public class MessagePackConverterFactory(private val strategy: Strategy) : Converter.Factory() {

    private val messagePack = MessagePack.Default
    private val json = Json {
        encodeDefaults = true
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return when (strategy) {
            Strategy.Json -> {
                val serializer = serializer(type)
                JsonResponseBodyConverter(json, serializer)
            }
            Strategy.MessagePack -> {
                val strategy = messagePack.serializersModule.serializer(type)
                MsgPackResponseBodyConverter(messagePack, strategy)
            }
        }
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return when (strategy) {
            Strategy.Json -> {
                val saver = serializer(type)
                JsonRequestBodyConverter(json, saver)
            }
            Strategy.MessagePack -> {
                val strategy = messagePack.serializersModule.serializer(type)
                MsgPackRequestBodyConverter(messagePack, strategy)
            }
        }
    }
}
