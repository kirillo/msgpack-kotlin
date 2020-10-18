package com.evnmo.msgpack

import org.junit.Test

class TestCollections : BaseTest() {

    @Test
    fun testCollections() {
        val original = Collections(
            list = listOf("item1", "item2"),
            map = mapOf("key1" to "value1", "key2" to "value2")
        )
        runTest(original, Collections::class)
    }

    @Test
    fun testEmptyCollections() {
        val original = Collections(
            list = listOf(),
            map = mapOf()
        )
        runTest(original, Collections::class)
    }
}