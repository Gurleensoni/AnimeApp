package com.example.animeapp.ui.animeList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.local.entity.AnimeEntity
import com.example.animeapp.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {


    private val _animeList =
        MediatorLiveData<List<AnimeEntity>>()
    val animeList: LiveData<List<AnimeEntity>> = _animeList

    private val dbSource =
        repository.getAnimeFromDb()

    private var currentSource:
            LiveData<List<AnimeEntity>>? = null

    private var page = 1
    private var loading = false

    init {
        currentSource = dbSource

        _animeList.addSource(dbSource) {
            _animeList.value = it
        }

        loadNextPage()
    }

    fun loadNextPage() {
        if (loading) return

        viewModelScope.launch {
            loading = true
            try {
                repository.refreshAnime(page)
                page++
            } catch (e: Exception) {
                Log.e("API_ERROR", "Load failed: ${e.message}", e)
            }

            loading = false
        }
    }

    fun searchAnime(query: String) {

        currentSource?.let {
            _animeList.removeSource(it)
        }

        val source =
            if (query.isBlank())
                dbSource
            else
                repository.searchAnime(query)

        currentSource = source

        _animeList.addSource(source) {
            _animeList.value = it
        }
    }
}
