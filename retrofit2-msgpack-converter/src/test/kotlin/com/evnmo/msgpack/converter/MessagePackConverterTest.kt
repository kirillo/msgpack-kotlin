package com.evnmo.msgpack.converter


import com.evnmo.msgpack.serializer.MessagePack
import kotlinx.serialization.encodeToByteArray
import okhttp3.mockwebserver.MockResponse
import okio.Buffer
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

internal class MessagePackConverterTest : BaseTest() {

    @Before
    fun setUp() {
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(MessagePackConverterFactory(Strategy.MessagePack))
            .build()
        service = retrofit.create(Service::class.java)
    }

    @Test
    fun serialize() {
        server.enqueue(MockResponse())

        service.serialize(Laptop()).execute()

        val actualRequest = server.takeRequest()
        val expectedRequest = MessagePack.Default.encodeToByteArray(Laptop())

        assertArrayEquals(expectedRequest, actualRequest.body.readByteArray())
        assertEquals("application/x-msgpack", actualRequest.headers["Content-Type"])
    }

    @Test
    fun deserialize() {
        val mockResponse = MessagePack.Default.encodeToByteArray(Laptop())

        server.enqueue(MockResponse().setBody(Buffer().write(mockResponse)))

        val actualResponse = service.deserialize().execute().body()!!

        assertEquals(Laptop(), actualResponse)
    }
}
