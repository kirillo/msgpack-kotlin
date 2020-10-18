package com.evnmo.msgpack

import com.fasterxml.jackson.annotation.JsonFormat
import kotlinx.serialization.Serializable
import org.junit.Test

class TestPrimitives : BaseTest() {

    @Test
    fun testPrimitives() {
        val original = Primitives(
            string = "some value",
            int = 9,
            bool = true,
            long = 9,
            double = 9.9,
            float = 9.9f,
            byte = 9,
            char = '9',
            short = 9,
            enum = TestEnum.Enum2
        )
        runTest(original, Primitives::class)
    }

    @Test
    fun testNullablePrimitivesWithoutNulls() {
        val original = NullablePrimitives(
            string = "some value",
            int = 9,
            bool = true,
            long = 9,
            double = 9.9,
            float = 9.9f,
            byte = 9,
            char = '9',
            short = 9,
            enum = TestEnum.Enum2
        )
        runTest(original, NullablePrimitives::class)
    }

    @Test
    fun testNullablePrimitivesWithNulls() {
        val original = NullablePrimitives(
            string = null,
            int = null,
            bool = null,
            long = null,
            double = null,
            float = null,
            byte = null,
            char = null,
            short = null,
            enum = null
        )
        runTest(original, NullablePrimitives::class)
    }

}

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
private data class Primitives(
    val string: String,
    val int: Int,
    val bool: Boolean,
    val long: Long,
    val double: Double,
    val float: Float,
    val byte: Byte,
    val char: Char,
    val short: Short,
    val enum: TestEnum
)

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
private data class NullablePrimitives(
    val string: String?,
    val int: Int?,
    val bool: Boolean?,
    val long: Long?,
    val double: Double?,
    val float: Float?,
    val byte: Byte?,
    val char: Char?,
    val short: Short?,
    val enum: TestEnum?
)

private enum class TestEnum {
    Enum1, Enum2
}
