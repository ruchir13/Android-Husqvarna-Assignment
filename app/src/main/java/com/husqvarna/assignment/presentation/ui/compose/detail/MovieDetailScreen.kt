package com.husqvarna.assignment.presentation.ui.compose.detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.husqvarna.assignment.presentation.viewmodel.details.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    detailViewModel: MovieDetailViewModel = hiltViewModel(),
    movieId: Int
) {
    val state = detailViewModel.state.value

    LaunchedEffect(movieId) {
        detailViewModel.fetchMovieDetails(movieId.toString())
    }

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        when {
            state.isLoading -> {
                item {
                    Text(
                        text = "Loading...",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            state.movieDetail != null -> {
                val movieDetail = state.movieDetail
                item {
                    DetailMovieItem(movieDetail)
                }
            }

            else -> {
                item {
                    Text(
                        text = state.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}