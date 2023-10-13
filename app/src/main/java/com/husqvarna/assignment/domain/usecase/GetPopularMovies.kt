package com.husqvarna.assignment.domain.usecase

import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.domain.model.Movie
import com.husqvarna.assignment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMovies(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }
}