package com.husqvarna.assignment.data.repository

import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.data.source.remote.RetrofitApiService
import com.husqvarna.assignment.domain.model.Movie
import com.husqvarna.assignment.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class MovieRepoImpl constructor(private val apiService: RetrofitApiService) : MovieRepository {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getPopularMovies()
            emit(Resource.Success(response.results))
        } catch (ex: HttpException) {
            emit(
                Resource.Error(
                    message = "Error code: ${ex.code()}, " +
                            "Unable to fetch popular movies. Please try again later."
                )
            )
        } catch (ex: IOException) {
            emit(Resource.Error(message = "Unable to fetch popular movies. Please try again later."))
        } catch (ex: Exception) {
            emit(Resource.Error(message = "Unable to fetch popular movies. Please try again later."))
        }
    }.flowOn(Dispatchers.IO)
}