package com.evnmo.msgpack

import com.fasterxml.jackson.annotation.JsonFormat
import kotlinx.serialization.Serializable
import org.junit.Test


class TestEnumConfig : BaseTest() {

    private val original = Enums(
        enum1 = TestEnum.Enum2,
        enum2 = TestEnum.Enum1,
        enum3 = null
    )

    @Test
    fun testAsIndex() {
        runTest(original, Enums::class, MessagePackConf(encodeEnumsAsStrings = false))
    }

    @Test
    fun testAsString() {
        runTest(original, Enums::class, MessagePackConf(encodeEnumsAsStrings = true))
    }

    @Serializable
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private data class Enums(
        val enum1: TestEnum,
        val enum2: TestEnum?,
        val enum3: TestEnum?
    )

    private enum class TestEnum {
        Enum1, Enum2
    }
}