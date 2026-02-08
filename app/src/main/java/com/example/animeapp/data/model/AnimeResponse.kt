package com.example.animeapp.data.model

data class AnimeResponse(
    val `data`: List<Anime>,
    val pagination: Pagination
)