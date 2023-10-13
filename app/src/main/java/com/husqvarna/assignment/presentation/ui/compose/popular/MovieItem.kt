package com.husqvarna.assignment.presentation.ui.compose.popular

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.husqvarna.assignment.domain.model.Movie
import com.husqvarna.assignment.presentation.ui.compose.CoilImage

@Composable
fun MovieItem(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 25.dp
            )
            .clickable(
                onClick = { onItemClick(movie) }
            )
    ) {
        CoilImage(
            imageUrl = movie.poster_path,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


