package com.evnmo.msgpack.converter

import com.evnmo.msgpack.serializer.MessagePack
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.serializer
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

@ExperimentalSerializationApi
public class MessagePackConverterFactory : Converter.Factory() {

    private val messagePack = MessagePack.Default

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val strategy = messagePack.serializersModule.serializer(type)
        return MsgPackResponseBodyConverter(messagePack, strategy)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        val strategy = messagePack.serializersModule.serializer(type)
        return MsgPackRequestBodyConverter(messagePack, strategy)
    }
}
