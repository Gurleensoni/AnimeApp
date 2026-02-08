package com.example.animeapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animeapp.data.local.entity.AnimeDetailEntity
import com.example.animeapp.data.local.entity.AnimeEntity

@Dao
interface AnimeDao {

    // ----- LIST -----
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeList(list: List<AnimeEntity>)

    @Query("SELECT * FROM anime_table")
    fun getAnimeList(): LiveData<List<AnimeEntity>>

    // ----- DETAILS -----
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeDetail(detail: AnimeDetailEntity)

    @Query("SELECT * FROM anime_detail_table WHERE id = :id")
    fun getAnimeDetail(id: Int): LiveData<AnimeDetailEntity?>

    @Query("""
SELECT * FROM anime_table 
WHERE title LIKE '%' || :query || '%'
""")
    fun searchAnime(query: String):
            LiveData<List<AnimeEntity>>

}
