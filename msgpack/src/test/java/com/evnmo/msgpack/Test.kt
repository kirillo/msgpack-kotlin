package com.evnmo.msgpack

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlinx.serialization.encodeToByteArray
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import org.msgpack.jackson.dataformat.MessagePackFactory

class Test {

    @Test
    fun test() {
        val objectMapper = ObjectMapper(MessagePackFactory()).registerKotlinModule()
        val original = TestData(
            string = "someString",
            nullableString = null,
            int = 9,
            nullableInt = null,
            bool = true,
            nullableBool = null,
            long = 9L,
            nullableLong = null,
            double = 9.9,
            nullableDouble = null,
            float = 9.9f,
            nullableFloat = null,
            byte = 9,
            nullableByte = null,
            char = '9',
            nullableChar = null,
            short = 9,
            nullableShort = null,

            enum = Type.Enum1,
            nullableEnum = null,

            list = listOf("item1", "item2"),
            nullableList = null,
            map = mapOf("key1" to "value1", "key2" to "value2"),
            nullableMap = null,
//            byteArray = ByteArray(9),
//            nullableByteArray = null,

            innerClass = Info(
                enum = Type.Enum2,
                double = 9.9,
            )
        )

        val actualEncoded = MessagePack.Default.encodeToByteArray(original)
        val expectedEncoded = objectMapper.writeValueAsBytes(original)

        assertArrayEquals(actualEncoded, expectedEncoded)

//        val actualDecoded = MessagePack.Default.decodeFromByteArray<TestData>(actualEncoded)
        val expectedDecoded = objectMapper.readValue<TestData>(expectedEncoded)
//
//        assertEquals(actualDecoded, expectedDecoded)
        assertEquals(original, expectedDecoded)
    }
}