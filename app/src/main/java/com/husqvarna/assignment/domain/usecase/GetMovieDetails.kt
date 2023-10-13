package com.husqvarna.assignment.domain.usecase

import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.domain.model.MovieDetail
import com.husqvarna.assignment.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetails(
    private val detailRepository: MovieDetailRepository
) {
    operator fun invoke(movieId: String): Flow<Resource<MovieDetail>> {
        return detailRepository.getMovieDetails(movieId)
    }
}