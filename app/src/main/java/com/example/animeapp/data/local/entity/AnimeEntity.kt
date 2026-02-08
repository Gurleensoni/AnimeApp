package com.example.animeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_table")
data class AnimeEntity (
    @PrimaryKey val id:Int,
    val title: String,
    val imageUrl: String,
    val score: Double?,
    val episodes: Int?
)