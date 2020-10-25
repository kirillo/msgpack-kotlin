package com.evnmo.msgpack.converter

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.serializer
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

@ExperimentalSerializationApi
public class MessagePackConverterFactory(
    private val serializationStrategy: Strategy = Strategy.AsMessagePack(),
    private val deserializationStrategy: Strategy = Strategy.AsMessagePack()
) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return when (deserializationStrategy) {
            is Strategy.AsJson -> {
                val serializer = serializer(type)
                JsonResponseBodyConverter(deserializationStrategy.json, serializer)
            }
            is Strategy.AsMessagePack -> {
                val serializer =
                    deserializationStrategy.messagePack.serializersModule.serializer(type)
                MsgPackResponseBodyConverter(deserializationStrategy.messagePack, serializer)
            }
        }
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return when (serializationStrategy) {
            is Strategy.AsJson -> {
                val serializer = serializer(type)
                JsonRequestBodyConverter(serializationStrategy.json, serializer)
            }
            is Strategy.AsMessagePack -> {
                val serializer =
                    serializationStrategy.messagePack.serializersModule.serializer(type)
                MsgPackRequestBodyConverter(serializationStrategy.messagePack, serializer)
            }
        }
    }
}
