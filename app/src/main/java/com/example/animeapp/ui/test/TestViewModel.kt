package com.example.animeapp.ui.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class TestViewModel : ViewModel() {

    fun testTopAnimeApi() {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitClient.api.getTopAnime(1)

                Log.d(
                    "API_TEST",
                    "Anime count: ${response.data.size}"
                )

            } catch (e: Exception) {
                Log.e(
                    "API_TEST",
                    "Error: ${e.message}"
                )
            }
        }
    }

    fun testDetailApi() {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitClient.api.getAnimeDetails(1)

                Log.d(
                    "API_TEST",
                    "Anime title: ${response.data.title}"
                )

            } catch (e: Exception) {
                Log.e(
                    "API_TEST",
                    "Detail Error: ${e.message}"
                )
            }
        }
    }
}
