package com.husqvarna.assignment.presentation.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.husqvarna.assignment.R

@Composable
fun CoilImage(imageUrl: String, modifier: Modifier,) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w780/${imageUrl}",
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp)),
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
    )
}
