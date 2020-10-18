package com.evnmo.msgpack

import com.fasterxml.jackson.annotation.JsonFormat
import kotlinx.serialization.Serializable
import org.junit.Test

class TestCollections : BaseTest() {

    @Test
    fun testCollections() {
        val original = Collections(
            list = listOf("item1", "item2"),
            map = mapOf("key1" to "value1", "key2" to "value2"),
            bytes = ByteArray(9)
        )
        runTest(original, Collections::class)
    }

    @Test
    fun testEmptyCollections() {
        val original = Collections(
            list = listOf(),
            map = mapOf(),
            bytes = ByteArray(0)
        )
        runTest(original, Collections::class)
    }

    @Test
    fun testNullableCollections() {
        val original = NullableCollections(
            list = null,
            map = null,
            bytes = null
        )
        runTest(original, NullableCollections::class)
    }

    @Test
    fun testNullableCollectionsNotNull() {
        val original = NullableCollections(
            list = listOf("item1", "item2"),
            map = mapOf("key1" to "value1", "key2" to "value2"),
            bytes = ByteArray(9)
        )
        runTest(original, NullableCollections::class)
    }

    @Test
    fun testNullableEmptyCollections() {
        val original = NullableCollections(
            list = emptyList(),
            map = emptyMap(),
            bytes = ByteArray(0)
        )
        runTest(original, NullableCollections::class)
    }

    @Test
    fun testCollectionsWithNullableItemsNotNull() {
        val original = CollectionsWithNullableItems(
            list = listOf("item1", "item2"),
            map = mapOf("key1" to "value1", "key2" to "value2")
        )
        runTest(original, CollectionsWithNullableItems::class)
    }

    @Test
    fun testCollectionsWithNullableItems() {
        val original = CollectionsWithNullableItems(
            list = listOf(null, "item2"),
            map = mapOf("key1" to "value1", "key2" to null)
        )
        runTest(original, CollectionsWithNullableItems::class)
    }

    @Test
    fun testNullableCollectionsWithNullableItems() {
        val original = NullableCollectionsWithNullableItems(
            list = listOf(null, "item2"),
            map = mapOf("key1" to "value1", "key2" to null)
        )
        runTest(original, NullableCollectionsWithNullableItems::class)
    }
}

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
private data class Collections(
    val list: List<String>,
    val map: Map<String, String>,
    val bytes: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Collections

        if (list != other.list) return false
        if (map != other.map) return false
        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = list.hashCode()
        result = 31 * result + map.hashCode()
        result = 31 * result + bytes.contentHashCode()
        return result
    }
}

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
private data class NullableCollections(
    val list: List<String>?,
    val map: Map<String, String>?,
    val bytes: ByteArray?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NullableCollections

        if (list != other.list) return false
        if (map != other.map) return false
        if (bytes != null) {
            if (other.bytes == null) return false
            if (!bytes.contentEquals(other.bytes)) return false
        } else if (other.bytes != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = list?.hashCode() ?: 0
        result = 31 * result + (map?.hashCode() ?: 0)
        result = 31 * result + (bytes?.contentHashCode() ?: 0)
        return result
    }
}

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
private data class CollectionsWithNullableItems(
    val list: List<String?>,
    val map: Map<String?, String?>,
)

@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
private data class NullableCollectionsWithNullableItems(
    val list: List<String?>?,
    val map: Map<String?, String?>?,
)
