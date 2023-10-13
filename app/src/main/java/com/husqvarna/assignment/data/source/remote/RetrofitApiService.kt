package com.husqvarna.assignment.data.source.remote

import com.husqvarna.assignment.domain.model.MovieDetail
import com.husqvarna.assignment.domain.model.PopularMovies
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApiService {

    @GET("popular")
    suspend fun getPopularMovies(): PopularMovies

    @GET("{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String): MovieDetail

    companion object {
        const val BASE_URL: String = "https://api.themoviedb.org/3/movie/"
    }
}
