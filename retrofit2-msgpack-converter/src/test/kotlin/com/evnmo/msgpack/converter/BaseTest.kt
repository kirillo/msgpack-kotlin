package com.evnmo.msgpack.converter


import kotlinx.serialization.Serializable
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal abstract class BaseTest {
    @get:Rule
    val server = MockWebServer()

    protected lateinit var service: Service

    protected interface Service {
        @GET("/")
        fun deserialize(): Call<Laptop>

        @POST("/")
        fun serialize(@Body user: Laptop): Call<Void?>
    }

    @Serializable
    protected data class Laptop(
        val name: String = "MacBook",
        val inches: Int = 13,
        val installedApps: List<String> = listOf("Safari", "Mail")
    )
}
