package com.evnmo.msgpack

import com.fasterxml.jackson.annotation.JsonFormat
import kotlinx.serialization.Serializable

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
data class TestData(
    val string: String,
    val nullableString: String?,
    val int: Int,
    val nullableInt: Int?,
    val bool: Boolean,
    val nullableBool: Boolean?,
    val long: Long,
    val nullableLong: Long?,
    val double: Double,
    val nullableDouble: Double?,
    val float: Float,
    val nullableFloat: Float?,
    val byte: Byte,
    val nullableByte: Byte?,
    val char: Char,
    val nullableChar: Char?,
    val short: Short,
    val nullableShort: Short?,

    val enum: Type,
    val nullableEnum: Type?,

    val list: List<String>,
    val nullableList: List<String>?,
    val map: Map<String, String>,
    val nullableMap: Map<String, String>?,
//    val byteArray: ByteArray,
//    val nullableByteArray: ByteArray?,

    val innerClass: Info
)

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
data class Info(
    val enum: Type,
    val double: Double
)

enum class Type {
    Enum1, Enum2
}