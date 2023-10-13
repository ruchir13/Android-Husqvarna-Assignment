package com.husqvarna.assignment.di

import com.husqvarna.assignment.data.repository.MovieDetailRepoImpl
import com.husqvarna.assignment.data.source.remote.RetrofitApiService
import com.husqvarna.assignment.domain.repository.MovieDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DetailRepoModule {

    @Provides
    fun provideMovieDetailRepo(apiService: RetrofitApiService): MovieDetailRepository =
        MovieDetailRepoImpl(apiService)
}