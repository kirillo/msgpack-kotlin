package com.evnmo.msgpack

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlinx.serialization.serializer
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.msgpack.jackson.dataformat.MessagePackFactory
import kotlin.reflect.KClass
import kotlin.reflect.full.createType

abstract class BaseTest {

    protected fun <T> runTest(testData: T, clazz: KClass<*>, config: MessagePackConf? = null) {

        val messagePack = if (config == null) {
            MessagePack.Default
        } else {
            MessagePack(config)
        }
        val jackson = ObjectMapper(MessagePackFactory()).registerKotlinModule()
        if (!messagePack.configuration.encodeEnumsAsStrings) {
            jackson.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX)
        }

        println("-------------")

        val actualEncoded =
            messagePack.encodeToByteArray(
                messagePack.serializersModule.serializer(clazz.createType()),
                testData
            )
        val expectedEncoded = jackson.writeValueAsBytes(testData)

        assertArrayEquals(expectedEncoded, actualEncoded)

        println("-------------")

        val actualDecoded = messagePack.decodeFromByteArray(
            messagePack.serializersModule.serializer(clazz.createType()),
            actualEncoded
        )
        val expectedDecoded = jackson.readValue(expectedEncoded, clazz.java)

        assertEquals(expectedDecoded, actualDecoded)
        assertEquals(testData, actualDecoded)
    }
}