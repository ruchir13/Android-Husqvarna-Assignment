package com.husqvarna.assignment.domain.repository

import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
}