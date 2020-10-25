package com.evnmo.msgpack.converter


import com.evnmo.msgpack.serializer.MessagePack
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.MockResponse
import okio.Buffer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

internal class MixedConverterTest : BaseTest() {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
    private val messagePack = MessagePack {
        useDebugLogging = true
    }

    @Before
    fun setUp() {
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(
                MessagePackConverterFactory(
                    serializationStrategy = Strategy.AsJson(json),
                    deserializationStrategy = Strategy.AsMessagePack(messagePack)
                )
            )
            .build()
        service = retrofit.create(Service::class.java)
    }

    @Test
    fun serialize() {
        server.enqueue(MockResponse())

        service.serialize(Laptop()).execute()

        val actualRequest = server.takeRequest()
        val expectedRequest = json.encodeToString(Laptop())

        assertEquals(expectedRequest, actualRequest.body.readUtf8())
        assertEquals("application/json; charset=utf-8", actualRequest.headers["Content-Type"])
    }

    @Test
    fun deserialize() {
        val mockResponse = messagePack.encodeToByteArray(Laptop())

        server.enqueue(MockResponse().setBody(Buffer().write(mockResponse)))

        val actualResponse = service.deserialize().execute().body()!!

        assertEquals(Laptop(), actualResponse)
    }
}
