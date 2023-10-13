package com.husqvarna.assignment.presentation.viewmodel.details

import com.husqvarna.assignment.domain.model.MovieDetail

data class MovieDetailUiState(
    val movieDetail: MovieDetail? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)