package com.example.animeapp.di

import android.content.Context
import androidx.room.Room
import com.example.animeapp.data.local.AnimeDatabase
import com.example.animeapp.data.local.dao.AnimeDao
import com.example.animeapp.data.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDao(db: AnimeDatabase): AnimeDao {
        return db.animeDao()
    }

    @Provides
    @Singleton
    fun provideRepository(
        dao: AnimeDao
    ): AnimeRepository {
        return AnimeRepository(dao)
    }
}
