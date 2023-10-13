package com.husqvarna.assignment.di

import com.husqvarna.assignment.domain.repository.MovieRepository
import com.husqvarna.assignment.domain.usecase.GetPopularMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieUseCaseModule {

    @Provides
    @Singleton
    fun providePopularMoviesUseCase(repository: MovieRepository): GetPopularMovies =
        GetPopularMovies(repository)
}