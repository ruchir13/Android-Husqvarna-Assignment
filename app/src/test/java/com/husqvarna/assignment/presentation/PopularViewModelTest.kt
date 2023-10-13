package com.husqvarna.assignment.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.husqvarna.assignment.domain.model.Movie
import com.husqvarna.assignment.domain.usecase.GetPopularMovies
import com.husqvarna.assignment.presentation.viewmodel.popular.PopularMovieViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class PopularViewModelTest {
    private lateinit var moviesViewModel: PopularMovieViewModel

    private lateinit var getPopularMovies: GetPopularMovies

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

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

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        getPopularMovies = GetPopularMovies(FakePopularRepository())

        moviesViewModel = PopularMovieViewModel(getPopularMovies)
    }

    @Test
    fun getMovies() {
        runTest {
            val mockMovieList = listOf(fakeMovie1, fakeMovie2)
            //When
            moviesViewModel.getMovies()
            //Then
            assertEquals(
                mockMovieList[0].title,
                moviesViewModel.state.value.moviesList[0].title
            )
        }
    }
}