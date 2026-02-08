package com.example.animeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_detail_table")
data class AnimeDetailEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val synopsis: String?,
    val imageUrl: String,
    val score: Double?,
    val episodes: Int?,
    val genres: String,
    val trailerUrl: String?
)
