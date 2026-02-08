package com.example.animeapp.ui.animeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.App
import com.example.animeapp.data.local.AnimeDatabase
import com.example.animeapp.data.local.entity.AnimeDetailEntity
import com.example.animeapp.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {


    private val _animeDetail =
        MutableLiveData<AnimeDetailEntity?>()
    val animeDetail: LiveData<AnimeDetailEntity?>
        get() = _animeDetail
    private val _cast = MutableLiveData<String>()
    val cast: LiveData<String> = _cast


    fun loadAnimeDetail(id: Int): LiveData<AnimeDetailEntity?> {
        viewModelScope.launch {
            repository.refreshAnimeDetail(id)
        }

        viewModelScope.launch {
            val castList = repository.getMainCast(id)
            _cast.postValue(castList.joinToString(", "))
        }

        return repository.getAnimeDetailFromDb(id)
    }

}

