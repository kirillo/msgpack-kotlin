package com.evnmo.msgpack.converter


import com.evnmo.msgpack.serializer.MessagePack
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToByteArray
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal class ConverterTest {
    @get:Rule
    val server = MockWebServer()

    private lateinit var service: Service

    @Before
    fun setUp() {
        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(MessagePackConverterFactory())
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

    private interface Service {
        @GET("/")
        fun deserialize(): Call<Laptop>

        @POST("/")
        fun serialize(@Body user: Laptop): Call<Void?>
    }

    @Serializable
    private data class Laptop(
        val name: String = "Macbook",
        val inches: Int = 13,
        val installedApps: List<String> = listOf("Safari", "Mail")
    )
}
