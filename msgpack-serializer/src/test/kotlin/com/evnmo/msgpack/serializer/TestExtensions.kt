package com.evnmo.msgpack.serializer

import com.fasterxml.jackson.annotation.JsonFormat
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.junit.Test
import java.util.*

internal class TestExtensions : BaseTest() {
    @Test
    fun testDate() {
        val original = Extensions(
            date = Date(System.currentTimeMillis() / 1000 * 1000)
        )
        runTest(original, Extensions::class, compareWithJackson = false)
    }

    @Serializable
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private data class Extensions(
        @Contextual
        val date: Date
    )
}
