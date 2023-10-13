package com.husqvarna.assignment.data.repository

import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.data.source.remote.RetrofitApiService
import com.husqvarna.assignment.domain.model.Movie
import com.husqvarna.assignment.domain.model.PopularMovies
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

class MovieRepoImplTest {

    private lateinit var movieRepo: MovieRepoImpl

    private val fakeMovie1 =
        Movie(
            id = 1,
            title = "Movie-1",
            poster_path = "/poster_path_1",
            release_date = "2023-10-12"
        )
    private val fakeMovie2 =
        Movie(
            id = 2,
            title = "Movie-2",
            poster_path = "/poster_path_2",
            release_date = "2023-10-12"
        )

    @Mock
    private lateinit var apiService: RetrofitApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieRepo = MovieRepoImpl(apiService)
    }

    @Test
    fun `getPopularMovies returns success`() = runTest {
        // Arrange
        val mockResponse = PopularMovies(listOf(fakeMovie1, fakeMovie2))
        Mockito.`when`(apiService.getPopularMovies()).thenReturn(mockResponse)

        // Act
        val result = movieRepo.getPopularMovies().toList()

        // Assert
        assert(result[0] is Resource.Loading)
        assert(result[1] is Resource.Success)
        assert((result[1] as Resource.Success).data?.size == 1)
    }

    @Test
    fun `getPopularMovies handles HTTP exception`() = runTest {
        // Arrange
        val errorResponse: Response<PopularMovies> =
            Response.error(404, ResponseBody.create(null, ""))
        launch {
            Mockito.`when`(apiService.getPopularMovies()).thenReturn(errorResponse.body())
            // Act
            val result = movieRepo.getPopularMovies().toList()
            // Assert
            assert(result[0] is Resource.Loading)
            assert(result[1] is Resource.Error)
        }
    }
}
