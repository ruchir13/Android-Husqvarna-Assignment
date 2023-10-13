package com.husqvarna.assignment.di

import com.husqvarna.assignment.data.repository.MovieRepoImpl
import com.husqvarna.assignment.data.source.remote.RetrofitApiService
import com.husqvarna.assignment.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MovieRepoModule {

    @Provides
    fun provideMovieRepo(apiService: RetrofitApiService): MovieRepository =
        MovieRepoImpl(apiService)
}