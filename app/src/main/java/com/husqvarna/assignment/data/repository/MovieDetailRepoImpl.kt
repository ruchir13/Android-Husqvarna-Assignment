package com.husqvarna.assignment.data.repository

import com.husqvarna.assignment.data.source.remote.Resource
import com.husqvarna.assignment.data.source.remote.RetrofitApiService
import com.husqvarna.assignment.domain.model.MovieDetail
import com.husqvarna.assignment.domain.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class MovieDetailRepoImpl constructor(private val retrofitService: RetrofitApiService) :
    MovieDetailRepository {

    override fun getMovieDetails(movieId: String): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())
        try {
            val response = retrofitService.getMovieDetails(movieId)
            emit(Resource.Success(response))
        } catch (ex: HttpException) {
            emit(
                Resource.Error(
                    message = "Error code: ${ex.code()}, " +
                            "Unable to get movie details, please try again later."
                )
            )
        } catch (ex: Exception) {
            emit(Resource.Error(message = "Unable to get movie details, please try again later."))
        }
    }.flowOn(Dispatchers.IO)
}