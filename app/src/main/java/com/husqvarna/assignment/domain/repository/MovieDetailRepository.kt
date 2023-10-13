package com.husqvarna.assignment.domain.repository

import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun getMovieDetails(movieId: String): Flow<Resource<MovieDetail>>
}