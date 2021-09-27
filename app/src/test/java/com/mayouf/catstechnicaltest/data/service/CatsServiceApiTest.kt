package com.mayouf.catstechnicaltest.data.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mayouf.catstechnicaltest.data.source.CatsServiceApi
import com.mayouf.catstechnicaltest.domain.model.Cats
import com.mayouf.catstechnicaltest.domain.utils.Constants
import com.mayouf.catstechnicaltest.domain.utils.createOkHttpBuilderExtension
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.observers.TestObserver
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CatsServiceApiTest {

    private lateinit var api: CatsServiceApi
    private var mockWebServer: MockWebServer = MockWebServer()
    private val builder = OkHttpClient.Builder()
    private lateinit var gson: Gson
    private lateinit var body: String

    @Before
    fun setUp() {
        api = mock()
        mockWebServer.start()
        gson = GsonBuilder()
            .setLenient()
            .create()
        body = "[\n" +
                "    {\n" +
                "        \"breeds\": [],\n" +
                "        \"id\": \"YK2tDe9R9\",\n" +
                "        \"url\": \"https://cdn2.thecatapi.com/images/YK2tDe9R9.jpg\",\n" +
                "        \"width\": 2982,\n" +
                "        \"height\": 2982\n" +
                "    },\n" +
                "    {\n" +
                "        \"breeds\": [],\n" +
                "        \"id\": \"GpaBX4yhD\",\n" +
                "        \"url\": \"https://cdn2.thecatapi.com/images/GpaBX4yhD.jpg\",\n" +
                "        \"width\": 1200,\n" +
                "        \"height\": 1200\n" +
                "    },\n" +
                "    {\n" +
                "        \"breeds\": [],\n" +
                "        \"id\": \"lQnGGje_l\",\n" +
                "        \"url\": \"https://cdn2.thecatapi.com/images/lQnGGje_l.jpg\",\n" +
                "        \"width\": 318,\n" +
                "        \"height\": 159\n" +
                "    },\n" +
                "    {\n" +
                "        \"breeds\": [],\n" +
                "        \"id\": \"ZRnAqKdOq\",\n" +
                "        \"url\": \"https://cdn2.thecatapi.com/images/ZRnAqKdOq.png\",\n" +
                "        \"width\": 500,\n" +
                "        \"height\": 408\n" +
                "    },\n" +
                "    {\n" +
                "        \"breeds\": [],\n" +
                "        \"id\": \"-leWf-21l\",\n" +
                "        \"url\": \"https://cdn2.thecatapi.com/images/-leWf-21l.jpg\",\n" +
                "        \"width\": 690,\n" +
                "        \"height\": 921\n" +
                "    }\n" +
                "]"

    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test that get cats succeeds with no error`() {

        val okHttpClient: OkHttpClient = builder.createOkHttpBuilderExtension(
            body, 200
        )

        val retrofit = Retrofit.Builder().baseUrl(mockWebServer.url(Constants.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CatsServiceApi::class.java)

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setHeader("content-type", "application/json")
        )

        val service: CatsServiceApi = retrofit as CatsServiceApi

        val call: TestObserver<List<Cats>> = service.getCats().test()

        call.awaitTerminalEvent()
        call.assertNoErrors()
        call.assertComplete()
    }

    @Test
    fun `test that get cats succeeds with error`() {

        val okHttpClient: OkHttpClient = builder.createOkHttpBuilderExtension(
            body, 500
        )

        val retrofit = Retrofit.Builder().baseUrl(mockWebServer.url(Constants.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CatsServiceApi::class.java)

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setHeader("content-type", "application/json")
        )

        val service: CatsServiceApi = retrofit as CatsServiceApi

        val call: TestObserver<List<Cats>> = service.getCats().test()

        call.awaitTerminalEvent()
        call.assertErrorMessage("HTTP 500 OK")
    }
}