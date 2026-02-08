package com.example.animeapp.data.remote

import com.example.animeapp.data.model.AnimeDetailResponse
import com.example.animeapp.data.model.AnimeResponse
import com.example.animeapp.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApiService {
        @GET("anime/{id}")
        suspend fun getAnimeDetails(
            @Path("id") id: Int
        ): AnimeDetailResponse

        @GET("top/anime")
        suspend fun getTopAnime(
                @Query("page") page: Int
        ): AnimeResponse

        @GET("anime/{id}/characters")
        suspend fun getCharacters(
                @Path("id") id: Int
        ): CharacterResponse


}