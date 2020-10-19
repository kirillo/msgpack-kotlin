package com.evnmo.msgpack.serializer

import com.fasterxml.jackson.annotation.JsonFormat
import kotlinx.serialization.Serializable
import org.junit.Test

internal class TestNestedClass : BaseTest() {

    @Test
    fun testNestedClass() {
        val original = NestedClass(
            innerClass = InnerClass(
                string = "someString",
                double = 9.9
            )
        )
        runTest(original, NestedClass::class)
    }

    @Test
    fun testNullableNestedClassWithoutNulls() {
        val original = NullableNestedClass(
            innerClass = InnerClass(
                string = "someString",
                double = 9.9
            )
        )
        runTest(original, NullableNestedClass::class)
    }

    @Test
    fun testNullableNestedClassWithNulls() {
        val original = NullableNestedClass(
            innerClass = null
        )
        runTest(original, NullableNestedClass::class)
    }

    @Serializable
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private data class NestedClass(
        val innerClass: InnerClass
    )

    @Serializable
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private data class NullableNestedClass(
        val innerClass: InnerClass?
    )

    @Serializable
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private data class InnerClass(
        val string: String,
        val double: Double
    )
}
