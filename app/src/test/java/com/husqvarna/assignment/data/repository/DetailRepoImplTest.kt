package com.husqvarna.assignment.data.repository

import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.data.source.remote.RetrofitApiService
import com.husqvarna.assignment.domain.model.Genres
import com.husqvarna.assignment.domain.model.MovieDetail
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class DetailRepoImplTest {

    private lateinit var detailRepo: MovieDetailRepoImpl

    private val fakeMovieDetail =
        MovieDetail(
            original_title = "Fake Title 1",
            overview = "This is overview of Fake movie - 1",
            genres = listOf(Genres("1","Crime"), Genres("2","Thriller")),
            release_date = "2023-10-12",
            poster_path = "/poster_path_1",
            vote_count = 100,
            tagline = "Fake Tagline 1"
        )

    @Mock
    private lateinit var apiService: RetrofitApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailRepo = MovieDetailRepoImpl(apiService)
    }

    @Test
    fun `getPopularMovies returns success`() = runTest {
        // Arrange
        val mockResponse = fakeMovieDetail
        Mockito.`when`(apiService.getMovieDetails("123")).thenReturn(mockResponse)

        // Act
        val result = detailRepo.getMovieDetails("23").toList()

        // Assert
        assert(result[0] is Resource.Loading)
        assert(result[1] is Resource.Success)
        assert((result[1] as Resource.Success).data?.vote_count==100)
    }

    @Test
    fun `getPopularMovies handles HTTP exception`() = runTest {
        // Arrange
        val errorResponse: Response<MovieDetail> =
            Response.error(404, ResponseBody.create(null, ""))
        launch {
            Mockito.`when`(apiService.getMovieDetails("123")).thenReturn(errorResponse.body())
            // Act
            val result = detailRepo.getMovieDetails("123")
            // Assert
        }
    }
}