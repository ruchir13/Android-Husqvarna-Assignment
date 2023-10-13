package com.husqvarna.assignment.data.source.remote

import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import okhttp3.mockwebserver.MockWebServer
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(JUnit4::class)
class RetrofitApiServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var retrofitService: RetrofitApiService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createApiService() {
        mockWebServer = MockWebServer()
        retrofitService = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()
            .create(RetrofitApiService::class.java)
    }

    @Test
    fun getPopularMoviesTest() = runBlocking {
        enqueueResponse("/popularMovies.json")
        val popularMovies = retrofitService.getPopularMovies().results

        Assert.assertEquals(false, popularMovies.isEmpty())
        Assert.assertEquals("The Nun II", popularMovies[0].title)
        Assert.assertEquals(20, popularMovies.size)
        Assert.assertEquals("2023-09-06", popularMovies[0].release_date)
        Assert.assertEquals("/5gzzkR7y3hnY8AD1wXjCnVlHba5.jpg", popularMovies[0].poster_path)
    }

    @Test
    fun getMovieDetailsTest() = runBlocking {
        enqueueResponse("/movieDetail.json")
        val movieDetails = retrofitService.getMovieDetails("968051")

        Assert.assertEquals("The Nun II", movieDetails.original_title)
        Assert.assertEquals(3, movieDetails.genres.size)
        Assert.assertEquals("2023-09-06", movieDetails.release_date)
        Assert.assertEquals("Confess your sins.", movieDetails.tagline)
        Assert.assertEquals("/5gzzkR7y3hnY8AD1wXjCnVlHba5.jpg", movieDetails.poster_path)
        Assert.assertEquals(879, movieDetails.vote_count)
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("mock_response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }

        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}