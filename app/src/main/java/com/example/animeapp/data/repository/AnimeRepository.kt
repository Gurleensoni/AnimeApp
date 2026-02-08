package com.example.animeapp.data.repository

import android.util.Log
import com.example.animeapp.data.local.dao.AnimeDao
import com.example.animeapp.data.local.entity.AnimeDetailEntity
import com.example.animeapp.data.local.entity.AnimeEntity
import com.example.animeapp.data.remote.RetrofitClient

class AnimeRepository(private val dao: AnimeDao) {

    private val api = RetrofitClient.api

    fun getAnimeFromDb() = dao.getAnimeList()

    fun searchAnime(query: String) =
        dao.searchAnime(query)

    suspend fun refreshAnime(page: Int): Boolean {

        Log.d("API_DEBUG", "Fetching page $page")

        val response = api.getTopAnime(page = page)

        Log.d("API_DEBUG", "API size = ${response.data.size}")

        val list = response.data.mapNotNull {

            val id = it.mal_id ?: return@mapNotNull null

            AnimeEntity(
                id = id,
                title = it.title,
                imageUrl = it.images.jpg.image_url,
                score = it.score,
                episodes = it.episodes
            )
        }

        Log.d("API_DEBUG", "Mapped size = ${list.size}")

        dao.insertAnimeList(list)

        Log.d("API_DEBUG", "Inserted into DB")

        return list.isNotEmpty()
    }

    fun getAnimeDetailFromDb(id: Int) =
        dao.getAnimeDetail(id)

    suspend fun refreshAnimeDetail(id: Int) {
        val response = api.getAnimeDetails(id)
        val d = response.data

        val entity = AnimeDetailEntity(
            id = d.mal_id,
            title = d.title,
            synopsis = d.synopsis,
            imageUrl = d.images.jpg.image_url,
            score = d.score,
            episodes = d.episodes,
            genres = d.genres.joinToString { it.name },
            trailerUrl = d.trailer?.url as String?
        )

        dao.insertAnimeDetail(entity)
    }

    suspend fun getMainCast(id: Int): List<String> {
        val response = api.getCharacters(id)

        return response.data
            .filter { it.role.contains("Main", true) }
            .map { it.character.name }
    }
}

