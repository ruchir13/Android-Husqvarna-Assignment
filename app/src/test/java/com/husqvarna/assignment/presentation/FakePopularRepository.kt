package com.husqvarna.assignment.presentation

import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.domain.model.Movie
import com.husqvarna.assignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePopularRepository : MovieRepository {
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

    val mockMovieList = listOf(fakeMovie1, fakeMovie2)
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return flowOf(Resource.Success(mockMovieList))
    }

}