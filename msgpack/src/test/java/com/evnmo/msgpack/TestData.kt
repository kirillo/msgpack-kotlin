package com.evnmo.msgpack

import com.fasterxml.jackson.annotation.JsonFormat
import kotlinx.serialization.Serializable

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
data class Primitives(
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
data class NullablePrimitives(
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

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
data class Collections(
    val list: List<String>,
    val map: Map<String, String>,
)

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
data class NullableCollections(
    val list: List<String>?,
    val map: Map<String, String>?,
)

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
data class CollectionsWithNullableItems(
    val list: List<String?>,
    val map: Map<String?, String?>,
)

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
data class NullableCollectionsWithNullableItems(
    val list: List<String?>?,
    val map: Map<String?, String?>?,
)

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
data class NestedClass(
    val innerClass: InnerClass
)

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
data class NullableNestedClass(
    val innerClass: InnerClass?
)

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
data class InnerClass(
    val anEnum: TestEnum,
    val double: Double
)

enum class TestEnum {
    Enum1, Enum2
}