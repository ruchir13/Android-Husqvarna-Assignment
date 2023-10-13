package com.husqvarna.assignment.domain.model

data class MovieDetail(
    val original_title: String,
    val overview: String,
    val genres: List<Genres>,
    val release_date: String,
    val poster_path: String,
    val vote_count: Int,
    val tagline: String
)