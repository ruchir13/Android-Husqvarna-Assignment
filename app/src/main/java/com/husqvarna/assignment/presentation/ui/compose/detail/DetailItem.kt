package com.husqvarna.assignment.presentation.ui.compose.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.husqvarna.assignment.domain.model.MovieDetail
import com.husqvarna.assignment.presentation.ui.compose.CoilImage

@Composable
fun DetailMovieItem(movieDetail: MovieDetail) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .fillMaxWidth(),
    ) {

        CoilImage(
            imageUrl = movieDetail.poster_path, modifier = Modifier.fillMaxWidth()
        )
        /*CoilImage(
            imageUrl = movieDetail.poster_path, modifier = Modifier
                .width(200.dp)
                .height(250.dp)
        )*/
    }
    Column(modifier = Modifier.padding(start = 10.dp)) {
        Text(
            text = movieDetail.original_title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = movieDetail.tagline,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            fontSize = 17.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .background(Color.Black)
                .clip(RoundedCornerShape(20.dp))
                .padding(8.dp)
        ) {
            Text(
                text = "${movieDetail.vote_count} votes",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Release date: ${movieDetail.release_date}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Genres",
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
    )
    LazyRow {
        items(movieDetail.genres.size) {
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .clip(RoundedCornerShape(20.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = movieDetail.genres[it].name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Overview",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
    )
    Row {
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = movieDetail.overview,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
        )
    }
}