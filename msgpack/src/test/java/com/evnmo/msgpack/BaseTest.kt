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

    private val jackson = ObjectMapper(MessagePackFactory())
        .enable(SerializationFeature.WRITE_ENUMS_USING_INDEX)
        .registerKotlinModule()
    private val messagePack = MessagePack.Default

    protected fun <T> runTest(testData: T, clazz: KClass<*>) {
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