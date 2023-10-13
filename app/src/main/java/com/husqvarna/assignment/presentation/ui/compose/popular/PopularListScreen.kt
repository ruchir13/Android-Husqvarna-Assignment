package com.husqvarna.assignment.presentation.ui.compose.popular

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.husqvarna.assignment.presentation.viewmodel.popular.PopularMovieViewModel

@Composable
fun PopularListScreen(
    moviesViewModel: PopularMovieViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val state = moviesViewModel.state.value

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
            state.moviesList.isNotEmpty() -> {
                items(state.moviesList.size) { i ->
                    MovieItem(movie = state.moviesList[i]) { movie ->
                        navController.navigate("movieDetail/${movie.id}")
                    }
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