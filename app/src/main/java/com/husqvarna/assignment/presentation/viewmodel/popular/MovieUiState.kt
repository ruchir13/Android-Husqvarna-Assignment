package com.husqvarna.assignment.presentation.viewmodel.popular

import com.husqvarna.assignment.domain.model.Movie

data class MovieUiState(
    val moviesList: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)