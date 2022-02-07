package com.suhail.jetpackcomposehiltmvvmcoroutine.dashboard.home

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.suhail.jetpackcomposehiltmvvmcoroutine.service.ApiService
import com.suhail.jetpackcomposehiltmvvmcoroutine.util.MockResponseFileReader
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TickerApiTest {
    private val server = MockWebServer()
    private lateinit var repository: HomeScreenRepository
    lateinit var mockedResponse: String

    private val gson = GsonBuilder().setLenient().create()

    @Before
    fun setUp() {
        server.start(8000)

        val url = server.url("/").toString()
        val okHttpClient = OkHttpClient.Builder().build()
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(okHttpClient)
            .build().create(ApiService::class.java)

        repository = HomeScreenRepositoryImpl(service)
    }

    @Test
    fun `test ticker api success`() {
        mockedResponse = MockResponseFileReader("tickerapi/tickerapi.json").content
        server.enqueue(MockResponse().setResponseCode(200).setBody(mockedResponse))
        val response = runBlocking{
            repository.getTicker()
        }

        val json = gson.toJson(response.body())
        val resultResponse = JsonParser().parse(json)
        val expectedResponse = JsonParser().parse(mockedResponse)

        Assert.assertNotNull(resultResponse)
        Assert.assertTrue(resultResponse.equals(expectedResponse))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

}