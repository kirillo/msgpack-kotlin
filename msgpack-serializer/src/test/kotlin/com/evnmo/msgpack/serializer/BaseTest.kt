package com.evnmo.msgpack.serializer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlinx.serialization.serializer
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.msgpack.jackson.dataformat.ExtensionTypeCustomDeserializers
import org.msgpack.jackson.dataformat.MessagePackFactory
import java.nio.ByteBuffer
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createType

internal abstract class BaseTest {

    protected fun <T> runTest(
        testData: T,
        clazz: KClass<*>,
        config: MessagePackConf? = null,
        compareWithJackson: Boolean = true
    ) {

        // init MessagePack
        val messagePack = MessagePack {
            config?.encodeEnumsAsStrings?.let {
                encodeEnumsAsStrings = it
            }

            useDebugLogging = true
        }

        // init Jackson
        val extTypeCustomDesers = ExtensionTypeCustomDeserializers().apply {
            addCustomDeser(-1) { byteArray ->
                val byteBuffer = ByteBuffer.wrap(byteArray)
                val unixSeconds = byteBuffer.int
                return@addCustomDeser Date((unixSeconds * 1000).toLong())
            }
        }

        val jackson =
            ObjectMapper(MessagePackFactory().setExtTypeCustomDesers(extTypeCustomDesers)).registerKotlinModule()
        if (!messagePack.configuration.encodeEnumsAsStrings) {
            jackson.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX)
        }

        // Run test
        println("-------------")

        val actualEncoded =
            messagePack.encodeToByteArray(
                messagePack.serializersModule.serializer(clazz.createType()),
                testData
            )

        println("-------------")

        val actualDecoded = messagePack.decodeFromByteArray(
            messagePack.serializersModule.serializer(clazz.createType()),
            actualEncoded
        )

        assertEquals(testData, actualDecoded)

        if (compareWithJackson) {
            val expectedEncoded = jackson.writeValueAsBytes(testData)
            assertArrayEquals(expectedEncoded, actualEncoded)

            val expectedDecoded = jackson.readValue(expectedEncoded, clazz.java)
            assertEquals(expectedDecoded, actualDecoded)
        }
    }
}