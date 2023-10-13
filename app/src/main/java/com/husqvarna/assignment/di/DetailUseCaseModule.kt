package com.husqvarna.assignment.di

import com.husqvarna.assignment.domain.repository.MovieDetailRepository
import com.husqvarna.assignment.domain.usecase.GetMovieDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DetailUseCaseModule {

    @Provides
    @Singleton
    fun provideDetailsUseCase(repository: MovieDetailRepository): GetMovieDetails =
        GetMovieDetails(repository)
}